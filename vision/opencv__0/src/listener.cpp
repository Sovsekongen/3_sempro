#include "ros/ros.h"
#include "std_msgs/String.h"
#include "std_msgs/Int8.h"
#include "std_msgs/Float32MultiArray.h"
#include "opencv2/opencv.hpp"

void chatterCallback(const std_msgs::String::ConstPtr& msg)
{
  ROS_INFO("I heard: [%s]", msg->data.c_str());
}

float arr[5];
float arrK[9];
int j = 0;

void arrayCallback(const std_msgs::Float32MultiArray::ConstPtr& array);

int main(int argc, char **argv)
{
  ros::init(argc, argv, "listener");
  ros::NodeHandle nh;

  ros::Subscriber sub3 = nh.subscribe("array", 1000, arrayCallback);

  if(j >= 1){
    cv::Matx33f K(cv::Matx33f::eye());
    cv::Vec<float, 5> k(0, 0, 0, 0, 0);

    for(int i = 0; i < 5; ++i){
      k[i] = arr[i];
    }
    int n = 0;
    for(int rows = 0; rows < 3; ++rows){
      for(int cols = 0; cols < 3; ++cols){
        K(rows,cols) = arrK[n];
        ++n;
      }
    }
    cv::Mat image;
    image = cv::imread("/home/mads/Documents/billeder/calibration/calibration/Image__2018-10-05__10-29-50.png", cv::IMREAD_COLOR);

    cv::Mat undisortedImage;

    cv::undistort(image, undisortedImage, K, k);

    cv::imshow("undisorted image", undisortedImage);
    cv::waitKey(0);
  }


  //kalder kun callback funktioner, så alt det kode der skal kaldes skal ligges ind i en callback funktion for at det skal
  //kaldes efter koden er eksekveret engang. 
  ros::spin();



  return 0;
}

void arrayCallback(const std_msgs::Float32MultiArray::ConstPtr& array)
{

  int i = 0;
  // print all the remaining numbers
  if(j == 0){
    for(std::vector<float>::const_iterator it = array->data.begin(); it != array->data.end(); ++it)
    {
      std::cout << "I was here1" << std::endl;
      arr[i] = *it;
      i++;
    }

    for(int i = 0; i < 5; ++i){
      std::cout << arr[i] << std::endl;
    }
  }
  else if(j == 1){
    for(std::vector<float>::const_iterator it = array->data.begin(); it != array->data.end(); ++it)
    {
      std::cout << "I was here2" << std::endl;
      arrK[i] = *it;
      i++;
    }

    for(int i = 0; i < 9; ++i){
      std::cout << arrK[i] << std::endl;
    }
  }
  ++j;

}
