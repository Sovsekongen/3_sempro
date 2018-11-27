#include "ros/ros.h"
#include "std_msgs/String.h"
#include "beginner_tutorials/cvmsger.h"
#include "geometry_msgs/Pose.h"


#include <sstream>


// rosrun beginner_tutorials talker
int main(int argc, char **argv)
{

  ros::init(argc, argv, "cpptalker");

  /**
   * NodeHandle is the main access point to communications with the ROS system.
   * The first NodeHandle constructed will fully initialize this node, and the last
   * NodeHandle destructed will close down the node.
   */
  ros::NodeHandle n;

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

    ros::spinOnce();

    loop_rate.sleep();
    ++count;

      if(count == 1)
      break;




  return 0;
}
