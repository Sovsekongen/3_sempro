##############################################################################
# CMake
##############################################################################

cmake_minimum_required(VERSION 2.8.3)
project(rosjava_catkin_package_a)

##############################################################################
# Catkin
##############################################################################

find_package(catkin REQUIRED COMPONENTS 
	rosjava_build_tools
	roscpp
	rospy
<<<<<<< HEAD
        message_generation
        std_msgs
        geometry_msgs
	genjava
=======
	std_msgs
	message_generation
>>>>>>> 26a60b4c68b3a2a261a9b928b81953632d92833b
)

# Set the gradle targets you want catkin's make to run by default, e.g.
#   catkin_rosjava_setup(installDist)
# Note that the catkin_create_rosjava_xxx scripts will usually automatically
# add tasks to this for you when you create subprojects.
catkin_rosjava_setup(installDist publish)

<<<<<<< HEAD
add_message_files(
        DIRECTORY
        my_pub_sub_tutorial/msg
        FILES
        Cvmsg.msg
        Urmsg.msg
#        DIRECTORY
#        beginner_tutorials/msg
#        FILES
#        cvmsger.msg
)

generate_messages(
        DEPENDENCIES
        std_msgs
        geometry_msgs
)

generate_rosjava_messages(
  PACKAGES
    std_msgs
    ros_comm_msgs            # ros/ros_comm_msgs
    rosjava_test_msgs        # rosjava/rosjava_test_msgs
    actionlib_msgs           # ros/common_msgs
)

catkin_package(
        CATKIN_DEPENDS message_runtime
=======
#add_message_files(
#	FILES
#	Num.msg
#)

#generate_messages(
#	DEPENDENCIES
#	std_msgs
#)

catkin_package(
#	CATKIN_DEPENDS message_runtime
>>>>>>> 26a60b4c68b3a2a261a9b928b81953632d92833b
)


##############################################################################
# Installation
##############################################################################

# Change this to match the maven group name you have specified in the
# allprojects closure the root build.gradle
install(DIRECTORY ${CATKIN_DEVEL_PREFIX}/${CATKIN_GLOBAL_MAVEN_DESTINATION}/com/github/rosjava/${PROJECT_NAME}/
        DESTINATION ${CATKIN_GLOBAL_MAVEN_DESTINATION}/com/github/rosjava/${PROJECT_NAME})

## Build talker.cpp
#add_executable(talker src/talker.cpp)
#target_link_libraries(talker ${catkin_LIBRARIES})
#add_dependencies(talker beginner_tutorials_generate_messages_cpp)

#add_executable(listener src/listener.cpp)
#target_link_libraries(listener ${catkin_LIBRARIES})
#add_dependencies(listener beginner_tutorials_generate_messages_cpp)
