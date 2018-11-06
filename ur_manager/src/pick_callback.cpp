/* TODO
 * TCP på model
 * Stik på model
 * TID!
 */

#include "ros/ros.h"
#include <moveit/move_group_interface/move_group_interface.h> //styring af robot
#include <tf/transform_listener.h> //til at transformere mellem frames
#include "ur_manager/ballPose.h"

#include "std_msgs/String.h"

//defined vars
#define TOPIC "poses"
#define WORLD_FRAME "world"
#define CAM_FRAME "camera_frame"
#define GRIPPER_OFFSET 0.19 //sørger for at gribberen IKKE kører ned i bordet
#define MOVE_GROUP "ur5_arm" //moveit navn for ur5 armen (uden gripper)
#define HOME "standby" //ur5_arm default pose

//DEBUG VAR
#define DEBUG false

class Picker{
public:
    Picker(ros::NodeHandle nh){
        //kør pick up for hver message på emnet
        //subscriber til TOPIC
        pose_sub = nh.subscribe<ur_manager::ballPose>(TOPIC, 1, &Picker::pickupCallback, this);
        //kun én messege bliver i køen

//        //TODO: close gripper

//        //returner til udgangspunkt
//        arm.setNamedTarget(HOME);
//        bool success
//                = (arm.plan(thee_plan) == moveit::planning_interface::MoveItErrorCode::SUCCESS);

//        ROS_INFO("SLEEPING"); //debug
//        ros::Duration(3).sleep(); //debug

//        if (success){
//            //arm.move();
//        }
    }

    //kører en pickup rutine
    void pickupCallback(const ur_manager::ballPoseConstPtr& msg)
    {

        last_pose = msg;
        ROS_INFO_STREAM("PICKER: Pose recieved: \n Frame_id: "
                        << last_pose->header.frame_id
                        << "\nPose: "
                        << last_pose->pose);

        if (last_pose != nullptr){ //a pose was recieved!
            //save radius for service call
            radius = last_pose->radius;

            //transform pose to world frame
            this->getPoseInWorld();

            //move
            this->moveToPickUp();
        } else {
            ROS_ERROR("PICKER - NO POSE!");
        }


    }

    //omregner pose fra kamera frame til world frame
    void getPoseInWorld(){
        tf::Transform cam_to_target;
        tf::poseMsgToTF(last_pose->pose, cam_to_target);
        //omregner object
        tf::StampedTransform req_to_cam;
        //find seneste transform i /tf emnet from cam_frame til world_frame
        listener.lookupTransform(WORLD_FRAME, CAM_FRAME, ros::Time(0), req_to_cam);

        //udregn transformationen af pose
        tf::Transform req_to_target;
        req_to_target = req_to_cam * cam_to_target; //klassisk matrice multiplikation

        //opdater cur_target
        tf::poseTFToMsg(req_to_target, cur_target);
        cur_target.position.z = GRIPPER_OFFSET;
        ROS_INFO_STREAM("PICKER: Pose in World frame:\n"
                        << cur_target);
        return;
    }

    bool readyToMove(){
        if (!last_pose)
            return false;

        ROS_INFO("PICKER: READY TO MOVE!");
        return true;
    }

    void moveToPickUp(/*moveit::planning_interface::MoveGroupInterface &group*/){
        //arm object
        moveit::planning_interface::MoveGroupInterface arm(MOVE_GROUP);
        arm.setNumPlanningAttempts(10);
        geometry_msgs::Pose approach = cur_target; //buffer mål
        approach.position.z += 0.2; //sæt buffer 20cm over target - for at konpensere for griber
        //læg mærke til at armen skal samle op lige ovenfra - dette begrænser rækkevide!


        do{
            arm.setPoseTarget(approach);
            bool success
                    = (arm.plan(thee_plan) == moveit::planning_interface::MoveItErrorCode::SUCCESS);

            ROS_DEBUG_STREAM(success);

            if (DEBUG){
            ROS_DEBUG("SLEEPING"); //debug
            ros::Duration(3).sleep(); //debug
            }

            if (success){
                ROS_INFO("PICKER - moving to approach!");
                arm.execute(thee_plan);
            } else {
                ROS_ERROR("UNABLE TO MOVE TO POSITION!");
                break;
            }

            arm.setPoseTarget(cur_target);
            success = (arm.plan(thee_plan) == moveit::planning_interface::MoveItErrorCode::SUCCESS);

            if (DEBUG){
                ROS_INFO("SLEEPING"); //debug
                ros::Duration(3).sleep(); //debug
            }

            if (success){
                ROS_INFO("PICKER - moving to grasp!");
                arm.execute(thee_plan);
            } else {
                ROS_ERROR("NO PLAN");
                break;
            }
        } while(false); //kør loop én gang, men muliggør break

        //return home
        arm.setNamedTarget(HOME);
        arm.move();
    }

private:
    tf::TransformListener listener;
    ros::Subscriber pose_sub;
    ur_manager::ballPoseConstPtr last_pose;
    geometry_msgs::Pose cur_target;
    double radius;

    //rviz debugger
    moveit::planning_interface::MoveGroupInterface::Plan thee_plan;
};


int main(int argc, char **argv)
{
    ros::init(argc, argv, "pick_callback");
    ros::NodeHandle nh;
    ros::AsyncSpinner spin(2);
    spin.start();

    ROS_INFO("Picker node launched");

    //create picker object - will run a callback
    Picker the_one_that_shall_pick_up(nh);


//    ros::spin();
    ros::waitForShutdown();
    return 0;
}
