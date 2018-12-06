#include <iostream>
#include <vector>
#include "ros/ros.h"
#include "std_msgs/String.h"
#include "std_msgs/Int8.h"
#include "geometry_msgs/Pose.h"
#include "opencv/ballPose.h"
#include <opencv2/opencv.hpp>
#include <pylon/PylonIncludes.h>
#ifdef PYLON_WIN_BUILD
#include <Pylon/PylonGUI.h>
#endif
#include <pylon/PylonImage.h>
#include <pylon/Pixel.h>
#include <pylon/ImageFormatConverter.h>
#include <camera_info_manager/camera_info_manager.h>
#include <camera_control_msgs/SetGain.h>
#include <pylon/usb/_BaslerUsbCameraParams.h>
#include <pylon/NodeMapProxy.h>



using namespace cv;
using namespace Pylon;
using namespace std;

int H_MIN = 0;
int H_MAX = 256;
int S_MIN = 0;
int S_MAX = 256;
int V_MIN = 0;
int V_MAX = 256;
//default capture width and height
const int FRAME_WIDTH = 640;
const int FRAME_HEIGHT = 480;
//max number of objects to be detected in frame
const int MAX_NUM_OBJECTS=50;
//minimum and maximum object area
const int MIN_OBJECT_AREA = 20*20;
const int MAX_OBJECT_AREA = FRAME_HEIGHT*FRAME_WIDTH/1.5;
//names that will appear at the top of each window
const string windowName = "Original Image";
const string windowName1 = "HSV Image";
const string windowName2 = "Thresholded Image";
const string windowName3 = "After Morphological Operations";
const string trackbarWindowName = "Trackbars";
void on_trackbar( int, void* )
{//This function gets called whenever a
    // trackbar position is changed
}

void createTrackbars(){
    //create window for trackbars


    namedWindow(trackbarWindowName,0);

    //create trackbars
    createTrackbar( "H_MIN", trackbarWindowName, &H_MIN, H_MAX, on_trackbar );
    createTrackbar( "H_MAX", trackbarWindowName, &H_MAX, H_MAX, on_trackbar );
    createTrackbar( "S_MIN", trackbarWindowName, &S_MIN, S_MAX, on_trackbar );
    createTrackbar( "S_MAX", trackbarWindowName, &S_MAX, S_MAX, on_trackbar );
    createTrackbar( "V_MIN", trackbarWindowName, &V_MIN, V_MAX, on_trackbar );
    createTrackbar( "V_MAX", trackbarWindowName, &V_MAX, V_MAX, on_trackbar );
}

void morphOps(Mat &thresh){

    //create structuring element that will be used to "dilate" and "erode" image.
    //the element chosen here is a 3px by 3px rectangle
    GaussianBlur(thresh, thresh, Size(9,9), 2,2);

    Mat erodeElement = getStructuringElement( MORPH_RECT,Size(3,3));
    //dilate with larger element so make sure object is nicely visible
    Mat dilateElement = getStructuringElement( MORPH_RECT,Size(8,8));

    erode(thresh,thresh,erodeElement);
    erode(thresh,thresh,erodeElement);

    dilate(thresh,thresh,dilateElement);
    dilate(thresh,thresh,dilateElement);
}

