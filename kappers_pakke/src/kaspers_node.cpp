#include <ros/ros.h>
#include <kappers_pakke/LocalizePart.h>
#include <string>

class ScanNPlan {
public:
    ScanNPlan(ros::NodeHandle &nh){
        vision_client = nh.serviceClient<kappers_pakke::LocalizePart>("localize_part");
    }

    void start(const std::string& base_frame){
      ROS_INFO("localizing part");
      kappers_pakke::LocalizePart srv;
      srv.request.base_frame = base_frame;
      ROS_INFO_STREAM("Requesting pose in frame: " << base_frame);

      if (!vision_client.call(srv)){
        ROS_ERROR("Could not localize!");
        return;
      }

      ROS_INFO_STREAM("Part localized: " << srv.response);

    }

private:
    ros::ServiceClient vision_client;
};

/**************************/
int main(int argc, char **argv)
{
  ros::init(argc, argv, "kaspers_node");
  ros::NodeHandle nh;

  ros::NodeHandle pvt_nh("~");
  std::string base_frame;
  pvt_nh.param<std::string>("base_frame", base_frame, "world");
  //                         param name   str obj ref  def val

  ROS_INFO("Kaspers node has been initialized!");

  ScanNPlan app(nh);

  //wait for app to initialize (sleep)
  ros::Duration(.8).sleep();

  app.start(base_frame);
  ROS_INFO("app done");

  ros::spin();
}
