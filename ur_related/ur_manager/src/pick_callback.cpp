/* TODO
 *
 */

#include "ros/ros.h"
#include <moveit/move_group_interface/move_group_interface.h> //styring af robot
#include <tf/transform_listener.h> //til at transformere mellem frames
//#include "opencv/ballPose.h" // topic [TIL Peter]
#include "ur_manager/ballPose.h" // [til Kasper]
#include <chrono>
#include <iostream>

//services
#include "ur_manager/move.h"
#include <std_srvs/Empty.h>
#include "wsg_50_common/Move.h"

//msgs
#include <std_msgs/String.h>
#include <std_msgs/Empty.h>

//Robot times topic
#define TIME_TOPIC "robot_times"
#include "ur_manager/robot_time.h"

//defined vars for control
#define POSE_TOPIC "poses" //where ball-poses are published
#define PICK_UP_STATE_TOPIC "pickup_state"
#define WORLD_FRAME "world"
#define CAM_FRAME "camera_frame"
#define GRIPPER_OFFSET 0.05 //sørger for at gribberen IKKE kører ned i bordet
#define MOVE_GROUP "ur5_arm" //moveit navn for ur5 armen (uden gripper)
#define HOME "standby" //ur5_arm default pose

//defined service names
#define MOVE_SRV "/ur_service_node/move_arm"
#define HOME_SRV "/ur_service_node/home_arm"
#define PRIME_SRV "/ur_service_node/prime_arm"
#define GRIP_SRV "/wsg_50_driver/grasp"
#define RELEASE_SRV "/wsg_50_driver/release"
#define THROW_SRV "/ur_script_service_node/throw_v1"
#define JOINT_STATE_SRV "joint_state_service"

//DEBUG VAR
#define DEBUG false

class Picker{
public:
    Picker(ros::NodeHandle &nh){
        //kør pick up for hver message på emnet

        //services
        ur_home_cli = nh.serviceClient<std_srvs::Empty>(HOME_SRV);
        ur_move_cli = nh.serviceClient<ur_manager::move>(MOVE_SRV);
        ur_prime_cli = nh.serviceClient<std_srvs::Empty>(PRIME_SRV);
        wsg_grip_cli = nh.serviceClient<wsg_50_common::Move>(GRIP_SRV);
        wsg_release_cli = nh.serviceClient<wsg_50_common::Move>(RELEASE_SRV);
        throw_cli = nh.serviceClient<std_srvs::Empty>(THROW_SRV);
        state_cli = nh.serviceClient<std_srvs::Empty>(JOINT_STATE_SRV);

        //subscriber til TOPIC
//        pose_sub = nh.subscribe<opencv::ballPose>(POSE_TOPIC, 1, &Picker::pickupCallback, this); //[til Peter]
        pose_sub = nh.subscribe<ur_manager::ballPose>(POSE_TOPIC, 1, &Picker::pickupCallback, this); //[til Kasper]
        //kun én messege bliver i køen

        //state publisher - use to trigger camera processing
        pickup_state_pub = nh.advertise<std_msgs::Empty>(PICK_UP_STATE_TOPIC, 1);

        //time publisher
        times_pub = nh.advertise<ur_manager::robot_time>(TIME_TOPIC, 1);

    }

