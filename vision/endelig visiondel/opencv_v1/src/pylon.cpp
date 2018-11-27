#include <iostream>
#include "ros/ros.h"
#include "std_msgs/String.h"
#include "std_msgs/Int8.h"
#include "std_msgs/Float32.h"
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
#include <cmath>
#include <ctime>
#include <cstdio>
#include <std_msgs/Empty.h>

using namespace std;
using namespace cv;
using namespace Pylon;

//number of images to be grapped
static const uint32_t c_countOfImagesToGrab = 100;
vector<Point> centerPoint(5);
std::clock_t start;
double duration;
std::string shape;
ros::Publisher ar_pub;

void shiftVector(const Point &newFirst){
    centerPoint.pop_back();
    std::vector<Point>::iterator it = centerPoint.begin();
    centerPoint.insert(it,newFirst);
}

bool isStationary(Point newCenter){
    int count = 0;
    bool succes = false;
    for(int i = 0; i < centerPoint.size(); ++i){
        //caster til en int, da koordinatterne aldrig helt stemmer overens og sammenligner med de sidste 5 frames
       if((int)newCenter.x == (int)centerPoint[i].x && (int)newCenter.y == (int)centerPoint[i].y){
            std::cout << (int)newCenter.x << " " << (int)newCenter.y << std::endl;
            ++count;
        }

    }
    if(count == 5){
        succes = true;
    }
    shiftVector(newCenter);
    return succes;

}

void morphOps(Mat &thresh){

    //create structuring element that will be used to "dilate" and "erode" image.
    //the element chosen here is a 3px by 3px rectangle
    //GaussianBlur(thresh, thresh, Size(9,9), 2,2);

    Mat erodeElement = getStructuringElement( MORPH_RECT,Size(3,3));
    //dilate with larger element so make sure object is nicely visible
    Mat dilateElement = getStructuringElement( MORPH_RECT,Size(8,8));

    erode(thresh,thresh,erodeElement);
    erode(thresh,thresh,erodeElement);

    dilate(thresh,thresh,dilateElement);
    dilate(thresh,thresh,dilateElement);
}

bool trackFilteredObject(int &x, int &y, int &radius, Mat threshold, Mat &cameraFeed){

    Mat temp;
    bool foundObject = false;
    threshold.copyTo(temp);
    //these two vectors needed for output of findContours
    vector< vector<Point> > contours;
    vector<Vec4i> hierarchy;
    //for circles


    //find contours of filtered image using openCV findContours function
    findContours(temp, contours, hierarchy, CV_RETR_TREE, CV_CHAIN_APPROX_SIMPLE, Point(0, 0));
    for(int i = 0; i < contours.size(); ++i){
      //Moments m = contours[0];
      std::vector<Point> approx;
      double area, perimeter;
      perimeter = arcLength(contours[i], true);
      cv::approxPolyDP(contours[i], approx,0.01*arcLength(contours[i], true), true);


      area = contourArea(contours[i]);

      if(area > 2000 && approx.size() > 8){
          //std::cout << area << std::endl;
          //std::cout << perimeter << std::endl;
          radius = perimeter/(2*M_PI);
          Moments moment = moments(contours[i], false);
          Point2f center(moment.m10/moment.m00, moment.m01/moment.m00);
          //drawContours(cameraFeed, contours, i, Scalar(255,0,0), 2, 8, hierarchy);
          circle(cameraFeed,center, radius, Scalar(0,0,255), 3, 8, 0);
          circle(cameraFeed, center, 5, Scalar(0,255,0), -1, 8, 0 );
          //std::cout << "X: "<< center.x << " Y: " << center.y << std::endl;
          if(isStationary(center)){
            foundObject = true;
            start = std::clock();
            x = center.x;
            y = center.y;
          }
      }

    }

    return foundObject;
}

