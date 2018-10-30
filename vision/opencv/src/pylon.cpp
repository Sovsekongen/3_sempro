#include <iostream>
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

using namespace cv;
using namespace Pylon;

//number of images to be grapped
static const uint32_t c_countOfImagesToGrab = 100;

int main(int argc, char **argv)
{
  ros::init(argc, argv, "grab");
  ros::NodeHandle nh;
  ros::Publisher pb = nh.advertise<opencv::ballPose>("chatter",1000);

  //kommer fra programmet cameraCalibration og skal bruges til at fjerne støjen fra kamerat.
  cv::Matx33f K(cv::Matx33f::eye());  // intrinsic camera matrix
  cv::Vec<float, 5> k(-0.23128, 0.0821809, 0, 0, 0); //distortion koeficients
  cv::Matx33f perspective_matrix(cv::Matx33f::eye());
  //hardcoded værdier fra cameraCalibration
  K(0,0) = 1193.2367;
  K(0,1) = 0;
  K(0,2) = 720;
  K(1,0) = 0;
  K(1,1) = 1193.2367;
  K(1,2) = 540;
  K(2,0) = 0;
  K(2,1) = 0;
  K(2,2) = 1;
  //hardcoded værdier fra cameraCalibration
  perspective_matrix(0,0) = 9.3163917181894507e-001;
  perspective_matrix(0,1) = 1.0294355489711596e-002;
  perspective_matrix(0,2) = -9.4013201509795977e+001;
  perspective_matrix(1,0) = 1.0122584732239079e-002;
  perspective_matrix(1,1) = 9.3240252700298087e-001;
  perspective_matrix(1,2) = -1.6230777306000755e+002;
  perspective_matrix(2,0) = 1.2374797961173999e-005;
  perspective_matrix(2,1) = 2.8149283796858644e-005;
  perspective_matrix(2,2) = 1;
  //output billedet med bolden i
  Mat ballImage;

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
      fc.OutputPixelFormat = PixelType_RGB8packed;

      while(camera.IsGrabbing()){
          camera.RetrieveResult( 500, ptrGrabResult, TimeoutHandling_ThrowException);
          if (ptrGrabResult->GrabSucceeded()){
                  fc.Convert(image, ptrGrabResult);
                  Mat gray;
                  cv_img = cv::Mat(ptrGrabResult->GetHeight(), ptrGrabResult->GetWidth(), CV_8UC3,(uint8_t*)image.GetBuffer());


                  cvtColor(cv_img, gray, CV_BGR2GRAY);
                  GaussianBlur(gray, gray, Size(9,9), 2, 2);
                  imshow("CV_Image", cv_img);
                  std::vector<Vec3f> circleTest;
                  HoughCircles(gray, circleTest,CV_HOUGH_GRADIENT, 1, gray.rows/8, 50, 40,0,2000);
                  waitKey(1);
                  std::cout << "circles number " << i << " " <<  circleTest.size() << std::endl;
                  ++i;
                  if(circleTest.size() > 0){
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

  Mat grayImage, undistortImg;

  //fjerner støjen fra cameraet
  undistort(ballImage, undistortImg, K, k);

  Mat result(818,760, undistortImg.type());
  cv::warpPerspective(undistortImg, result, perspective_matrix, result.size(), CV_INTER_LINEAR);
  cvtColor(result, grayImage, CV_BGR2GRAY);
  GaussianBlur(grayImage, grayImage, Size(9,9), 2, 2);
  std::vector<Vec3f> circleTest;
  HoughCircles(grayImage, circleTest,CV_HOUGH_GRADIENT, 1, grayImage.rows/8, 50, 50,0,2000);


  opencv::ballPose ballPose;
  geometry_msgs::Pose pose;
  int x, y, radius;

  if(ros::ok){


    for(size_t i = 0; i < circleTest.size(); ++i){
      x = cvRound(circleTest[i][0]);
      y = cvRound(circleTest[i][1]);
      radius = cvRound(circleTest[i][2]);
    }
    //læser radius, x og y kordinatter ned i en string stream
    pose.position.x = x;
    pose.position.y = y;
    pose.position.z = 10;
    pose.orientation.w = 1;

    ballPose.pose = pose;
    ballPose.radius = radius;

    pb.publish(ballPose);

    ROS_INFO("I published something!");

    ros::spinOnce();

    sleep(2);
  }


  /*std::cerr << std::endl << "Press Enter to exit." << std::endl;

  while(std::cin.get() != '\n');

  return exitCode;*/

  return 0;
}
