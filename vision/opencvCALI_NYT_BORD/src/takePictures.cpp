#include <ros/ros.h>
#include <iostream>
#include <fstream>
#include <sstream>
#include <string>
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
#include <vector>

using namespace cv;
using namespace Pylon;

int main(int argc, char **argv)
{
    ros::init(argc, argv, "takePictures");
    ros::NodeHandle nh;

    // The exit code of the sample application.
    int exitCode = 0;
    int i = 0;

    // Automagically call PylonInitialize and PylonTerminate to ensure
    // the pylon runtime system is initialized during the lifetime of this object.
    Pylon::PylonAutoInitTerm autoInitTerm;

    CGrabResultPtr ptrGrabResult;

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
        char key = waitKey(32);



        for(int j = 1; j < 11;++j){
        while(camera.IsGrabbing()){

            camera.RetrieveResult( 500, ptrGrabResult, TimeoutHandling_ThrowException);
            if (ptrGrabResult->GrabSucceeded()){



                     fc.Convert(image, ptrGrabResult);
                     std::ostringstream ss;
                     String a,b,c,path;
                          ss << j;


                          cv_img = cv::Mat(ptrGrabResult->GetHeight(), ptrGrabResult->GetWidth(), CV_8UC3,(uint8_t*)image.GetBuffer());
                    imshow("Preview", cv_img);

                      waitKey(0);

//                        a = "images/image";
//                        b = ss.str();
//                        c = ".jpg";
//                        path = a + b + c;
//                        ROS_INFO_STREAM(a);
//                        ROS_INFO_STREAM(b);
//                        ROS_INFO_STREAM(c);
//                        ROS_INFO_STREAM(path);
//                        imwrite(path,cv_img);

                        ROS_INFO("Picture saved");

                        if(j == 10){
                            break;
                        }







            }
            else{
              std::cout << "the pictures was not grabbed" << std::endl;
            }

            ros::spinOnce();
            break;

        }

    }
        ROS_INFO("Done");
        waitKey(0);
    }
    catch(GenICam::GenericException &e){
      // Error handling.
      std::cerr << "An exception occurred." << std::endl
      << e.GetDescription() << std::endl;
      exitCode = 1;
    }
}
