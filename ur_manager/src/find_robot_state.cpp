#include <ros/ros.h>
#include <moveit/move_group_interface/move_group_interface.h>

#include <string>
#include <vector>
#include <iostream>
#include <fstream>

/*
 * Used for moving the robot a
*/

static const std::string save_path = "positions.txt";

int main(int argc, char **argv)
{
    ros::init(argc, argv, "find_robot_state");
    ros::NodeHandle nh;
    ros::AsyncSpinner spinner(1);
    spinner.start();
    ROS_INFO("ur_manager Calibration tool launched");

    /* VARS */
    const std::string ur5_group = "ur5_arm";
    moveit::planning_interface::MoveGroupInterface ur5(ur5_group);
    const robot_state::JointModelGroup *ur5_joint_model_group =
            ur5.getCurrentState()->getJointModelGroup(ur5_group);
    geometry_msgs::Pose pose;
    pose = ur5.getCurrentPose().pose;
    ROS_INFO_STREAM("Current pose: " << pose);

    //vars for saving found points
    std::vector<std::vector<double>> joint_positions;
    std::vector<double> joint_position(6);
    std::vector<geometry_msgs::Pose> poses;

//    system("read"); //wait via shell command

    while (true){
        std::cout << "Move Robot to next position!\n";

        //ask for input - are there more points?
        std::string input;
        std::cout << "continue [enter] or quit ['q' + enter]";
        std::getline(std::cin, input);
        if (input.compare("q") == 0)
            break;

        moveit::core::RobotStatePtr cur_state = ur5.getCurrentState();
        cur_state->copyJointGroupPositions(ur5_joint_model_group, joint_position);
        joint_positions.push_back(joint_position);
        pose = ur5.getCurrentPose().pose;
        poses.push_back(pose);
        ROS_INFO_STREAM("Captured pose: " << pose);
        int i = 0;
        for (int i = 0; i < joint_position.size(); i++){
            ROS_INFO_STREAM("Joint[" << i << "]: " << joint_position[i]);
        }
    }

    std::ofstream file(save_path);
    //fill with found data
    file << std::left
         << std::setw(8) << "pose #"
         << std::setw(12) << "pan_joint"
         << std::setw(12) << "lift_joint"
         << std::setw(15) << "elbow_joint"
         << std::setw(14) << "wri_1_joint"
         << std::setw(14) << "wri_2_joint"
         << std::setw(14) << "wri_3_joint"
         << std::setw(12) << "pose pos.x"
         << std::setw(12) << "pose pos.y"
         << std::setw(12) << "pose pos.z"
         << std::setw(12) << "pose ori.w"
         << std::endl;

    for (int i = 0; i < joint_positions.size(); i++){
        file << std::left
             << std::setw(8) << (i+1)
             << std::setw(12) << joint_positions[i][0]
             << std::setw(12) << joint_positions[i][1]
             << std::setw(15) << joint_positions[i][2]
             << std::setw(14) << joint_positions[i][3]
             << std::setw(14) << joint_positions[i][4]
             << std::setw(14) << joint_positions[i][5]
             << std::setw(12) << poses[i].position.x
             << std::setw(12) << poses[i].position.y
             << std::setw(12) << poses[i].position.z
             << std::setw(12) << poses[i].orientation.w
             << std::endl;

    }

    ROS_INFO_STREAM("Done finding poses - list can be found at: ~/" << save_path);
}
