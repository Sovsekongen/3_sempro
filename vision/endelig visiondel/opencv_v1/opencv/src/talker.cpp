#include <iostream>
#include "ros/ros.h"
#include "std_msgs/String.h"
#include "std_msgs/Int8.h"
#include "geometry_msgs/Pose.h"
#include "opencv/ballPose.h"
#include <opencv2/opencv.hpp>


using namespace cv;


static const uint32_t c_countOfImagesToGrab = 100;

int main(int argc, char **argv)
{


  // Create a VideoCapture object and open the input file
  // If the input is the web camera, pass 0 instead of the video file name
  VideoCapture vCap(0);
  Mat frame;
  Mat frame_gray;

  // Check if camera opened successfully
  if(!vCap.isOpened()){
    std::cout << "Error opening video stream or file" << std::endl;
    return -1;
  }

  //initere din node, og kalder den talker, hvilket gør at ingen andre noder i dit system kan hedde talker
  ros::init(argc, argv, "talker");
  ros::NodeHandle nh;

  //fortæller at vi publisher på topicet chatter og hvilken data type vi publisher
  ros::Publisher chatter_pub = nh.advertise<opencv::ballPose>("chatter", 1000);
  opencv::ballPose pose;
  geometry_msgs::Pose ballPose;
  //loop rate
  ros::Rate loop_rate(10);

  //er ok så længe din talker kører
  while (ros::ok())
  {

    vCap >> frame;

    //konvertere til grayscale og fjerner støj
    cvtColor(frame, frame_gray, CV_BGR2GRAY);
    GaussianBlur(frame_gray,frame_gray, Size(9,9),2,2);
    //tegner cirkler hvis der er nogen
    std::vector<Vec3f> circleTest;
    HoughCircles(frame_gray, circleTest,CV_HOUGH_GRADIENT, 1, frame_gray.rows/8, 50, 100,0,0);

    if(circleTest.size() > 0){
      int radius;
      int x;
      int y;
        for(size_t i = 0; i < circleTest.size(); i++){
          x = cvRound(circleTest[i][0]);
          y = cvRound(circleTest[i][1]);
          radius = cvRound(circleTest[i][2]);
        }
      //læser radius, x og y kordinatter ned i en string stream
        ballPose.position.x = x;
        ballPose.position.y = y;
        ballPose.position.z = 10;
        ballPose.orientation.w = 1;



      //publisher
      pose.pose = ballPose;
      pose.radius = radius;

      chatter_pub.publish(pose);
    }

    ros::spinOnce();

    loop_rate.sleep();

  }

  return 0;
}
