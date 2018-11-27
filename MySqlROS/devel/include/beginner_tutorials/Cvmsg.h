// Generated by gencpp from file beginner_tutorials/Cvmsg.msg
// DO NOT EDIT!


#ifndef BEGINNER_TUTORIALS_MESSAGE_CVMSG_H
#define BEGINNER_TUTORIALS_MESSAGE_CVMSG_H


#include <string>
#include <vector>
#include <map>

#include <ros/types.h>
#include <ros/serialization.h>
#include <ros/builtin_message_traits.h>
#include <ros/message_operations.h>

#include <std_msgs/Header.h>
#include <geometry_msgs/Pose.h>

namespace beginner_tutorials
{
template <class ContainerAllocator>
struct Cvmsg_
{
  typedef Cvmsg_<ContainerAllocator> Type;

  Cvmsg_()
    : header()
    , pose()
    , color()
    , shape()
    , pic()
    , radius(0)
    , exeTime(0)  {
    }
  Cvmsg_(const ContainerAllocator& _alloc)
    : header(_alloc)
    , pose(_alloc)
    , color(_alloc)
    , shape(_alloc)
    , pic(_alloc)
    , radius(0)
    , exeTime(0)  {
  (void)_alloc;
    }



   typedef  ::std_msgs::Header_<ContainerAllocator>  _header_type;
  _header_type header;

   typedef  ::geometry_msgs::Pose_<ContainerAllocator>  _pose_type;
  _pose_type pose;

   typedef std::basic_string<char, std::char_traits<char>, typename ContainerAllocator::template rebind<char>::other >  _color_type;
  _color_type color;

   typedef std::basic_string<char, std::char_traits<char>, typename ContainerAllocator::template rebind<char>::other >  _shape_type;
  _shape_type shape;

   typedef std::basic_string<char, std::char_traits<char>, typename ContainerAllocator::template rebind<char>::other >  _pic_type;
  _pic_type pic;

   typedef uint32_t _radius_type;
  _radius_type radius;

   typedef uint32_t _exeTime_type;
  _exeTime_type exeTime;





  typedef boost::shared_ptr< ::beginner_tutorials::Cvmsg_<ContainerAllocator> > Ptr;
  typedef boost::shared_ptr< ::beginner_tutorials::Cvmsg_<ContainerAllocator> const> ConstPtr;

}; // struct Cvmsg_

typedef ::beginner_tutorials::Cvmsg_<std::allocator<void> > Cvmsg;

typedef boost::shared_ptr< ::beginner_tutorials::Cvmsg > CvmsgPtr;
typedef boost::shared_ptr< ::beginner_tutorials::Cvmsg const> CvmsgConstPtr;

// constants requiring out of line definition



template<typename ContainerAllocator>
std::ostream& operator<<(std::ostream& s, const ::beginner_tutorials::Cvmsg_<ContainerAllocator> & v)
{
ros::message_operations::Printer< ::beginner_tutorials::Cvmsg_<ContainerAllocator> >::stream(s, "", v);
return s;
}

} // namespace beginner_tutorials

namespace ros
{
namespace message_traits
{



// BOOLTRAITS {'IsFixedSize': False, 'IsMessage': True, 'HasHeader': True}
// {'beginner_tutorials': ['/home/viktor/Desktop/3_sempro/MySqlROS/src/beginner_tutorials/msg'], 'geometry_msgs': ['/opt/ros/kinetic/share/geometry_msgs/cmake/../msg'], 'std_msgs': ['/opt/ros/kinetic/share/std_msgs/cmake/../msg']}

// !!!!!!!!!!! ['__class__', '__delattr__', '__dict__', '__doc__', '__eq__', '__format__', '__getattribute__', '__hash__', '__init__', '__module__', '__ne__', '__new__', '__reduce__', '__reduce_ex__', '__repr__', '__setattr__', '__sizeof__', '__str__', '__subclasshook__', '__weakref__', '_parsed_fields', 'constants', 'fields', 'full_name', 'has_header', 'header_present', 'names', 'package', 'parsed_fields', 'short_name', 'text', 'types']




template <class ContainerAllocator>
struct IsFixedSize< ::beginner_tutorials::Cvmsg_<ContainerAllocator> >
  : FalseType
  { };

template <class ContainerAllocator>
struct IsFixedSize< ::beginner_tutorials::Cvmsg_<ContainerAllocator> const>
  : FalseType
  { };

template <class ContainerAllocator>
struct IsMessage< ::beginner_tutorials::Cvmsg_<ContainerAllocator> >
  : TrueType
  { };

template <class ContainerAllocator>
struct IsMessage< ::beginner_tutorials::Cvmsg_<ContainerAllocator> const>
  : TrueType
  { };

template <class ContainerAllocator>
struct HasHeader< ::beginner_tutorials::Cvmsg_<ContainerAllocator> >
  : TrueType
  { };

template <class ContainerAllocator>
struct HasHeader< ::beginner_tutorials::Cvmsg_<ContainerAllocator> const>
  : TrueType
  { };


template<class ContainerAllocator>
struct MD5Sum< ::beginner_tutorials::Cvmsg_<ContainerAllocator> >
{
  static const char* value()
  {
    return "76c45fb61e7f729ef565aaafd3f52a41";
  }

