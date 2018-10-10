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
	std_msgs
	message_generation
)

# Set the gradle targets you want catkin's make to run by default, e.g.
#   catkin_rosjava_setup(installDist)
# Note that the catkin_create_rosjava_xxx scripts will usually automatically
# add tasks to this for you when you create subprojects.
catkin_rosjava_setup(installDist publish)

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
