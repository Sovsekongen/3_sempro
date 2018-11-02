//Simple ROS node

#include <ros/ros.h>
#include <fake_ar_publisher/ARMarker.h>
#include <kappers_pakke/LocalizePart.h>
#include <tf/transform_listener.h>


//callback code
class Localizer{
public:
  Localizer(ros::NodeHandle nh)
  {
    //subscriber object
    ar_sub = nh.subscribe<fake_ar_publisher::ARMarker>("ar_pose_marker", 1, &Localizer::visionCallback, this);
    //                      topic type/return type       topic name           run this on sub

    //create service server object - the publisher
    server_ = nh.advertiseService("localize_part", &Localizer::localizePart, this);
    //                              service name       run this on call

  }

  void visionCallback(const fake_ar_publisher::ARMarkerConstPtr& msg){
    last_msg = msg;
//    ROS_INFO_STREAM(last_msg -> pose.pose);
  }

  //callback service function
  bool localizePart(kappers_pakke::LocalizePart::Request &req, kappers_pakke::LocalizePart::Response &res){
    fake_ar_publisher::ARMarkerConstPtr p = last_msg;
    ROS_INFO("pointer p updated");
    if (!p){
      ROS_ERROR("VISION_NODE - No pose received!");
      return false;
    }

    ROS_INFO("VISION_NODE - pose received");
    tf::Transform cam_to_target;
    tf::poseMsgToTF(p->pose.pose, cam_to_target);
    //tf listener object
    tf::StampedTransform req_to_cam;
    listener.lookupTransform(req.base_frame, p->header.frame_id, ros::Time(0), req_to_cam);

    tf::Transform req_to_target;
    req_to_target = req_to_cam * cam_to_target;
    tf::poseTFToMsg(req_to_target, res.pose);
    return true;
  }

  //members
  ros::Subscriber ar_sub;
  fake_ar_publisher::ARMarkerConstPtr last_msg;
  ros::ServiceServer server_;
  tf::TransformListener listener;
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
