#include "ros/ros.h"
#include "std_msgs/String.h"
#include "geometry_msgs/Pose.h"
#include "opencv/ballPose.h"

void chatterCallback(const opencv::ballPose::ConstPtr& msg)
{
  float radius, x, y;
  float time;
  radius = msg.get()->radius;
  x = msg.get()->pose.position.x;
  y = msg.get()->pose.position.y;
  time = msg.get()->timer;
  std::cout << "radius " << radius << " x " << x << " y " << y << " time " << time << std::endl;
}

int main(int argc, char **argv)
{
  ros::init(argc, argv, "ballPosition");
  ros::NodeHandle nh;

  ros::Subscriber sub = nh.subscribe("poses", 1000, chatterCallback);

  ros::spin();

  return 0;
}
