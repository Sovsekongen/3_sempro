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
//  ROS_INFO_NAMED("tutorial", "Visualizing plan 1 (pose goal) %s", success ? "" : "FAILED");
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
//  ROS_INFO_NAMED("tutorial", "Visualizing plan 2 (pose goal) %s", success ? "" : "FAILED");
//  move_group.execute(my_plan);
//  ROS_INFO("Execution done!");

  //TODO!!!
  //moving with path constraints
  //create some constraint for a link:
  moveit_msgs::OrientationConstraint ocm;
  ocm.link_name = "upper_arm_link";
  robot_state::RobotState start_state(*move_group.getCurrentState()); //the current state of move_group (the robot)

  //Plan a path through waypoints
  //set the set start state to current:
  move_group.setStartStateToCurrentState();
  std::vector<geometry_msgs::Pose> waypts;
  //Create the waypoints starting with the current pose & add them to vector:
  geometry_msgs::Pose target3 = move_group.getCurrentPose().pose;
  waypts.push_back(target3);
  //target3 just gets updated to create a triangle like move
  target3.position.z -= 0.1; //move 20cm down
  waypts.push_back(target3);
  target3.position.y += 0.1; //move 20cm along y
  waypts.push_back(target3);
  target3.position.z += 0.1; //move 20cm up
  target3.position.y -= 0.1; //move 20cm back on y
  waypts.push_back(target3);

  move_group.setMaxVelocityScalingFactor(2); //set caling to max-vel to make approaches more safe

  // We want the Cartesian path to be interpolated at a resolution of 1 cm
  // which is why we will specify 0.01 as the max step in Cartesian
  // translation.  We will specify the jump threshold as 0.0, effectively disabling it.
  // Warning - disabling the jump threshold while operating real hardware can cause
  // large unpredictable motions of redundant joints and could be a safety issue
  moveit_msgs::RobotTrajectory trajectory;
  const double jump_threshold = 0.0;
  const double eef_step = 0.01;
  double fraction = move_group.computeCartesianPath(waypts, eef_step, jump_threshold, trajectory);
  ROS_INFO_NAMED("tutorial", "Visualizing plan 4 (Cartesian path) (%.2f%% acheived)", fraction * 100.0);
  ros::Duration(5).sleep();
  my_plan.trajectory_ = trajectory;
  if (fraction == 1.0){
    move_group.execute(my_plan);
    ROS_INFO("Executing plan");
  }

  std::system("echo I did something!");
}
