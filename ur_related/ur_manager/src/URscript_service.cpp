/*TODO
 * All in ur script
 * move service joint space
 */

/*
 * NOTE!!!
 * NO SAFETY REGARDING POSITION OF GRIPPER
 */

//c++ includes
#include <sstream>
#include <cmath>

//ROS includes
#include "ros/ros.h"
#include <std_msgs/String.h> //for URscript topic
#include <geometry_msgs/Pose.h>
#include <tf/transform_listener.h> //for transformation between frames

//service files
#include "ur_manager/move.h"
#include <std_srvs/Empty.h>

//names/defines
#define MOVE_ARM_JOINT_SRV "joint_move"
#define MOVE_ARM_CART_SRV "cart_move"

#define SCRIPT_TOPIC "/ur_driver/URScript"

#define BASE_FRAME "base" //"frame does not exist

#define Z_OFFSET 0.01

//How to set the tcp on robot 19cm away from tool_0:
// set_tcp(p[0,0,0.19,0,0,0])

//buffer class that make service able to publish to topic
class Script_srv{
public:
    Script_srv(ros::NodeHandle nh){
        //advertise services
        cart_moveSS = nh.advertiseService(MOVE_ARM_CART_SRV, &Script_srv::cart_move_srv, this);
        joint_moveSS = nh.advertiseService(MOVE_ARM_JOINT_SRV, &Script_srv::joint_move_srv, this);

        //tell publisher wich topic to publish on
        script_pub = nh.advertise<std_msgs::String>(SCRIPT_TOPIC, 1);
    }

    bool cart_move_srv(ur_manager::move::Request &req, ur_manager::move::Response &res){
        geometry_msgs::Pose target_pose;
        //ROS_INFO_STREAM("Script_service: requested pose: " << req); //debug stream
        if (!(req.frame_id == BASE_FRAME)){
            target_pose = getPoseInRobotBase(req);
        } else {
            target_pose = req.pose;
        }

        //make sure tcp is configured: //DOES NOT WORK!!!!
        msg.data = "set_tcp(p[0,0,0.19,0,0,0])";
        script_pub.publish(msg);

        ROS_INFO_STREAM("Script_service: target pose: " << target_pose); //debug stream

        std::stringstream script; //buffer for URscript msg

        //orientation in UR format
        double rx = M_PI;
        double ry = 0;
        double rz = 0;

        //create script
        script << "movej(p["
           << target_pose.position.x << "," //x-coor
           << target_pose.position.y << "," //y-coor
           << Z_OFFSET << "," //z-coor
           << rx << ","
           << ry << ","
           << rz
           <<"])";

        msg.data = script.str();

        ROS_INFO_STREAM("URscript_service - msg: " << msg.data);

        script_pub.publish(msg); //publish script to controller

        return true;
    }

    bool joint_move_srv(std_srvs::Empty::Request &req, std_srvs::Empty::Request &res){
        return true;
    }

    geometry_msgs::Pose getPoseInRobotBase(const ur_manager::move::Request &req){

        tf::Transform req_to_target; //the transform of pose in req.frame_id
        tf::poseMsgToTF(req.pose, req_to_target); //get as tf

        //get the latest tf from req.frame_if to base of robot (always the same though)
        tf::StampedTransform tf_req_to_base;
        listener.lookupTransform(BASE_FRAME, req.frame_id, ros::Time(0), tf_req_to_base);

        //transform target to base frame
        tf::Transform base_to_target;
        base_to_target = tf_req_to_base * req_to_target;

        //save pose
        geometry_msgs::Pose target;
        tf::poseTFToMsg(base_to_target, target);

        return target;
    }

private:
    ros::Publisher script_pub; //publisher
    std_msgs::String msg; //string to be publishd to ur_script topic
    ros::ServiceServer joint_moveSS, cart_moveSS; //service objects
    tf::TransformListener listener; //transform listener
};

int main(int argc, char **argv)
{
    ros::init(argc, argv, "URscript_service");
    ros::NodeHandle nh("~");
//    ros::AsyncSpinner spinner(2);
//    spinner.start();

    ROS_INFO("Script service launched");

    Script_srv srv(nh);

    ros::spin();
//    ros::waitForShutdown();
}
