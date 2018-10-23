#include <ros/ros.h>
#include <moveit/move_group_interface/move_group_interface.h>
#include <moveit/planning_scene_interface/planning_scene_interface.h>
#include <moveit_msgs/DisplayRobotState.h>
#include <moveit_msgs/DisplayTrajectory.h>

#include <string>
#include <vector>

int main(int argc, char **argv)
{
  ros::init(argc, argv, "basic_throw_2");
  ros::NodeHandle nh;
  ros::AsyncSpinner spin(1);
  spin.start();
  ROS_INFO("Basic_throw_node launched");

  const std::string GROUP = "manipulator"; //move group name
  moveit::planning_interface::MoveGroupInterface move_group(GROUP); //the move-group object
  moveit::planning_interface::PlanningSceneInterface planning_scene_interface; //can manipulate the scene

  const robot_state::JointModelGroup *joint_model_group =
      move_group.getCurrentState()->getJointModelGroup(GROUP);

  //print the name of the reference frame for this robot.
  ROS_INFO_NAMED("tutorial", "Reference frame: %s", move_group.getPlanningFrame().c_str());

  //print the name of the end-effector link for this group.
  ROS_INFO_NAMED("tutorial", "End effector link: %s", move_group.getEndEffectorLink().c_str());

  //Plan to move based on position
  geometry_msgs::Pose target_pose1;
  target_pose1.orientation.w = 1.0;
  target_pose1.position.x = 0.3;
  target_pose1.position.y = 0.3;
  target_pose1.position.z = 1;
  move_group.setPoseTarget(target_pose1);

  // Now, we call the planner to compute the plan and visualize it.
  // Note that we are just planning, not asking move_group
  // to actually move the robot.
  moveit::planning_interface::MoveGroupInterface::Plan my_plan;
  bool success = (move_group.plan(my_plan) == moveit::planning_interface::MoveItErrorCode::SUCCESS);
  ROS_INFO_NAMED("tutorial", "Visualizing plan 1 (pose goal) %s", success ? "" : "FAILED");
//  move_group.execute(my_plan);
//  ROS_INFO("Execution done!");

  //Plan a move in joint-space
  //create an pointer that references the current robot's state.
  // RobotState is the object that contains all the current position/velocity/acceleration data.
  moveit::core::RobotStatePtr current_state = move_group.getCurrentState();
  //Retrieve current joint values:
  std::vector<double> joint_positions;
  //copy them from state
  current_state->copyJointGroupPositions(joint_model_group, joint_positions);
//  ROS_INFO_STREAM("current joint states: " << joint_positions);
  //modify the joint_positions to create goal
  joint_positions[0] = -1.0; //in rads
//  ROS_INFO_STREAM("current joint states: " << joint_positions);
  move_group.setJointValueTarget(joint_positions);

  success = (move_group.plan(my_plan) == moveit::planning_interface::MoveItErrorCode::SUCCESS);
  ROS_INFO_NAMED("tutorial", "Visualizing plan 2 (pose goal) %s", success ? "" : "FAILED");
  move_group.execute(my_plan);
  ROS_INFO("Execution done!");
}
