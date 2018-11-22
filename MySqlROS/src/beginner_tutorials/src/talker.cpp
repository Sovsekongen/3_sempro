#include "ros/ros.h"
#include "std_msgs/String.h"
<<<<<<< HEAD
#include "beginner_tutorials/cvmsger.h"
#include "geometry_msgs/Pose.h"

=======
>>>>>>> 26a60b4c68b3a2a261a9b928b81953632d92833b

#include <sstream>

/**
 * This tutorial demonstrates simple sending of messages over the ROS system.
 */
<<<<<<< HEAD

// rosrun beginner_tutorials talker
=======
>>>>>>> 26a60b4c68b3a2a261a9b928b81953632d92833b
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
  ros::init(argc, argv, "cpptalker");

  /**
   * NodeHandle is the main access point to communications with the ROS system.
   * The first NodeHandle constructed will fully initialize this node, and the last
   * NodeHandle destructed will close down the node.
   */
  ros::NodeHandle n;

  /**
   * The advertise() function is how you tell ROS that you want to
   * publish on a given topic name. This invokes a call to the ROS
   * master node, which keeps a registry of who is publishing and who
   * is subscribing. After this advertise() call is made, the master
   * node will notify anyone who is trying to subscribe to this topic name,
   * and they will in turn negotiate a peer-to-peer connection with this
   * node.  advertise() returns a Publisher object which allows you to
   * publish messages on that topic through a call to publish().  Once
   * all copies of the returned Publisher object are destroyed, the topic
   * will be automatically unadvertised.
   *
   * The second parameter to advertise() is the size of the message queue
   * used for publishing messages.  If messages are published more quickly
   * than we can send them, the number here specifies how many messages to
   * buffer up before throwing some away.
   */
<<<<<<< HEAD
  ros::Publisher chatter_pub = n.advertise</*std_msgs::String*/beginner_tutorials::cvmsger>("chatter", 1000);

  ros::Rate loop_rate(1);


  int count = 0;
  while (ros::ok())
  {
    //msg message
    beginner_tutorials::cvmsger msg;
    geometry_msgs::Pose pose;
    pose.position.x = 217;
    pose.position.y = 583;
    pose.position.z = 10;
    pose.orientation.w = 1;
    msg.pose = pose;
    msg.radius = 300;
    msg.exeTime = 1889;
    msg.pic = "D:/Billeder/throwX.PNG";
    msg.color = "206cdf";
    msg.shape = "Sphere";



    //String message
//    std_msgs::String msg;

//    std::stringstream ss;

//    if(count == 0)
//      ss << "CV;322;167;12;300;206cdf;Square;D:/Billeder/throwX.PNG;";
//    if(count == 1)
//      ss << "UR;472;992;";

//    msg.data = ss.str();

   // ROS_INFO(msg.radius);


    ros::Duration(0.5).sleep();
=======
  ros::Publisher chatter_pub = n.advertise<std_msgs::String>("chatter", 1000);

  ros::Rate loop_rate(10);

  /**
   * A count of how many messages we have sent. This is used to create
   * a unique string for each message.
   */
  int count = 0;
  while (ros::ok())
  {
    /**
     * This is a message object. You stuff it with data, and then publish it.
     */
    std_msgs::String msg;

    std::stringstream ss;
    ss << "hello world bitchy boys " << count;
    msg.data = ss.str();

    ROS_INFO("%s", msg.data.c_str());

    /**
     * The publish() function is how you send messages. The parameter
     * is the message object. The type of this object must agree with the type
     * given as a template parameter to the advertise<>() call, as was done
     * in the constructor above.
     */
>>>>>>> 26a60b4c68b3a2a261a9b928b81953632d92833b
    chatter_pub.publish(msg);

    ros::spinOnce();

    loop_rate.sleep();
    ++count;
<<<<<<< HEAD

      if(count == 1)
      break;
=======
>>>>>>> 26a60b4c68b3a2a261a9b928b81953632d92833b
  }


  return 0;
}
