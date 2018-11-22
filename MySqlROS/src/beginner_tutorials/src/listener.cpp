#include "ros/ros.h"
#include "std_msgs/String.h"
#include "beginner_tutorials/cvmsger.h"
#include "beginner_tutorials/urmsg.h"
#include "geometry_msgs/Pose.h"
#include <sstream>
#include <iostream>

ros::Publisher talk;
ros::MultiThreadedSpinner spinner(3);
void chatterCallback(const beginner_tutorials::cvmsger::ConstPtr& msg)
{
  ROS_INFO("Message received from talker.cpp ", msg);

  //ros::Rate loop_rate(1);
  std_msgs::String msger;

  std::stringstream ss;
  ss << "CV;";
  ss << msg.get()->pose.position.x << ";";
  ss << msg.get()->pose.position.y << ";";
  ss << msg.get()->exeTime << ";";
  ss << msg.get()->radius << ";";
  ss << msg.get()->color << ";";
  ss << msg.get()->shape << ";";
  ss << msg.get()->pic << ";";

  msger.data = ss.str();

  ROS_INFO("%s", msger.data.c_str());
  ros::Duration(0.5).sleep();
  talk.publish(msger);

  spinner.spin();
  //ros::spinOnce();
  //loop_rate.sleep();

}

void chatterCallbackUR(const beginner_tutorials::urmsg::ConstPtr& msg)
{
  ROS_INFO("Message received from urtalker.cpp ", msg);

//  ros::Rate loop_rate(1);
  std_msgs::String msger;

  std::stringstream ss;
  ss << "UR;";
  ss << msg.get()->pickUpTime << ";";
  ss << msg.get()->throwTime << ";";

  msger.data = ss.str();

  ROS_INFO("%s", msger.data.c_str());
  ros::Duration(0.5).sleep();
  talk.publish(msger);

//  ros::spinOnce();
//  loop_rate.sleep();
  spinner.spin();
}

// rosrun beginner_tutorials listner
int main(int argc, char **argv)
{

  ros::init(argc, argv, "listener");

  ros::NodeHandle n;
  ros::NodeHandle t;
  ros::NodeHandle u;

  talk = t.advertise<std_msgs::String>("chatter1",1000);


  ros::Subscriber cvsub = n.subscribe("chatter", 1000, chatterCallback);

  ros::Subscriber ursub = u.subscribe("chatter2",1000,chatterCallbackUR);



  //ros::MultiThreadedSpinner spinner(3);


  spinner.spin();

  return 0;
}

