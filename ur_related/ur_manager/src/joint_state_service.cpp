#include "ros/ros.h"
#include <sensor_msgs/JointState.h>
#include <std_srvs/Empty.h>
#include <iostream>
#include <fstream>

#define JOINT_STATE_TOPIC "joint_states"
#define SERVICE_NAME "joint_state_service"
#define SAVE_PATH "Joint_States.txt"

class State_srv{
public:
    State_srv(ros::NodeHandle &nh){
        state_sub = nh.subscribe<sensor_msgs::JointState>(JOINT_STATE_TOPIC, 1, &State_srv::callback, this);
        srv = nh.advertiseService(SERVICE_NAME, &State_srv::service, this);
    }

    void callback(const sensor_msgs::JointStateConstPtr &state){
        last_state = state;
    }

    bool service(std_srvs::Empty::Request &req, std_srvs::Empty::Request &res){
        ROS_INFO("JOINT_STATE_SRV: SAVING CURRENT STATE!");
        std::ofstream file(SAVE_PATH);

        file << std::setw(10) << "Name";
        for(int i = 0; i < last_state->name.size(); i++){
            file << std::setw(22) << last_state->name[i];
        }
        file << std::endl;

        file << std::setw(10) << "Velocity";
        for(int i = 0; i < last_state->name.size(); i++){
            file << std::setw(22) << last_state->velocity[i];
        }
        file << std::endl;

        file << std::setw(10) << "Position";
        for(int i = 0; i < last_state->name.size(); i++){
            file << std::setw(22) << last_state->position[i];
        }
        file << std::endl;
    }

private:
    ros::Subscriber state_sub;
    sensor_msgs::JointStateConstPtr last_state;
    ros::ServiceServer srv;
};

int main(int argc, char **argv)
{
    ros::init(argc, argv, "joint_state_service");
    ros::NodeHandle nh;

    State_srv state_srv(nh);
    ros::spin();

    return 0;
}