    //kører en pickup rutine
//    void pickupCallback(const opencv::ballPoseConstPtr& msg) //til peter
    void pickupCallback(const ur_manager::ballPoseConstPtr& msg) //til kasper
    {

        //start time
        start_time = std::chrono::duration_cast< std::chrono::milliseconds >(
                    std::chrono::system_clock::now().time_since_epoch());

        last_pose = msg;
        ROS_INFO_STREAM("PICKER: Pose recieved: \n Frame_id: "
                        << last_pose->header.frame_id
                        << "\nPose: "
                        << last_pose->pose);

        if (last_pose != nullptr){ //a pose was recieved!
//            //save radius for service call
//            radius = last_pose->radius;
            radius = 64; //hardcoded ball radius
            grip_call.request.speed = 420;
            grip_call.request.width = radius;
            release_call.request.speed = 420;
            release_call.request.width = 100;

            //transform pose to world frame
            bool cor_frame = this->getPoseInWorld();
            if (!cor_frame){
                ROS_INFO_STREAM("PICKER recieved wrong frame on topic - recieved: " << last_pose->header.frame_id);
                return;
            }

            //set target til request
            ur_move_call.request.frame_id = WORLD_FRAME;
            ur_move_call.request.pose = approach;
            ur_move_call.request.scalingFactor = 1.0;
            ROS_INFO_STREAM("PICKER: request to move_arm_srv: " << ur_move_call.request.pose);
            //execute ruitine
            do{                
                //to to approach
                ur_move_cli.call(ur_move_call); //call move

                if (ur_move_call.response.error == 255){
                    set_failed_times();
                    ROS_ERROR("PICKER_CALLBACK - MOVE FAILED");
                    break;
                }

                //go to grip
                ur_move_call.request.pose = cur_target;
                ur_move_call.request.scalingFactor = 0.5;
                ur_move_cli.call(ur_move_call); //call move

                if (ur_move_call.response.error == 255){
                    set_failed_times();
                    ROS_ERROR("PICKER_CALLBACK - MOVE FAILED");
                    break;
                }

                //for test!
                continue_promt();

                if (!wsg_grip_cli.call(grip_call)){ //grip
                    set_failed_times();
                    ROS_ERROR("PICKER_CALLBACK: GRIPPER FAILED");
                    break;
                }

                if (!ur_home_cli.call(emp_call)){ //til standby
                    set_failed_times();
                    break;
                }

                //timestamp
                pickup_end_time = std::chrono::duration_cast<std::chrono::milliseconds>(
                            std::chrono::system_clock::now().time_since_epoch());
                //set pick_up_time
                times_msg.pickup_time = pickup_end_time.count() - start_time.count();

                if (!ur_prime_cli.call(emp_call)) //ryk arm tilbage
                    times_msg.throw_time = -1.0;
                    break;

                if (!throw_cli.call(emp_call)) //kaste move
                    times_msg.throw_time = -1.0;
                    break;

                //wait for release
                usleep(260000); //500000 (slam dunk WORKS! //[400000, 350000] hits the backboard //250000 is a nice loop

                /******************/
                //Test service call
                state_cli.call(emp_call);
                /******************/

                wsg_release_cli.call(release_call);

                end_time = std::chrono::duration_cast< std::chrono::milliseconds >(
                            std::chrono::system_clock::now().time_since_epoch());

            }while(false); //kør én gang

            //kør hjem igen
            ur_home_cli.call(emp_call);

        } else {
            ROS_ERROR("PICKER - NO POSE!");
        }

        //end time


        if (times_msg.throw_time != -1.0) //throw time
            times_msg.throw_time = end_time.count() - pickup_end_time.count();

        //publish times
        times_pub.publish(times_msg);
        ROS_INFO_STREAM("PICKER:\nPickup time:" << times_msg.pickup_time
                        << "ms\nThrow time: " << times_msg.throw_time << "ms");

        //publish to pickup_state
        //Will trigger new image prossecing!
        pickup_state_pub.publish(state_msg);

    }

    //omregner pose fra kamera frame til world frame
    bool getPoseInWorld(){
        if (last_pose->header.frame_id != CAM_FRAME)
            return false;

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
        approach = cur_target;
        approach.position.z += 0.1;
        ROS_INFO_STREAM("PICKER: Pose in World frame:\n"
                        << cur_target);
        return true;
    }

    bool readyToMove(){ /*(deprecated)*/
        if (!last_pose)
            return false;

        ROS_INFO("PICKER: READY TO MOVE!");
        return true;
    }

    void moveToPickUp(/*moveit::planning_interface::MoveGroupInterface &group*/){ /*(deptrecated)*/
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
                arm.execute(thee_plan);std::chrono::milliseconds end_time;
            } else {
                ROS_ERROR("PICKER - NO PLAN");
                break;
            }
        } while(false); //kør loop én gang, men muliggør break

        //return home
        arm.setNamedTarget(HOME);
        arm.move();
    }

    void set_failed_times(){
        times_msg.pickup_time = -1.0;
        times_msg.throw_time = -1.0;
    }

    void continue_promt(){
        ROS_INFO("PICER_CALLBACK: PRESS ENTER TO CONTINUE!");
        std::cin.ignore(1);
    }
private:
    tf::TransformListener listener;
    ros::Subscriber pose_sub;
//    opencv::ballPoseConstPtr last_pose; //til peter
    ur_manager::ballPoseConstPtr last_pose; //til kasper
    geometry_msgs::Pose cur_target;
    geometry_msgs::Pose approach;
    double radius;

    //publisher for pickupstate
    ros::Publisher pickup_state_pub;
    std_msgs::Empty state_msg;

    //service clients
    ros::ServiceClient ur_home_cli;
    ros::ServiceClient ur_move_cli;
    ros::ServiceClient ur_prime_cli;
    ros::ServiceClient wsg_grip_cli;
    ros::ServiceClient wsg_release_cli;
    ros::ServiceClient throw_cli;
    ros::ServiceClient state_cli;

    //service objects
    wsg_50_common::Move grip_call;
    wsg_50_common::Move release_call;
    ur_manager::move ur_move_call;
    std_srvs::Empty emp_call;

    //move_group planning
    moveit::planning_interface::MoveGroupInterface::Plan thee_plan;

    //time
    std::chrono::milliseconds start_time;
    std::chrono::milliseconds pickup_end_time;
    std::chrono::milliseconds end_time;
    ros::Publisher times_pub;
    ur_manager::robot_time times_msg;
};


int main(int argc, char **argv)
{
    ros::init(argc, argv, "pick_callback");
    ros::NodeHandle nh;

//    ros::AsyncSpinner spin(1);
//    spin.start();

    ROS_INFO("Picker node launched");

    //create picker object - will run a callback
    Picker the_one_that_shall_pick_up(nh);


    ros::spin();
//    ros::waitForShutdown();
    return 0;
}
