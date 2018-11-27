#include "ros/ros.h"
#include "std_msgs/String.h"
#include "geometry_msgs/Pose.h"
#include "cppcon/cvmsg.h"
#include "cppcon/urmsg.h"
#include <sstream>
#include <iostream>
#include "conn.h"
#include "ctime"

//ros::AsyncSpinner spinner(3);

Conn c;

bool receivedCV = false;

void chatterCallback(const cppcon::cvmsg::ConstPtr& msg)
{
  if(!receivedCV)
  {
    std::cout << "DAB" << std::endl;
    std::time_t now = std::time(0);
    std::tm * ptm = std::localtime(&now);
    char buffer[32];
    // Format: 2018-11-27 20:20:00
    std::strftime(buffer, 32, "%Y.%m.%d %H:%M:%S", ptm);
    std::stringstream ss;
    ss << buffer;

    c.insertFromVision(ss.str(), msg.get()->pose.position.x,
                       msg.get()->pose.position.y, msg.get()->exeTime, msg.get()->radius,
                       msg.get()->color, msg.get()->shape, msg.get()->pic);
    ros::Duration(0.5).sleep();
    receivedCV = true;
  }
}

void chatterCallbackUR(const cppcon::urmsg::ConstPtr& msg)
{
  if(receivedCV)
  {
    std::cout << "DAB" << std::endl;
    c.insertFromUR(msg.get()->pickUpTime, msg.get()->throwTime);

    ros::Duration(0.5).sleep();
  }

  receivedCV = false;
}

// rosrun beginner_tutorials listner
int main(int argc, char **argv)
{
  ros::init(argc, argv, "listener");
  std::cout << " YO FAM " << std::endl;

  ros::NodeHandle n;
  ros::NodeHandle u;

  ros::Subscriber cvsub = n.subscribe("chatter", 1000, chatterCallback);
  ros::Subscriber ursub = u.subscribe("chatter2", 1000, chatterCallbackUR);

  ros::spin();
}
