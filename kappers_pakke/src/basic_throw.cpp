#include <ros/ros.h>
#include <string>
#include <moveit/move_group_interface/move_group_interface.h>
//#include <moveit/planning_interface/planning_interface.h>"
#include <tf/tf.h>
#include <vector>
#include <geometry_msgs/Pose.h>
#include <geometry_msgs/Point.h>
#include <geometry_msgs/Quaternion.h>

class Planner {
public:
  Planner(ros::NodeHandle &nh){

  }

  void do_throw(const std::string& base_frame, const std::vector<geometry_msgs::Pose>& poses){
      //object accosiated with manipulator moveit_config group
      moveit::planning_interface::MoveGroupInterface move_group("manipulator");
      move_group.setPoseReferenceFrame(base_frame);
      move_group.setPlannerId("RRTkConfigDefault"); //planning algorithm
      move_group.setNumPlanningAttempts(15);

      //go home
      move_group.setNamedTarget("home");
      move_group.move();
      ROS_INFO_STREAM("Moved home");

      //set start state of robot
      robot_state::RobotState start_state(*move_group.getCurrentState());
      start_state.setFromIK(move_group.getCurrentState()->getJointModelGroup("manipulator"), poses[0]);

      move_group.setPoseTarget(poses[0]);
      move_group.move();

      //Compute a trajectory
      move_group.setPlanningTime(10.0);
      moveit_msgs::RobotTrajectory traj;
      traj.joint_trajectory.header.frame_id = base_frame;
      const double jump_threshold = 0.0;
      const double eef_step = 0.01;
      double fraction = move_group.computeCartesianPath(poses,
                                                       eef_step,
                                                       jump_threshold,
                                                       traj);
      ROS_INFO_NAMED("Throw", "Visualizing plan 4 (Cartesian path) (%.2f%% acheived)", fraction * 100.0);
//      if (fraction == -1.0){
//        ROS_ERROR("PLANNING FAILED");
//        return;
//      }
//      ROS_INFO_STREAM("PLAN " << fraction << "% achieved");
//      ROS_INFO_STREAM("Traj: " << traj.joint_trajectory);

//      moveit::planning_interface::MoveGroupInterface::Plan plan();
//      plan.trajectory_ = traj;
//      plan.start_state_ = start_state;

//      move_group.execute(plan);

      //execute moves
//      for (int i = 1; i < poses.size(); i++){
//        ROS_INFO_STREAM("Moving to " << poses[i]);
//        move_group.setPoseTarget(poses[i]);
//        move_group.move();
//        ROS_INFO_STREAM("Reached Pose!");
//      }

      //go home

      /********************************/
      //Solution from https://groups.google.com/forum/#!topic/moveit-users/x5FwalM5ruk

      move_group.setNamedTarget("home");
      move_group.move();
      ROS_INFO_STREAM("Moved home");
  }
};

/* MAIN */

int main(int argc, char **argv)
{
  ros::init(argc, argv, "basic_throw");
  ros::AsyncSpinner a_spin(1); //moveit loop
  a_spin.start();

  ros::NodeHandle nh;
  ros::NodeHandle pvt_nh("~");

  std::string base_frame;
  pvt_nh.param<std::string>("base_frame", base_frame, "world");

  std::vector<geometry_msgs::Pose> poses;
  //Create poses:
  geometry_msgs::Pose target1;
  target1.position.x = 0.1;
  target1.position.y = 0.1;
  target1.position.z = 0.1;
  target1.orientation.w = 1;
  poses.push_back(target1);

  geometry_msgs::Pose target2;
  target2.position.x += 0.2;
  poses.push_back(target2);

  geometry_msgs::Pose target3;
  target2.position.y += 0.2;
  poses.push_back(target3);

  ROS_INFO("Basic_throw node started");
  ROS_INFO_STREAM("Ref Frame: " << base_frame);

  Planner plan(nh);
  ros::Duration(.5).sleep();

  plan.do_throw(base_frame, poses);
  ROS_INFO("throw complete");

  ros::waitForShutdown(); //with async_spinner
}
