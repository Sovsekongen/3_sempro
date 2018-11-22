cmake_minimum_required(VERSION 2.8.3)
project(opencv)

## Compile as C++11, supported in ROS Kinetic and newer
# add_compile_options(-std=c++11)

## Find catkin macros and libraries
## if COMPONENTS list like find_package(catkin REQUIRED COMPONENTS xyz)
## is used, also find other catkin packages
#cv_bridge skal bruges til opencv
find_package(catkin REQUIRED
    roscpp
    rospy
    cv_bridge
    image_transport
    std_msgs
    message_generation
    sensor_msgs
    actionlib_msgs
    tf
    camera_info_manager
    )

include_directories(${catkin_INCLUDE_DIRS})

##Opencv virker fint uden dette, men hvis du skal bruge funktionen imshow, skal du have denne med
find_package(OpenCV REQUIRED)

SET( CMAKE_EXE_LINKER_FLAGS "${CMAKE_EXE_LINKER_FLAGS} -Wl,-E")

#generate_dynamic_reconfigure_options(cfg/BaslerToF.cfg)

##catkin_package(
  #  INCLUDE_DIRS include
   # LIBRARIES basler_camera
#)

#skal altid med når du benytter dig af opencv
include_directories(${OpenCV_INCLUDE_DIRS})
#include_directories($ENV{PYLON_ROOT}/include $ENV{GENICAM_ROOT_V2_3}/library/CPP/include)
include_directories(/opt/pylon5/include)
link_directories(/opt/pylon5/lib64)

#skal finde pylon filerne
#link_directories(
 # $ENV{PYLON_ROOT}/lib64
  #$ENV{GENICAM_ROOT_V2_3}/bin/Linux64_x64
  #$ENV{GENICAM_ROOT_V2_3}/bin/Linux64_x64/GenApi/Generic
  #$ENV{GENICAM_ROOT_V2_3}/library/CPP/lib/Linux64_x64
#)
#find_package(OpenCV REQUIRED)

#include_directories( ${OpenCV_INCLUDE_DIRS} )



## System dependencies are found with CMake's conventions
# find_package(Boost REQUIRED COMPONENTS system)


## Uncomment this if the package has a setup.py. This macro ensures
## modules and global scripts declared therein get installed
## See http://ros.org/doc/api/catkin/html/user_guide/setup_dot_py.html
# catkin_python_setup()

################################################
## Declare ROS messages, services and actions ##
################################################

## To declare and build messages, services or actions from within this
## package, follow these steps:
## * Let MSG_DEP_SET be the set of packages whose message types you use in
##   your messages/services/actions (e.g. std_msgs, actionlib_msgs, ...).
## * In the file package.xml:
##   * add a build_depend tag for "message_generation"
##   * add a build_depend and a exec_depend tag for each package in MSG_DEP_SET
##   * If MSG_DEP_SET isn't empty the following dependency has been pulled in
##     but can be declared for certainty nonetheless:
##     * add a exec_depend tag for "message_rtarget_link_libraries( your_target ${OpenCV_LIBS} )untime"
## * In this file (CMakeLists.txt):
##   * add "message_generation" and every package in MSG_DEP_SET to
##     find_package(catkin REQUIRED COMPONENTS ...)
##   * add "message_runtime" and every package in MSG_DEP_SET to
##     catkin_package(CATKIN_DEPENDS ...)
##   * uncomment the add_*_files sections below as needed
##     and list every .msg/.srv/.action file to be processed
##   * uncomment the generate_messages entry below
##   * add every package in MSG_DEP_SET to generate_messages(DEPENDENCIES ...)

## Generate messages in the 'msg' folder
add_message_files(
  FILES
  Num.msg
  ballPose.msg
  # Message2.msg
)

## Generate services in the 'srv' folder
add_service_files(
   FILES
   AddTwoInts.srv
#   Service2.srv
)

## Generate actions in the 'action' folder
# add_action_files(
#   FILES
#   Action1.action
#   Action2.action
# )

## Generate added messages and services with any dependencies listed here
generate_messages(
  DEPENDENCIES
  actionlib_msgs
  sensor_msgs
  std_msgs
  geometry_msgs
  # Or other packages containing msgs
)

################################################
## Declare ROS dynamic reconfigure parameters ##
################################################

## To declare and build dynamic reconfigure parameters within this
## package, follow these steps:
## * In the file package.xml:
##   * add a build_depend and a exec_depend tag for "dynamic_reconfigure"
## * In this file (CMakeLists.txt):
##   * add "dynamic_reconfigure" to
##     find_package(catkin REQUIRED COMPONENTS ...)
##   * uncomment the "generate_dynamic_reconfigure_options" section below
##     and list every .cfg file to be processed

## Generate dynamic reconfigure parameters in the 'cfg' folder
# generate_dynamic_reconfigure_options(
#   cfg/DynReconf1.cfg
#   cfg/DynReconf2.cfg
# )

###################################
## catkin specific configuration ##
###################################
## The catkin_package macro generates cmake config files for your package
## Declare things to be passed to dependent projects
## INCLUDE_DIRS: uncomment this if your package contains header files
## LIBRARIES: libraries you create in this project that dependent projects also need
## CATKIN_DEPENDS: catkin_packages dependent projects also need
## DEPENDS: system dependencies of this project that dependent projects also need
##catkin_package(
#  INCLUDE_DIRS include
#  LIBRARIES opencv
#  CATKIN_DEPENDS other_catkin_pkg
#  DEPENDS system_lib
#)

###########
## Build ##
###########

