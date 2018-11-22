#include "ros/ros.h"
#include "std_msgs/String.h"
#include "geometry_msgs/Pose.h"
#include "opencv/ballPose.h"

void chatterCallback(const opencv::ballPose::ConstPtr& msg)
{
  int radius, x, y;
  radius = msg.get()->radius;
  x = msg.get()->pose.position.x;
  y = msg.get()->pose.position.y;
  std::cout << "radius " << radius << " x " << x << " y " << y << std::endl;
}

int main(int argc, char **argv)
{
  ros::init(argc, argv, "ballPosition");
  ros::NodeHandle nh;

  ros::Subscriber sub = nh.subscribe("chatter", 1000, chatterCallback);

  ros::spin();

  return 0;
}
