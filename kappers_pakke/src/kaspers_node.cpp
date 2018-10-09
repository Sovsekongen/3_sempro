#include <ros/ros.h>
#include <kappers_pakke/LocalizePart.h>
#include <string>
#include <tf/tf.h>
#include <moveit/move_group_interface/move_group_interface.h>
#include <cmath>

class ScanNPlan {
public:
    ScanNPlan(ros::NodeHandle &nh){
        //client to specific service
        vision_client = nh.serviceClient<kappers_pakke::LocalizePart>("localize_part");
        //                                                              service name
    }

    void start(const std::string& base_frame){
//      ROS_INFO("localizing part");
      kappers_pakke::LocalizePart srv;
      srv.request.base_frame = base_frame;
      ROS_INFO_STREAM("Requesting pose in frame: " << base_frame);

      if (!vision_client.call(srv)){ //call service - save in srv var
        ROS_ERROR("Could not localize!");
        return;
      }

      ROS_INFO_STREAM("Part localized: " << srv.response);

      geometry_msgs::Pose move_target = srv.response.pose;

      //object accosiated with manipulator moveit_config group
      moveit::planning_interface::MoveGroupInterface move_group("manipulator");

      //plan moves!
      move_group.setPoseReferenceFrame(base_frame); //set ref frame
      move_group.setPlannerId("RRTkConfigDefault"); //planning algorithm
      move_group.setNumPlanningAttempts(15);

      //set target manual
      geometry_msgs::Pose approach;
      approach.position = move_target.position;
      approach.orientation = move_target.orientation;
      approach.position.x += 0.1;
      move_group.setPoseTarget(approach);
      move_group.move(); //execute move

      move_group.setPoseTarget(move_target); //set target from service
      move_group.move();
      ROS_INFO_STREAM("Moved to" << srv.response.pose);

      move_group.setNamedTarget("home"); //move to a known configuration from moveit config file
      move_group.move();
      ROS_INFO_STREAM("Moved to home");
      return;
    }

private:
    ros::ServiceClient vision_client;
};

/**************************/
int main(int argc, char **argv)
{
  ros::init(argc, argv, "kaspers_node");

  //loop that works with movit
  ros::AsyncSpinner async_spinner(1);
  async_spinner.start();

  ros::NodeHandle nh;

  ros::NodeHandle pvt_nh("~");
  std::string base_frame;
  pvt_nh.param<std::string>("base_frame", base_frame, "world");
  //                         param name   str obj ref  def val

  ROS_INFO("Kaspers node has been initialized!");

  ScanNPlan app(nh);

  //wait for app to initialize (sleep)
  ros::Duration(.8).sleep();

  app.start(base_frame);
  ROS_INFO("app done");

  //keep looping
//  ros::spin();
  ros::waitForShutdown(); //used by async spinner - for moveit!
}