void trackFilteredObject(int &x, int &y, Mat threshold, Mat &cameraFeed){

    Mat temp;
    threshold.copyTo(temp);
    //these two vectors needed for output of findContours
    vector< vector<Point> > contours;
    vector<Vec4i> hierarchy;
    //for circles
    vector<Vec3f> circles;

    //find contours of filtered image using openCV findContours function
    findContours(temp, contours, hierarchy, CV_RETR_TREE, CV_CHAIN_APPROX_SIMPLE, Point(0, 0));
    for(int i = 0; i < contours.size(); ++i){
      //Moments m = contours[0];
      std::vector<Point> approx;
      double area, perimeter;
      perimeter = arcLength(contours[i], true);
      cv::approxPolyDP(contours[i], approx,0.1*arcLength(contours[i], true), true);


      area = contourArea(contours[i]);

      if(area > 5000 && area < 7000){
          std::cout << area << std::endl;
          Moments moment = moments(contours[i], false);
          Point2f center(moment.m10/moment.m00, moment.m01/moment.m00);
          drawContours(cameraFeed, contours, i, Scalar(255,0,0), 2, 8, hierarchy);
          circle(cameraFeed, center, 5, Scalar(0,255,0), -1, 8, 0 );
      }
      //skal bruges senere til at bestemme om det er en cirkel
      /*if((approx.size() > 8)){
      drawContours(cameraFeed, contours, i, Scalar(255,0,0), 2, 8, hierarchy);
      }*/
    }

    //find circles
    HoughCircles(temp, circles,CV_HOUGH_GRADIENT, 1, temp.rows/8, 200, 100, 0, 0);

    for( int i = 0; i< circles.size(); ++i)
    {
        Point center(cvRound(circles[i][0]), cvRound(circles[i][1]));
        int radius = cvRound(circles[i][2]);
        circle( cameraFeed, center, 3, Scalar(0,255,0), -1, 8, 0 );
              // circle outline
        circle( cameraFeed, center, radius, Scalar(0,0,255), 3, 8, 0 );
    }
}
int main(int argc, char **argv)
{
  ros::init(argc, argv, "pylonSettings");
  ros::NodeHandle nh;


  ros::Rate loop_rate(10);

  // The exit code of the sample application.
  int exitCode = 0;

  Pylon::PylonAutoInitTerm autoInitTerm;

  CGrabResultPtr ptrGrabResult;

  namedWindow("CV_Image",WINDOW_AUTOSIZE);

  try
  {

      CInstantCamera camera( CTlFactory::GetInstance().CreateFirstDevice());


      std::cout << "Using device " << camera.GetDeviceInfo().GetModelName() << std::endl;
      camera.Open();

      GenApi::CIntegerPtr width(camera.GetNodeMap().GetNode("Width"));
      GenApi::CIntegerPtr height(camera.GetNodeMap().GetNode("Height"));
      Mat cv_img(width->GetValue(), height->GetValue(), CV_8UC3);
      Mat hsv, threshold;
      int x, y;
      camera.StartGrabbing();
      CPylonImage image;
      CImageFormatConverter fc;
      fc.OutputPixelFormat = PixelType_BGR8packed;
      //create trackbars
      createTrackbars();


      while(camera.IsGrabbing()){
          camera.RetrieveResult( 500, ptrGrabResult, TimeoutHandling_ThrowException);
          if (ptrGrabResult->GrabSucceeded()){
                  fc.Convert(image, ptrGrabResult);

                  cv_img = cv::Mat(ptrGrabResult->GetHeight(), ptrGrabResult->GetWidth(), CV_8UC3,(uint8_t*)image.GetBuffer(), cv::Mat::AUTO_STEP);
                  cvtColor(cv_img, hsv, CV_BGR2HSV);
                  inRange(hsv, Scalar(H_MIN,S_MIN,V_MIN), Scalar(H_MAX,S_MAX,V_MAX), threshold);
                  //fjerner støj og eroder og dialater
                  morphOps(threshold);

                  //finder objekter
                  trackFilteredObject(x,y,threshold,cv_img);
                  cv::resize(cv_img, cv_img, Size(1000,750));
                  cv::resize(hsv, hsv, Size(1000,750));
                  cv::resize(threshold, threshold, Size(1000,750));
                  imshow("CV_Image",cv_img);
                  imshow("HSV", hsv);
                  imshow("thresh", threshold);

                  waitKey(1);
                  if(waitKey(27) == 30){
                        break;
                  }
          }
          else{
            std::cout << "the pictures was not grabbed" << std::endl;
          }
          ros::spinOnce();

          loop_rate.sleep();
      }

  }
  catch(GenICam::GenericException &e){
    // Error handling.
    std::cerr << "An exception occurred." << std::endl
    << e.GetDescription() << std::endl;
    exitCode = 1;
  }

  return 0;
}