  static const char* value(const ::beginner_tutorials::Cvmsg_<ContainerAllocator>&) { return value(); }
  static const uint64_t static_value1 = 0x76c45fb61e7f729eULL;
  static const uint64_t static_value2 = 0xf565aaafd3f52a41ULL;
};

template<class ContainerAllocator>
struct DataType< ::beginner_tutorials::Cvmsg_<ContainerAllocator> >
{
  static const char* value()
  {
    return "beginner_tutorials/Cvmsg";
  }

  static const char* value(const ::beginner_tutorials::Cvmsg_<ContainerAllocator>&) { return value(); }
};

template<class ContainerAllocator>
struct Definition< ::beginner_tutorials::Cvmsg_<ContainerAllocator> >
{
  static const char* value()
  {
    return "Header header\n\
geometry_msgs/Pose pose\n\
string color\n\
string shape\n\
string pic\n\
uint32 radius\n\
uint32 exeTime\n\
\n\
================================================================================\n\
MSG: std_msgs/Header\n\
# Standard metadata for higher-level stamped data types.\n\
# This is generally used to communicate timestamped data \n\
# in a particular coordinate frame.\n\
# \n\
# sequence ID: consecutively increasing ID \n\
uint32 seq\n\
#Two-integer timestamp that is expressed as:\n\
# * stamp.sec: seconds (stamp_secs) since epoch (in Python the variable is called 'secs')\n\
# * stamp.nsec: nanoseconds since stamp_secs (in Python the variable is called 'nsecs')\n\
# time-handling sugar is provided by the client library\n\
time stamp\n\
#Frame this data is associated with\n\
# 0: no frame\n\
# 1: global frame\n\
string frame_id\n\
\n\
================================================================================\n\
MSG: geometry_msgs/Pose\n\
# A representation of pose in free space, composed of position and orientation. \n\
Point position\n\
Quaternion orientation\n\
\n\
================================================================================\n\
MSG: geometry_msgs/Point\n\
# This contains the position of a point in free space\n\
float64 x\n\
float64 y\n\
float64 z\n\
\n\
================================================================================\n\
MSG: geometry_msgs/Quaternion\n\
# This represents an orientation in free space in quaternion form.\n\
\n\
float64 x\n\
float64 y\n\
float64 z\n\
float64 w\n\
";
  }

  static const char* value(const ::beginner_tutorials::Cvmsg_<ContainerAllocator>&) { return value(); }
};

} // namespace message_traits
} // namespace ros

namespace ros
{
namespace serialization
{

  template<class ContainerAllocator> struct Serializer< ::beginner_tutorials::Cvmsg_<ContainerAllocator> >
  {
    template<typename Stream, typename T> inline static void allInOne(Stream& stream, T m)
    {
      stream.next(m.header);
      stream.next(m.pose);
      stream.next(m.color);
      stream.next(m.shape);
      stream.next(m.pic);
      stream.next(m.radius);
      stream.next(m.exeTime);
    }

    ROS_DECLARE_ALLINONE_SERIALIZER
  }; // struct Cvmsg_

} // namespace serialization
} // namespace ros

namespace ros
{
namespace message_operations
{

template<class ContainerAllocator>
struct Printer< ::beginner_tutorials::Cvmsg_<ContainerAllocator> >
{
  template<typename Stream> static void stream(Stream& s, const std::string& indent, const ::beginner_tutorials::Cvmsg_<ContainerAllocator>& v)
  {
    s << indent << "header: ";
    s << std::endl;
    Printer< ::std_msgs::Header_<ContainerAllocator> >::stream(s, indent + "  ", v.header);
    s << indent << "pose: ";
    s << std::endl;
    Printer< ::geometry_msgs::Pose_<ContainerAllocator> >::stream(s, indent + "  ", v.pose);
    s << indent << "color: ";
    Printer<std::basic_string<char, std::char_traits<char>, typename ContainerAllocator::template rebind<char>::other > >::stream(s, indent + "  ", v.color);
    s << indent << "shape: ";
    Printer<std::basic_string<char, std::char_traits<char>, typename ContainerAllocator::template rebind<char>::other > >::stream(s, indent + "  ", v.shape);
    s << indent << "pic: ";
    Printer<std::basic_string<char, std::char_traits<char>, typename ContainerAllocator::template rebind<char>::other > >::stream(s, indent + "  ", v.pic);
    s << indent << "radius: ";
    Printer<uint32_t>::stream(s, indent + "  ", v.radius);
    s << indent << "exeTime: ";
    Printer<uint32_t>::stream(s, indent + "  ", v.exeTime);
  }
};

} // namespace message_operations
} // namespace ros

#endif // BEGINNER_TUTORIALS_MESSAGE_CVMSG_H