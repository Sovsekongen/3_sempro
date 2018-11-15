#include <ros/ros.h>
#include <moveit/move_group_interface/move_group_interface.h>
#include <moveit/planning_scene_interface/planning_scene_interface.h>
#include <moveit_msgs/DisplayRobotState.h>
#include <moveit_msgs/DisplayTrajectory.h>

#include <string>
#include <vector>

int main(int argc, char **argv)
{
    ros::init(argc, argv, "plan_exe_hardcoded_traj");
    ros::NodeHandle nh;
    ros::AsyncSpinner spinner(1);
    spinner.start();
    ROS_INFO("Plan & execute hardcoded trajectory!");

    const std::string GROUP = "ur5_arm"; //move group name
    moveit::planning_interface::MoveGroupInterface move_group(GROUP); //the move-group object
    move_group.setNumPlanningAttempts(10);

    //move to standby then throw prime:
    move_group.setNamedTarget("standby");
    move_group.move();
    move_group.setNamedTarget("throw_prime");
    move_group.move();


    //Plan a path through waypoints
    //set the set start state to current:
    move_group.setStartStateToCurrentState();
    std::vector<geometry_msgs::Pose> waypts;
    //Create the waypoints starting with the current pose & add them to vector:
    geometry_msgs::Pose waypt = move_group.getCurrentPose().pose;
    //waypts.push_back(waypt);
    waypt.position.x = 0.0;
    waypt.position.y = -1.11183;
    waypt.position.z = 0.0743505;
    waypt.orientation.w = 0.000503911;
    waypts.push_back(waypt);
    waypt.position.x = 0.0204639;
    waypt.position.y = -0.89354;
    waypt.position.z = 0.661038;
    waypt.orientation.w = 0.000320343;
    waypts.push_back(waypt);
    waypt.position.x = 0.0199489;
    waypt.position.y = -0.267482;
    waypt.position.z = 0.861729;
    waypt.orientation.w = 0.000410967;
    waypts.push_back(waypt);
    waypt.position.x = 0.0546796;
    waypt.position.y = 0.326323;
    waypt.position.z = 0.571672;
    waypt.orientation.w = 0.0216073;
    waypts.push_back(waypt);
    waypt.position.x = 0.056855;
    waypt.position.y = 0.41545;
    waypt.position.z = 0.346689;
    waypt.orientation.w = 0.0206073;
    waypts.push_back(waypt);

    //start planning
    moveit::planning_interface::MoveGroupInterface::Plan thee_plan;
    moveit_msgs::RobotTrajectory trajectory;
    const double jump_threshold = 0.0001;
    const double eef_step = 0.01;
    double fraction = move_group.computeCartesianPath(waypts, eef_step, jump_threshold, trajectory);
    ROS_INFO_NAMED("tutorial", "Visualizing plan 4 (Cartesian path) (%.2f%% acheived)", fraction * 100.0);
    ros::Duration(5).sleep();
    thee_plan.trajectory_ = trajectory;
    //if (fraction == 1.0){
      //move_group.execute(thee_plan);
      ROS_INFO("Executing plan");
    //}
}