bool trackFilteredObject2(double &x, double &y, int &radius, Mat threshold, Mat &cameraFeed){
    Mat temp;
    bool foundObject = false;
    threshold.copyTo(temp);
    //these two vectors needed for output of findContours
    vector< vector<Point> > contours;
    vector<Vec4i> hierarchy;
    //for circles


    //find contours of filtered image using openCV findContours function
    findContours(temp, contours, hierarchy, CV_RETR_TREE, CV_CHAIN_APPROX_SIMPLE, Point(0, 0));
    for(int i = 0; i < contours.size(); ++i){
      //Moments m = contours[0];
      std::vector<Point> approx;
      double area, perimeter;
      perimeter = arcLength(contours[i], true);
      cv::approxPolyDP(contours[i], approx,0.01*arcLength(contours[i], true), true);


      area = contourArea(contours[i]);

      if(area > 2000 && approx.size() > 8){
          //std::cout << area << std::endl;
          //std::cout << perimeter << std::endl;
          radius = perimeter/(2*M_PI);
          shape = "circle";
          Moments moment = moments(contours[i], false);
          Point2f center(moment.m10/moment.m00, moment.m01/moment.m00);
          //drawContours(cameraFeed, contours, i, Scalar(255,0,0), 2, 8, hierarchy);
          circle(cameraFeed,center, radius, Scalar(0,0,255), 3, 8, 0);
          circle(cameraFeed, center, 5, Scalar(0,255,0), -1, 8, 0 );
          x = center.x;
          y = center.y;
          foundObject = true;

      }

    return foundObject;
    }
}
void pubCallback(const std_msgs::Empty::ConstPtr& robotState){



    //kommer fra programmet cameraCalibration og skal bruges til at fjerne støjen fra kamerat.
    cv::Matx33f K(cv::Matx33f::eye());  // intrinsic camera matrix
    //passer til et lyse bord
    cv::Vec<float, 5> k(-0.244326, 0.0973656, 0, 0, 0); //distortion koeficients
    cv::Matx33f perspective_matrix(cv::Matx33f::eye());
    //hardcoded værdier fra cameraCalibration
    K(0,0) = 1177.615;
    K(0,1) = 0;
    K(0,2) = 720;
    K(1,0) = 0;
    K(1,1) = 1177.615;
    K(1,2) = 540;
    K(2,0) = 0;
    K(2,1) = 0;
    K(2,2) = 1;

    //hardcoded værdier fra cameraCalibration
    perspective_matrix(0,0) = -0.007841199424946668;
    perspective_matrix(0,1) =  -0.9023913671545787;
    perspective_matrix(0,2) = 940.7152293440147;
    perspective_matrix(1,0) = 0.9091055049463109;
    perspective_matrix(1,1) =-0.01166670440700907;
    perspective_matrix(1,2) = -36.93499127499547;
    perspective_matrix(2,0) = 9.13636074497781e-06;
    perspective_matrix(2,1) = -4.45127376630085e-06;
    perspective_matrix(2,2) = 1;
    //output billedet med bolden i og billederne der skal finde bolden
    Mat ballImage, threshold, hsv;

    for(int i = 0; i < centerPoint.size(); ++i){
        centerPoint[i].x = i;
        centerPoint[i].y = i;
    }


    //skal bruges til noget camera calibration, men virker ikke pt.
   // camera_info_manager::CameraInfoManager a(nh, "camera", "/home/mads/catkinws/src/pylon_camera/config/default.yaml");
    //a.loadCameraInfo("/home/mads/catkinws/src/pylon_camera/config/default.yaml");

    ros::Rate loop_rate(10);

    // The exit code of the sample application.
    int exitCode = 0;
    int i = 0;

    // Automagically call PylonInitialize and PylonTerminate to ensure
    // the pylon runtime system is initialized during the lifetime of this object.
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

        camera.StartGrabbing();
        CPylonImage image;
        CImageFormatConverter fc;
        fc.OutputPixelFormat = PixelType_BGR8packed;

        while(camera.IsGrabbing()){
            camera.RetrieveResult( 500, ptrGrabResult, TimeoutHandling_ThrowException);
            if (ptrGrabResult->GrabSucceeded()){
                    fc.Convert(image, ptrGrabResult);
                    cv_img = cv::Mat(ptrGrabResult->GetHeight(), ptrGrabResult->GetWidth(), CV_8UC3,(uint8_t*)image.GetBuffer());

                    cvtColor(cv_img, hsv, CV_BGR2HSV);
                    inRange(hsv, Scalar(11,246,240), Scalar(94,256,256), threshold);

                    int x = 0, y = 0, radius = 0;
                    morphOps(threshold);
                    imshow("CV_Image", threshold);
                    waitKey(1);
                    //tjekker om bolden er fundet
                    if(trackFilteredObject(x, y, radius, threshold, cv_img)){

                      cv_img.copyTo(ballImage);
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

    Mat threshold2, undistortImg, hsv2;

    //fjerner støjen fra cameraet
    undistort(ballImage, undistortImg, K, k);

    Mat result(1245,914, undistortImg.type());
    cv::warpPerspective(undistortImg, result, perspective_matrix, result.size(), CV_INTER_LINEAR);
    cvtColor(result, hsv2, CV_BGR2HSV);

    inRange(hsv2, Scalar(11,246,240), Scalar(94,256,256), threshold2);
    morphOps(threshold2);


      opencv::ballPose ballPose;
      geometry_msgs::Pose pose;
      double x = 0;
      double y = 0;
      int radius = 22;
      int xOffset = 450;
      int yOffset = 640;

      if(trackFilteredObject2(x, y, radius, threshold2, result)){
        std::cout << "succes" << std::endl;
      }
      else{
        std::cout << "not succes" << std::endl;
      }

      if(ros::ok){
        Point center(x, y);
        circle(result, center, 3, Scalar(0,255,0), -1, 8, 0 );

        x = x - xOffset;
        y = y - yOffset;

        //gemmer center koordinatterne i en pose.
        pose.position.x = x/1000;
        pose.position.y = y/1000;
        pose.position.z = 10;
        pose.orientation.w = 1;
        //beregner eksekveringstiden
        duration = (std::clock() -  start) / (double)CLOCKS_PER_SEC;
        cout << "Time [sec]: "<< duration << endl;


        ballPose.pose = pose;
        ballPose.radius = radius;
        ballPose.timer = duration;
        ballPose.shape = shape;
        ballPose.color = "orange";
        ballPose.filepath = "test";
        ballPose.header.frame_id = "camera_frame";


        std::cout << radius << std::endl;

        ar_pub.publish(ballPose);

        ROS_INFO("I published something!");

        ros::spinOnce();

        sleep(2);
      }
}

int main(int argc, char **argv)
{
  ros::init(argc, argv, "grab");
  ros::NodeHandle nh;
  ar_pub = nh.advertise<opencv::ballPose>("/poses",1);
  ros::Subscriber sub = nh.subscribe("pickup_state", 1, pubCallback);


// tid mellem hver søgning
   //ros::Timer t = nh.createTimer(ros::Duration(0.1), pubCallback, false, true);
   ros::spin();


  return 0;
}
