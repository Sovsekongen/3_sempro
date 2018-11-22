#include "ros/ros.h"
#include "std_msgs/String.h"
#include "beginner_tutorials/urmsg.h"
#include <iostream>
#include <sstream>

int main(int argc, char **argv)
{

  ros::init(argc, argv, "urtalker");


  ros::NodeHandle n;


  ros::Publisher chatter_pub = n.advertise<beginner_tutorials::urmsg>("chatter2", 1000);

  ros::Rate loop_rate(1);

  int count = 0;
  while (ros::ok())
  {

    //Custom message
    beginner_tutorials::urmsg msg;
    msg.pickUpTime = 327;
    msg.throwTime = 205;

//    // String message
//    std_msgs::String msg;
//    std::stringstream ss;
//    ss << "UR;489;291;" << count;
//    msg.data = ss.str();


    ros::Duration(0.5).sleep();
    chatter_pub.publish(msg);
    ROS_INFO("urtalker sent message");

    ros::spinOnce();

    loop_rate.sleep();
    ++count;
    if(count == 1)
      break;
  }


  return 0;
}