## Specify additional locations of header files
## Your package locations should be listed before other locations
include_directories(
# include
# ${catkin_INCLUDE_DIRS}
)

## Declare a C++ library
# add_library(${PROJECT_NAME}
#   src/${PROJECT_NAME}/opencv.cpp
# )

## Add cmake target dependencies of the library
## as an example, code may need to be generated before libraries
## either from message generation or dynamic reconfigure
# add_dependencies(${PROJECT_NAME} ${${PROJECT_NAME}_EXPORTED_TARGETS} ${catkin_EXPORTED_TARGETS})

## Declare a C++ executable
## With catkin_make all packages are built within a single CMake context
## The recommended prefix ensures that target names across packages don't collide
# add_executable(${PROJECT_NAME}_node src/opencv_node.cpp)

## Rename C++ executable without prefix
## The above recommended prefix causes long target names, the following renames the
## target back to the shorter version for ease of user use
## e.g. "rosrun someones_pkg node" instead of "rosrun someones_pkg someones_pkg_node"
# set_target_properties(${PROJECT_NAME}_node PROPERTIES OUTPUT_NAME node PREFIX "")

## Add cmake target dependencies of the executable
## same as for the library above
# add_dependencies(${PROJECT_NAME}_node ${${PROJECT_NAME}_EXPORTED_TARGETS} ${catkin_EXPORTED_TARGETS})

## Specify libraries to link a library or executable target against
# target_link_libraries(${PROJECT_NAME}_node
#   ${catkin_LIBRARIES}
# )

#############
## Install ##
#############

# all install targets should use catkin DESTINATION variables
# See http://ros.org/doc/api/catkin/html/adv_user_guide/variables.html

## Mark executable scripts (Python etc.) for installation
## in contrast to setup.py, you can choose the destination
# install(PROGRAMS
#   scripts/my_python_script
#   DESTINATION ${CATKIN_PACKAGE_BIN_DESTINATION}
# )

## Mark executables and/or libraries for installation
# install(TARGETS ${PROJECT_NAME} ${PROJECT_NAME}_node
#   ARCHIVE DESTINATION ${CATKIN_PACKAGE_LIB_DESTINATION}
#   LIBRARY DESTINATION ${CATKIN_PACKAGE_LIB_DESTINATION}
#   RUNTIME DESTINATION ${CATKIN_PACKAGE_BIN_DESTINATION}
# )

## Mark cpp header files for installation
# install(DIRECTORY include/${PROJECT_NAME}/
#   DESTINATION ${CATKIN_PACKAGE_INCLUDE_DESTINATION}
#   FILES_MATCHING PATTERN "*.h"
#   PATTERN ".svn" EXCLUDE
# )

## Mark other files for installation (e.g. launch and bag files, etc.)
# install(FILES
#   # myfile1
#   # myfile2
#   DESTINATION ${CATKIN_PACKAGE_SHARE_DESTINATION}
# )

#############
## Testing ##
#############

## Add gtest based cpp test target and link libraries
# catkin_add_gtest(${PROJECT_NAME}-test test/test_opencv.cpp)
# if(TARGET ${PROJECT_NAME}-test)
#   target_link_libraries(${PROJECT_NAME}-test ${PROJECT_NAME})
# endif()

## Add folders to be run by python nosetests
# catkin_add_nosetests(test)
## ${OpenCV_LIBS} skal tilføjes her for at du kan benytte dig af opencv med ros
#talker node
add_executable(talker src/talker.cpp)
target_link_libraries(talker ${OpenCV_LIBS} ${catkin_LIBRARIES})
add_dependencies(talker opencv_generate_messages_cpp)

#listener node
add_executable(listener src/listener.cpp)
target_link_libraries(listener ${OpenCV_LIBS} ${catkin_LIBRARIES})
add_dependencies(listener opencv_generate_messages_cpp)

#pylon node
add_executable(pylon src/pylon.cpp)
target_link_libraries(pylon
  ${OpenCV_LIBS}
  ${catkin_LIBRARIES}
  pylonbase
  pylonutility
  GCBase_gcc_v3_1_Basler_pylon_v5_1
  GenApi_gcc_v3_1_Basler_pylon_v5_1
)
add_dependencies(pylon opencv_generate_messages_cpp)

#cameraCalibration node
add_executable(cameraCalibration src/cameraCalibration.cpp)
target_link_libraries(cameraCalibration ${OpenCV_LIBS} ${catkin_LIBRARIES})
add_dependencies(cameraCalibration opencv_generate_messages_cpp)

#takePicture node
add_executable(takePictures src/takePictures.cpp)
target_link_libraries(takePictures
    ${OpenCV_LIBS}
    ${catkin_LIBRARIES}
    pylonbase
    pylonutility
    GCBase_gcc_v3_1_Basler_pylon_v5_1
    GenApi_gcc_v3_1_Basler_pylon_v5_1
)
add_dependencies(takePictures opencv_generate_messages_cpp)


add_executable(ballPosition src/ballPosition.cpp)
target_link_libraries(ballPosition ${OpenCV_LIBS} ${catkin_LIBRARIES})
add_dependencies(ballPosition opencv_generate_messages_cpp)

add_executable(colourSettings src/colourSettings.cpp)
target_link_libraries(colourSettings
  ${OpenCV_LIBS}
  ${catkin_LIBRARIES}
  pylonbase
  pylonutility
  GCBase_gcc_v3_1_Basler_pylon_v5_1
  GenApi_gcc_v3_1_Basler_pylon_v5_1
)
add_dependencies(colourSettings opencv_generate_messages_cpp)
