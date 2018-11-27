/* TODO
 * make pick service
 * put everyting into a class
 * make clients for the wsg services
 */

#include "ros/ros.h"
#include "ur_manager/move.h" //service file
#include <std_srvs/Empty.h> //for services that are called without input or output
#include <moveit/move_group_interface/move_group_interface.h> //styring af robot

#define MOVE_SRV "move_arm"
#define HOME_SRV "home_arm"
#define PRIME_SRV "prime_arm"
#define MOVE_GROUP "ur5_arm" //moveit navn for ur5 armen (uden gripper)
#define HOME "standby" //ur5_arm default pose
#define PRIME "throw_prime"

bool move_arm(geometry_msgs::Pose target, double scalingFactor){ //move arm to a target
    moveit::planning_interface::MoveGroupInterface arm(MOVE_GROUP); // Der skal sættes en scalingfaktor på arm.
    moveit::planning_interface::MoveGroupInterface::Plan thee_plan;

    ROS_INFO_STREAM("UR_SERVICE: arm_ref_frame: " << arm.getPoseReferenceFrame());

    if (0.0 < scalingFactor && scalingFactor <= 1.0) {
      arm.setMaxVelocityScalingFactor(scalingFactor);
    }
    else {
      ROS_WARN("UR_SERVICE: Error setting maximum velocity for robot joints. The specified value is either 0, lower than 0, or higher than 1.");
    }
    arm.setNumPlanningAttempts(10);
    arm.setPoseTarget(target);
    arm.setStartStateToCurrentState();

    bool success
            = (arm.plan(thee_plan) == moveit::planning_interface::MoveItErrorCode::SUCCESS);

    if (success){
        ROS_INFO("UR_SERVICE: Moving arm");
        arm.execute(thee_plan);
        return true;
    } else {
        ROS_ERROR("UR_SERVICE: Could not plan move!");
        return false;
    }
}

bool move_arm_srv(ur_manager::move::Request &req, ur_manager::move::Response &res){ //move to requested position
    bool success = move_arm(req.pose, req.scalingFactor); //Move_arm skal også have scalingfactor med som argument.
    if (success){
        return true;
    } else {
        res.error = 255;
        return false;
    }
}

bool home_arm_srv(std_srvs::Empty::Request &req, std_srvs::Empty::Request &res){ //move arm to "standby"
    moveit::planning_interface::MoveGroupInterface arm(MOVE_GROUP);
    moveit::planning_interface::MoveGroupInterface::Plan thee_plan;
    arm.setNumPlanningAttempts(10);
    arm.setNamedTarget(HOME);
    arm.setStartStateToCurrentState();

    bool success
            = (arm.plan(thee_plan) == moveit::planning_interface::MoveItErrorCode::SUCCESS);

    if (success){
        ROS_INFO("UR_SERVICE: Moving arm");
        arm.execute(thee_plan);
        return true;
    } else {
        ROS_ERROR("UR_SERVICE: Could not plan move!");
        return false;
    }
}

bool prime_arm_srv(std_srvs::Empty::Request &req, std_srvs::Empty::Request &res){ //move arm to "standby"
    moveit::planning_interface::MoveGroupInterface arm(MOVE_GROUP);
    moveit::planning_interface::MoveGroupInterface::Plan thee_plan;
    arm.setNumPlanningAttempts(10);
    arm.setNamedTarget(PRIME);
    arm.setStartStateToCurrentState();

    bool success
            = (arm.plan(thee_plan) == moveit::planning_interface::MoveItErrorCode::SUCCESS);

    if (success){
        ROS_INFO("UR_SERVICE: Moving arm");
        arm.execute(thee_plan);
        return true;
    } else {
        ROS_ERROR("UR_SERVICE: Could not plan move!");
        return false;
    }
}

bool pick_srv(){ //pick object with wsg gripper - will make an approach
    return true;
}

int main(int argc, char **argv)
{
    ros::init(argc, argv, "ur_service");
    ros::NodeHandle nh("~");
    ros::AsyncSpinner spin(2);
    spin.start();

    ROS_INFO("UR_services available now!");

    ros::ServiceServer move_armSS, home_armSS, prime_armSS;

    move_armSS = nh.advertiseService(MOVE_SRV, move_arm_srv);
    home_armSS = nh.advertiseService(HOME_SRV, home_arm_srv);
    prime_armSS = nh.advertiseService(PRIME_SRV, prime_arm_srv);

    //ros::spin();
    ros::waitForShutdown();
    return 0;
}
