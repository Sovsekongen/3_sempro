#include "ros/ros.h"
#include "std_msgs/String.h"
<<<<<<< HEAD
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
=======

/**
 * This tutorial demonstrates simple receipt of messages over the ROS system.
 */
void chatterCallback(const std_msgs::String::ConstPtr& msg)
{
  ROS_INFO("I heard: [%s]", msg->data.c_str());
}

int main(int argc, char **argv)
{
  /**
   * The ros::init() function needs to see argc and argv so that it can perform
   * any ROS arguments and name remapping that were provided at the command line.
   * For programmatic remappings you can use a different version of init() which takes
   * remappings directly, but for most command-line programs, passing argc and argv is
   * the easiest way to do it.  The third argument to init() is the name of the node.
   *
   * You must call one of the versions of ros::init() before using any other
   * part of the ROS system.
   */
  ros::init(argc, argv, "listener");

  /**
   * NodeHandle is the main access point to communications with the ROS system.
   * The first NodeHandle constructed will fully initialize this node, and the last
   * NodeHandle destructed will close down the node.
   */
  ros::NodeHandle n;

  /**
   * The subscribe() call is how you tell ROS that you want to receive messages
   * on a given topic.  This invokes a call to the ROS
   * master node, which keeps a registry of who is publishing and who
   * is subscribing.  Messages are passed to a callback function, here
   * called chatterCallback.  subscribe() returns a Subscriber object that you
   * must hold on to until you want to unsubscribe.  When all copies of the Subscriber
   * object go out of scope, this callback will automatically be unsubscribed from
   * this topic.
   *
   * The second parameter to the subscribe() function is the size of the message
   * queue.  If messages are arriving faster than they are being processed, this
   * is the number of messages that will be buffered up before beginning to throw
   * away the oldest ones.
   */
  ros::Subscriber sub = n.subscribe("chatter", 1000, chatterCallback);

  /**
   * ros::spin() will enter a loop, pumping callbacks.  With this version, all
   * callbacks will be called from within this thread (the main one).  ros::spin()
   * will exit when Ctrl-C is pressed, or the node is shutdown by the master.
   */
  ros::spin();
>>>>>>> 26a60b4c68b3a2a261a9b928b81953632d92833b

  return 0;
}

