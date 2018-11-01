#include <ros/ros.h>
#include <moveit/move_group_interface/move_group_interface.h>
#include <moveit/planning_scene_interface/planning_scene_interface.h>
#include <moveit_msgs/DisplayRobotState.h>
#include <moveit_msgs/DisplayTrajectory.h>

#include <string>
#include <vector>
#include <iostream>


moveit::planning_interface::MoveGroupInterface::Plan speed_up_plan(
        moveit::planning_interface::MoveGroupInterface::Plan &plan, double scale);

int main(int argc, char **argv)
{
    ros::init(argc, argv, "plan_exe_hardcoded_traj");
    ros::NodeHandle nh;
    ros::AsyncSpinner spinner(1);
    spinner.start();
    ROS_INFO("Plan & execute hardcoded joint values!");

    const std::string GROUP = "ur5_arm"; //move group name
    moveit::planning_interface::MoveGroupInterface move_group(GROUP); //the move-group object
    const robot_state::JointModelGroup *joint_model_group =
        move_group.getCurrentState()->getJointModelGroup(GROUP);
    move_group.setNumPlanningAttempts(10);

    moveit::planning_interface::MoveGroupInterface::Plan my_plan;

    //move to standby then throw prime:
    move_group.setNamedTarget("standby");
    move_group.move();
    move_group.setNamedTarget("throw_prime");
    move_group.move();

    //Plan a move in joint-space
    //create an pointer that references the current robot's state.
    // RobotState is the object that contains all the current position/velocity/acceleration data.
    moveit::core::RobotStatePtr current_state = move_group.getCurrentState();
    //Vector containing joint positions [rad]
    std::vector<double> joint_positions;
     current_state->copyJointGroupPositions(joint_model_group, joint_positions);
    //set the positions:
//    joint_positions[0] = -1.53632;
    joint_positions[1] = -0.8;
    joint_positions[2] = 0.64;
    joint_positions[3] = -0.72;
    joint_positions[4] =1.56643;
    joint_positions[5] = 0;
  //  ROS_INFO_STREAM("current joint states: " << joint_positions);
    move_group.setJointValueTarget(joint_positions);
    bool success = (move_group.plan(my_plan) == moveit::planning_interface::MoveItErrorCode::SUCCESS);

    //attempt to speed up things:
    if (success){
        double speed = 1.1;
        moveit::planning_interface::MoveGroupInterface::Plan fast_plan = speed_up_plan(my_plan, speed);

        //some debug:
        std::cout << "my_plan time from start: "
                  << my_plan.trajectory_.joint_trajectory.points[1].time_from_start.sec
                  << std::endl;
        std::cout << "my_plan time from start: "
                  << fast_plan.trajectory_.joint_trajectory.points[1].time_from_start.sec
                  << std::endl;

        ROS_INFO("excuting fast plan!!!!");
        ros::Duration(3).sleep();
//        move_group.setMaxVelocityScalingFactor(0.1);
//        move_group.setMaxAccelerationScalingFactor(0.1);
        move_group.execute(fast_plan);
    }

//    joint_positions[1] = -0.5;
//    move_group.setJointValueTarget(joint_positions);
//    success = (move_group.plan(my_plan) == moveit::planning_interface::MoveItErrorCode::SUCCESS);
//    move_group.execute(my_plan);
}

moveit::planning_interface::MoveGroupInterface::Plan speed_up_plan(
        moveit::planning_interface::MoveGroupInterface::Plan &plan, double scale){
    moveit::planning_interface::MoveGroupInterface::Plan new_plan;
    new_plan.trajectory_.joint_trajectory = plan.trajectory_.joint_trajectory;
    int num_joints = new_plan.trajectory_.joint_trajectory.joint_names.size();
    int num_points = new_plan.trajectory_.joint_trajectory.points.size();

    //scale points
    for (int i = 0; i < num_points; i++){
        new_plan.trajectory_.joint_trajectory.points[i].time_from_start.sec =
                new_plan.trajectory_.joint_trajectory.points[i].time_from_start.toSec() / scale;
        for (int j = 0; j < num_joints; j++){
            new_plan.trajectory_.joint_trajectory.points[i].velocities[j] *= scale;

            new_plan.trajectory_.joint_trajectory.points[i].accelerations[j] *= scale;

            new_plan.trajectory_.joint_trajectory.points[i].positions[j] =
                    plan.trajectory_.joint_trajectory.points[i].positions[j];
        }
    }

    return new_plan;
}
