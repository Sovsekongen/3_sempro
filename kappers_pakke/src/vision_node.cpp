//Simple ROS node

#include <ros/ros.h>
#include <fake_ar_publisher/ARMarker.h>
#include <kappers_pakke/LocalizePart.h>

//callback code
class Localizer{
public:
  Localizer(ros::NodeHandle nh)
  {
    ar_sub = nh.subscribe<fake_ar_publisher::ARMarker>("ar_pose_marker", 1, &Localizer::visionCallback, this);
    //create service server object
    server_ = nh.advertiseService("localize_part", &Localizer::localizePart, this);

  }

  void visionCallback(const fake_ar_publisher::ARMarkerConstPtr& msg){
    last_msg = msg;
    //ROS_INFO_STREAM(last_msg -> pose.pose);
  }

  //callback service
  bool localizePart(kappers_pakke::LocalizePart::Request &req, kappers_pakke::LocalizePart::Response &res){
    fake_ar_publisher::ARMarkerConstPtr p = last_msg;
    if (!p)
      return false;

    res.pose = p->pose.pose;
    return true;
  }

  //members
  ros::Subscriber ar_sub;
  fake_ar_publisher::ARMarkerConstPtr last_msg;
  ros::ServiceServer server_;
};

/*******************************/
int main(int argc, char *argv[])
{
  //first ros func to be called befreo anythin else
  ros::init(argc, argv, "vision_node");

  //ROS node handle
  ros::NodeHandle nh;

  //subscribing to fake_ar
  Localizer localizer(nh);


  //a logging method
  ROS_INFO("Hello kasper you clever man!");

  //loop program
  ros::spin();

}
