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

                  imshow("CV_Image",cv_img);

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
