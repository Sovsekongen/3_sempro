# generated from genmsg/cmake/pkg-genmsg.cmake.em

<<<<<<< HEAD
message(STATUS "rosjava_catkin_package_a: 2 messages, 0 services")

set(MSG_I_FLAGS "-Irosjava_catkin_package_a:/home/viktor/Desktop/3_sempro/MySqlROS/src/rosjava_catkin_package_a/my_pub_sub_tutorial/msg;-Istd_msgs:/opt/ros/kinetic/share/std_msgs/cmake/../msg;-Igeometry_msgs:/opt/ros/kinetic/share/geometry_msgs/cmake/../msg")
=======
message(WARNING "Invoking generate_messages() without having added any message or service file before.
You should either add add_message_files() and/or add_service_files() calls or remove the invocation of generate_messages().")
message(STATUS "rosjava_catkin_package_a: 0 messages, 0 services")

set(MSG_I_FLAGS "-Istd_msgs:/opt/ros/kinetic/share/std_msgs/cmake/../msg")
>>>>>>> 26a60b4c68b3a2a261a9b928b81953632d92833b

# Find all generators
find_package(gencpp REQUIRED)
find_package(geneus REQUIRED)
find_package(genjava REQUIRED)
find_package(genlisp REQUIRED)
find_package(gennodejs REQUIRED)
find_package(genpy REQUIRED)

add_custom_target(rosjava_catkin_package_a_generate_messages ALL)

# verify that message/service dependencies have not changed since configure



<<<<<<< HEAD
get_filename_component(_filename "/home/viktor/Desktop/3_sempro/MySqlROS/src/rosjava_catkin_package_a/my_pub_sub_tutorial/msg/Urmsg.msg" NAME_WE)
add_custom_target(_rosjava_catkin_package_a_generate_messages_check_deps_${_filename}
  COMMAND ${CATKIN_ENV} ${PYTHON_EXECUTABLE} ${GENMSG_CHECK_DEPS_SCRIPT} "rosjava_catkin_package_a" "/home/viktor/Desktop/3_sempro/MySqlROS/src/rosjava_catkin_package_a/my_pub_sub_tutorial/msg/Urmsg.msg" ""
)

get_filename_component(_filename "/home/viktor/Desktop/3_sempro/MySqlROS/src/rosjava_catkin_package_a/my_pub_sub_tutorial/msg/Cvmsg.msg" NAME_WE)
add_custom_target(_rosjava_catkin_package_a_generate_messages_check_deps_${_filename}
  COMMAND ${CATKIN_ENV} ${PYTHON_EXECUTABLE} ${GENMSG_CHECK_DEPS_SCRIPT} "rosjava_catkin_package_a" "/home/viktor/Desktop/3_sempro/MySqlROS/src/rosjava_catkin_package_a/my_pub_sub_tutorial/msg/Cvmsg.msg" "geometry_msgs/Quaternion:geometry_msgs/Pose:std_msgs/Header:geometry_msgs/Point"
)

=======
>>>>>>> 26a60b4c68b3a2a261a9b928b81953632d92833b
#
#  langs = gencpp;geneus;genjava;genlisp;gennodejs;genpy
#

### Section generating for lang: gencpp
### Generating Messages
<<<<<<< HEAD
_generate_msg_cpp(rosjava_catkin_package_a
  "/home/viktor/Desktop/3_sempro/MySqlROS/src/rosjava_catkin_package_a/my_pub_sub_tutorial/msg/Urmsg.msg"
  "${MSG_I_FLAGS}"
  ""
  ${CATKIN_DEVEL_PREFIX}/${gencpp_INSTALL_DIR}/rosjava_catkin_package_a
)
_generate_msg_cpp(rosjava_catkin_package_a
  "/home/viktor/Desktop/3_sempro/MySqlROS/src/rosjava_catkin_package_a/my_pub_sub_tutorial/msg/Cvmsg.msg"
  "${MSG_I_FLAGS}"
  "/opt/ros/kinetic/share/geometry_msgs/cmake/../msg/Quaternion.msg;/opt/ros/kinetic/share/geometry_msgs/cmake/../msg/Pose.msg;/opt/ros/kinetic/share/std_msgs/cmake/../msg/Header.msg;/opt/ros/kinetic/share/geometry_msgs/cmake/../msg/Point.msg"
  ${CATKIN_DEVEL_PREFIX}/${gencpp_INSTALL_DIR}/rosjava_catkin_package_a
)
=======
>>>>>>> 26a60b4c68b3a2a261a9b928b81953632d92833b

### Generating Services

### Generating Module File
_generate_module_cpp(rosjava_catkin_package_a
  ${CATKIN_DEVEL_PREFIX}/${gencpp_INSTALL_DIR}/rosjava_catkin_package_a
  "${ALL_GEN_OUTPUT_FILES_cpp}"
)

add_custom_target(rosjava_catkin_package_a_generate_messages_cpp
  DEPENDS ${ALL_GEN_OUTPUT_FILES_cpp}
)
add_dependencies(rosjava_catkin_package_a_generate_messages rosjava_catkin_package_a_generate_messages_cpp)

# add dependencies to all check dependencies targets
<<<<<<< HEAD
get_filename_component(_filename "/home/viktor/Desktop/3_sempro/MySqlROS/src/rosjava_catkin_package_a/my_pub_sub_tutorial/msg/Urmsg.msg" NAME_WE)
add_dependencies(rosjava_catkin_package_a_generate_messages_cpp _rosjava_catkin_package_a_generate_messages_check_deps_${_filename})
get_filename_component(_filename "/home/viktor/Desktop/3_sempro/MySqlROS/src/rosjava_catkin_package_a/my_pub_sub_tutorial/msg/Cvmsg.msg" NAME_WE)
add_dependencies(rosjava_catkin_package_a_generate_messages_cpp _rosjava_catkin_package_a_generate_messages_check_deps_${_filename})
=======
>>>>>>> 26a60b4c68b3a2a261a9b928b81953632d92833b

# target for backward compatibility
add_custom_target(rosjava_catkin_package_a_gencpp)
add_dependencies(rosjava_catkin_package_a_gencpp rosjava_catkin_package_a_generate_messages_cpp)

# register target for catkin_package(EXPORTED_TARGETS)
list(APPEND ${PROJECT_NAME}_EXPORTED_TARGETS rosjava_catkin_package_a_generate_messages_cpp)

### Section generating for lang: geneus
### Generating Messages
<<<<<<< HEAD
_generate_msg_eus(rosjava_catkin_package_a
  "/home/viktor/Desktop/3_sempro/MySqlROS/src/rosjava_catkin_package_a/my_pub_sub_tutorial/msg/Urmsg.msg"
  "${MSG_I_FLAGS}"
  ""
  ${CATKIN_DEVEL_PREFIX}/${geneus_INSTALL_DIR}/rosjava_catkin_package_a
)
_generate_msg_eus(rosjava_catkin_package_a
  "/home/viktor/Desktop/3_sempro/MySqlROS/src/rosjava_catkin_package_a/my_pub_sub_tutorial/msg/Cvmsg.msg"
  "${MSG_I_FLAGS}"
  "/opt/ros/kinetic/share/geometry_msgs/cmake/../msg/Quaternion.msg;/opt/ros/kinetic/share/geometry_msgs/cmake/../msg/Pose.msg;/opt/ros/kinetic/share/std_msgs/cmake/../msg/Header.msg;/opt/ros/kinetic/share/geometry_msgs/cmake/../msg/Point.msg"
  ${CATKIN_DEVEL_PREFIX}/${geneus_INSTALL_DIR}/rosjava_catkin_package_a
)
=======
>>>>>>> 26a60b4c68b3a2a261a9b928b81953632d92833b

### Generating Services

### Generating Module File
_generate_module_eus(rosjava_catkin_package_a
  ${CATKIN_DEVEL_PREFIX}/${geneus_INSTALL_DIR}/rosjava_catkin_package_a
  "${ALL_GEN_OUTPUT_FILES_eus}"
)

add_custom_target(rosjava_catkin_package_a_generate_messages_eus
  DEPENDS ${ALL_GEN_OUTPUT_FILES_eus}
)
add_dependencies(rosjava_catkin_package_a_generate_messages rosjava_catkin_package_a_generate_messages_eus)

# add dependencies to all check dependencies targets
<<<<<<< HEAD
get_filename_component(_filename "/home/viktor/Desktop/3_sempro/MySqlROS/src/rosjava_catkin_package_a/my_pub_sub_tutorial/msg/Urmsg.msg" NAME_WE)
add_dependencies(rosjava_catkin_package_a_generate_messages_eus _rosjava_catkin_package_a_generate_messages_check_deps_${_filename})
get_filename_component(_filename "/home/viktor/Desktop/3_sempro/MySqlROS/src/rosjava_catkin_package_a/my_pub_sub_tutorial/msg/Cvmsg.msg" NAME_WE)
add_dependencies(rosjava_catkin_package_a_generate_messages_eus _rosjava_catkin_package_a_generate_messages_check_deps_${_filename})
=======
>>>>>>> 26a60b4c68b3a2a261a9b928b81953632d92833b

# target for backward compatibility
add_custom_target(rosjava_catkin_package_a_geneus)
add_dependencies(rosjava_catkin_package_a_geneus rosjava_catkin_package_a_generate_messages_eus)

# register target for catkin_package(EXPORTED_TARGETS)
list(APPEND ${PROJECT_NAME}_EXPORTED_TARGETS rosjava_catkin_package_a_generate_messages_eus)

### Section generating for lang: genjava
### Generating Messages
<<<<<<< HEAD
_generate_msg_java(rosjava_catkin_package_a
  "/home/viktor/Desktop/3_sempro/MySqlROS/src/rosjava_catkin_package_a/my_pub_sub_tutorial/msg/Urmsg.msg"
  "${MSG_I_FLAGS}"
  ""
  ${CATKIN_DEVEL_PREFIX}/${genjava_INSTALL_DIR}/rosjava_catkin_package_a
)
_generate_msg_java(rosjava_catkin_package_a
  "/home/viktor/Desktop/3_sempro/MySqlROS/src/rosjava_catkin_package_a/my_pub_sub_tutorial/msg/Cvmsg.msg"
  "${MSG_I_FLAGS}"
  "/opt/ros/kinetic/share/geometry_msgs/cmake/../msg/Quaternion.msg;/opt/ros/kinetic/share/geometry_msgs/cmake/../msg/Pose.msg;/opt/ros/kinetic/share/std_msgs/cmake/../msg/Header.msg;/opt/ros/kinetic/share/geometry_msgs/cmake/../msg/Point.msg"
  ${CATKIN_DEVEL_PREFIX}/${genjava_INSTALL_DIR}/rosjava_catkin_package_a
)
=======
>>>>>>> 26a60b4c68b3a2a261a9b928b81953632d92833b

### Generating Services

### Generating Module File
_generate_module_java(rosjava_catkin_package_a
  ${CATKIN_DEVEL_PREFIX}/${genjava_INSTALL_DIR}/rosjava_catkin_package_a
  "${ALL_GEN_OUTPUT_FILES_java}"
)

add_custom_target(rosjava_catkin_package_a_generate_messages_java
  DEPENDS ${ALL_GEN_OUTPUT_FILES_java}
)
add_dependencies(rosjava_catkin_package_a_generate_messages rosjava_catkin_package_a_generate_messages_java)

# add dependencies to all check dependencies targets
<<<<<<< HEAD
get_filename_component(_filename "/home/viktor/Desktop/3_sempro/MySqlROS/src/rosjava_catkin_package_a/my_pub_sub_tutorial/msg/Urmsg.msg" NAME_WE)
add_dependencies(rosjava_catkin_package_a_generate_messages_java _rosjava_catkin_package_a_generate_messages_check_deps_${_filename})
get_filename_component(_filename "/home/viktor/Desktop/3_sempro/MySqlROS/src/rosjava_catkin_package_a/my_pub_sub_tutorial/msg/Cvmsg.msg" NAME_WE)
add_dependencies(rosjava_catkin_package_a_generate_messages_java _rosjava_catkin_package_a_generate_messages_check_deps_${_filename})
=======
>>>>>>> 26a60b4c68b3a2a261a9b928b81953632d92833b

# target for backward compatibility
add_custom_target(rosjava_catkin_package_a_genjava)
add_dependencies(rosjava_catkin_package_a_genjava rosjava_catkin_package_a_generate_messages_java)

# register target for catkin_package(EXPORTED_TARGETS)
list(APPEND ${PROJECT_NAME}_EXPORTED_TARGETS rosjava_catkin_package_a_generate_messages_java)

### Section generating for lang: genlisp
### Generating Messages
<<<<<<< HEAD
_generate_msg_lisp(rosjava_catkin_package_a
  "/home/viktor/Desktop/3_sempro/MySqlROS/src/rosjava_catkin_package_a/my_pub_sub_tutorial/msg/Urmsg.msg"
  "${MSG_I_FLAGS}"
  ""
  ${CATKIN_DEVEL_PREFIX}/${genlisp_INSTALL_DIR}/rosjava_catkin_package_a
)
_generate_msg_lisp(rosjava_catkin_package_a
  "/home/viktor/Desktop/3_sempro/MySqlROS/src/rosjava_catkin_package_a/my_pub_sub_tutorial/msg/Cvmsg.msg"
  "${MSG_I_FLAGS}"
  "/opt/ros/kinetic/share/geometry_msgs/cmake/../msg/Quaternion.msg;/opt/ros/kinetic/share/geometry_msgs/cmake/../msg/Pose.msg;/opt/ros/kinetic/share/std_msgs/cmake/../msg/Header.msg;/opt/ros/kinetic/share/geometry_msgs/cmake/../msg/Point.msg"
  ${CATKIN_DEVEL_PREFIX}/${genlisp_INSTALL_DIR}/rosjava_catkin_package_a
)
=======
>>>>>>> 26a60b4c68b3a2a261a9b928b81953632d92833b

### Generating Services

### Generating Module File
_generate_module_lisp(rosjava_catkin_package_a
  ${CATKIN_DEVEL_PREFIX}/${genlisp_INSTALL_DIR}/rosjava_catkin_package_a
  "${ALL_GEN_OUTPUT_FILES_lisp}"
)

add_custom_target(rosjava_catkin_package_a_generate_messages_lisp
  DEPENDS ${ALL_GEN_OUTPUT_FILES_lisp}
)
add_dependencies(rosjava_catkin_package_a_generate_messages rosjava_catkin_package_a_generate_messages_lisp)

# add dependencies to all check dependencies targets
<<<<<<< HEAD
get_filename_component(_filename "/home/viktor/Desktop/3_sempro/MySqlROS/src/rosjava_catkin_package_a/my_pub_sub_tutorial/msg/Urmsg.msg" NAME_WE)
add_dependencies(rosjava_catkin_package_a_generate_messages_lisp _rosjava_catkin_package_a_generate_messages_check_deps_${_filename})
get_filename_component(_filename "/home/viktor/Desktop/3_sempro/MySqlROS/src/rosjava_catkin_package_a/my_pub_sub_tutorial/msg/Cvmsg.msg" NAME_WE)
add_dependencies(rosjava_catkin_package_a_generate_messages_lisp _rosjava_catkin_package_a_generate_messages_check_deps_${_filename})
=======
>>>>>>> 26a60b4c68b3a2a261a9b928b81953632d92833b

# target for backward compatibility
add_custom_target(rosjava_catkin_package_a_genlisp)
add_dependencies(rosjava_catkin_package_a_genlisp rosjava_catkin_package_a_generate_messages_lisp)

# register target for catkin_package(EXPORTED_TARGETS)
list(APPEND ${PROJECT_NAME}_EXPORTED_TARGETS rosjava_catkin_package_a_generate_messages_lisp)

### Section generating for lang: gennodejs
### Generating Messages
<<<<<<< HEAD
_generate_msg_nodejs(rosjava_catkin_package_a
  "/home/viktor/Desktop/3_sempro/MySqlROS/src/rosjava_catkin_package_a/my_pub_sub_tutorial/msg/Urmsg.msg"
  "${MSG_I_FLAGS}"
  ""
  ${CATKIN_DEVEL_PREFIX}/${gennodejs_INSTALL_DIR}/rosjava_catkin_package_a
)
_generate_msg_nodejs(rosjava_catkin_package_a
  "/home/viktor/Desktop/3_sempro/MySqlROS/src/rosjava_catkin_package_a/my_pub_sub_tutorial/msg/Cvmsg.msg"
  "${MSG_I_FLAGS}"
  "/opt/ros/kinetic/share/geometry_msgs/cmake/../msg/Quaternion.msg;/opt/ros/kinetic/share/geometry_msgs/cmake/../msg/Pose.msg;/opt/ros/kinetic/share/std_msgs/cmake/../msg/Header.msg;/opt/ros/kinetic/share/geometry_msgs/cmake/../msg/Point.msg"
  ${CATKIN_DEVEL_PREFIX}/${gennodejs_INSTALL_DIR}/rosjava_catkin_package_a
)
=======
>>>>>>> 26a60b4c68b3a2a261a9b928b81953632d92833b

### Generating Services

### Generating Module File
_generate_module_nodejs(rosjava_catkin_package_a
  ${CATKIN_DEVEL_PREFIX}/${gennodejs_INSTALL_DIR}/rosjava_catkin_package_a
  "${ALL_GEN_OUTPUT_FILES_nodejs}"
)

add_custom_target(rosjava_catkin_package_a_generate_messages_nodejs
  DEPENDS ${ALL_GEN_OUTPUT_FILES_nodejs}
)
add_dependencies(rosjava_catkin_package_a_generate_messages rosjava_catkin_package_a_generate_messages_nodejs)

# add dependencies to all check dependencies targets
<<<<<<< HEAD
get_filename_component(_filename "/home/viktor/Desktop/3_sempro/MySqlROS/src/rosjava_catkin_package_a/my_pub_sub_tutorial/msg/Urmsg.msg" NAME_WE)
add_dependencies(rosjava_catkin_package_a_generate_messages_nodejs _rosjava_catkin_package_a_generate_messages_check_deps_${_filename})
get_filename_component(_filename "/home/viktor/Desktop/3_sempro/MySqlROS/src/rosjava_catkin_package_a/my_pub_sub_tutorial/msg/Cvmsg.msg" NAME_WE)
add_dependencies(rosjava_catkin_package_a_generate_messages_nodejs _rosjava_catkin_package_a_generate_messages_check_deps_${_filename})
=======
>>>>>>> 26a60b4c68b3a2a261a9b928b81953632d92833b

# target for backward compatibility
add_custom_target(rosjava_catkin_package_a_gennodejs)
add_dependencies(rosjava_catkin_package_a_gennodejs rosjava_catkin_package_a_generate_messages_nodejs)

# register target for catkin_package(EXPORTED_TARGETS)
list(APPEND ${PROJECT_NAME}_EXPORTED_TARGETS rosjava_catkin_package_a_generate_messages_nodejs)

### Section generating for lang: genpy
### Generating Messages
<<<<<<< HEAD
_generate_msg_py(rosjava_catkin_package_a
  "/home/viktor/Desktop/3_sempro/MySqlROS/src/rosjava_catkin_package_a/my_pub_sub_tutorial/msg/Urmsg.msg"
  "${MSG_I_FLAGS}"
  ""
  ${CATKIN_DEVEL_PREFIX}/${genpy_INSTALL_DIR}/rosjava_catkin_package_a
)
_generate_msg_py(rosjava_catkin_package_a
  "/home/viktor/Desktop/3_sempro/MySqlROS/src/rosjava_catkin_package_a/my_pub_sub_tutorial/msg/Cvmsg.msg"
  "${MSG_I_FLAGS}"
  "/opt/ros/kinetic/share/geometry_msgs/cmake/../msg/Quaternion.msg;/opt/ros/kinetic/share/geometry_msgs/cmake/../msg/Pose.msg;/opt/ros/kinetic/share/std_msgs/cmake/../msg/Header.msg;/opt/ros/kinetic/share/geometry_msgs/cmake/../msg/Point.msg"
  ${CATKIN_DEVEL_PREFIX}/${genpy_INSTALL_DIR}/rosjava_catkin_package_a
)
=======
>>>>>>> 26a60b4c68b3a2a261a9b928b81953632d92833b

### Generating Services

### Generating Module File
_generate_module_py(rosjava_catkin_package_a
  ${CATKIN_DEVEL_PREFIX}/${genpy_INSTALL_DIR}/rosjava_catkin_package_a
  "${ALL_GEN_OUTPUT_FILES_py}"
)

add_custom_target(rosjava_catkin_package_a_generate_messages_py
  DEPENDS ${ALL_GEN_OUTPUT_FILES_py}
)
add_dependencies(rosjava_catkin_package_a_generate_messages rosjava_catkin_package_a_generate_messages_py)

# add dependencies to all check dependencies targets
<<<<<<< HEAD
get_filename_component(_filename "/home/viktor/Desktop/3_sempro/MySqlROS/src/rosjava_catkin_package_a/my_pub_sub_tutorial/msg/Urmsg.msg" NAME_WE)
add_dependencies(rosjava_catkin_package_a_generate_messages_py _rosjava_catkin_package_a_generate_messages_check_deps_${_filename})
get_filename_component(_filename "/home/viktor/Desktop/3_sempro/MySqlROS/src/rosjava_catkin_package_a/my_pub_sub_tutorial/msg/Cvmsg.msg" NAME_WE)
add_dependencies(rosjava_catkin_package_a_generate_messages_py _rosjava_catkin_package_a_generate_messages_check_deps_${_filename})
=======
>>>>>>> 26a60b4c68b3a2a261a9b928b81953632d92833b

# target for backward compatibility
add_custom_target(rosjava_catkin_package_a_genpy)
add_dependencies(rosjava_catkin_package_a_genpy rosjava_catkin_package_a_generate_messages_py)

# register target for catkin_package(EXPORTED_TARGETS)
list(APPEND ${PROJECT_NAME}_EXPORTED_TARGETS rosjava_catkin_package_a_generate_messages_py)



if(gencpp_INSTALL_DIR AND EXISTS ${CATKIN_DEVEL_PREFIX}/${gencpp_INSTALL_DIR}/rosjava_catkin_package_a)
  # install generated code
  install(
    DIRECTORY ${CATKIN_DEVEL_PREFIX}/${gencpp_INSTALL_DIR}/rosjava_catkin_package_a
    DESTINATION ${gencpp_INSTALL_DIR}
  )
endif()
if(TARGET std_msgs_generate_messages_cpp)
  add_dependencies(rosjava_catkin_package_a_generate_messages_cpp std_msgs_generate_messages_cpp)
endif()
<<<<<<< HEAD
if(TARGET geometry_msgs_generate_messages_cpp)
  add_dependencies(rosjava_catkin_package_a_generate_messages_cpp geometry_msgs_generate_messages_cpp)
endif()
=======
>>>>>>> 26a60b4c68b3a2a261a9b928b81953632d92833b

if(geneus_INSTALL_DIR AND EXISTS ${CATKIN_DEVEL_PREFIX}/${geneus_INSTALL_DIR}/rosjava_catkin_package_a)
  # install generated code
  install(
    DIRECTORY ${CATKIN_DEVEL_PREFIX}/${geneus_INSTALL_DIR}/rosjava_catkin_package_a
    DESTINATION ${geneus_INSTALL_DIR}
  )
endif()
if(TARGET std_msgs_generate_messages_eus)
  add_dependencies(rosjava_catkin_package_a_generate_messages_eus std_msgs_generate_messages_eus)
endif()
<<<<<<< HEAD
if(TARGET geometry_msgs_generate_messages_eus)
  add_dependencies(rosjava_catkin_package_a_generate_messages_eus geometry_msgs_generate_messages_eus)
endif()
=======
>>>>>>> 26a60b4c68b3a2a261a9b928b81953632d92833b

if(genjava_INSTALL_DIR AND EXISTS ${CATKIN_DEVEL_PREFIX}/${genjava_INSTALL_DIR}/rosjava_catkin_package_a)
  # install generated code
  install(
    DIRECTORY ${CATKIN_DEVEL_PREFIX}/${genjava_INSTALL_DIR}/rosjava_catkin_package_a
    DESTINATION ${genjava_INSTALL_DIR}
  )
endif()
if(TARGET std_msgs_generate_messages_java)
  add_dependencies(rosjava_catkin_package_a_generate_messages_java std_msgs_generate_messages_java)
endif()
<<<<<<< HEAD
if(TARGET geometry_msgs_generate_messages_java)
  add_dependencies(rosjava_catkin_package_a_generate_messages_java geometry_msgs_generate_messages_java)
endif()
=======
>>>>>>> 26a60b4c68b3a2a261a9b928b81953632d92833b

if(genlisp_INSTALL_DIR AND EXISTS ${CATKIN_DEVEL_PREFIX}/${genlisp_INSTALL_DIR}/rosjava_catkin_package_a)
  # install generated code
  install(
    DIRECTORY ${CATKIN_DEVEL_PREFIX}/${genlisp_INSTALL_DIR}/rosjava_catkin_package_a
    DESTINATION ${genlisp_INSTALL_DIR}
  )
endif()
if(TARGET std_msgs_generate_messages_lisp)
  add_dependencies(rosjava_catkin_package_a_generate_messages_lisp std_msgs_generate_messages_lisp)
endif()
<<<<<<< HEAD
if(TARGET geometry_msgs_generate_messages_lisp)
  add_dependencies(rosjava_catkin_package_a_generate_messages_lisp geometry_msgs_generate_messages_lisp)
endif()
=======
>>>>>>> 26a60b4c68b3a2a261a9b928b81953632d92833b

if(gennodejs_INSTALL_DIR AND EXISTS ${CATKIN_DEVEL_PREFIX}/${gennodejs_INSTALL_DIR}/rosjava_catkin_package_a)
  # install generated code
  install(
    DIRECTORY ${CATKIN_DEVEL_PREFIX}/${gennodejs_INSTALL_DIR}/rosjava_catkin_package_a
    DESTINATION ${gennodejs_INSTALL_DIR}
  )
endif()
if(TARGET std_msgs_generate_messages_nodejs)
  add_dependencies(rosjava_catkin_package_a_generate_messages_nodejs std_msgs_generate_messages_nodejs)
endif()
<<<<<<< HEAD
if(TARGET geometry_msgs_generate_messages_nodejs)
  add_dependencies(rosjava_catkin_package_a_generate_messages_nodejs geometry_msgs_generate_messages_nodejs)
endif()
=======
>>>>>>> 26a60b4c68b3a2a261a9b928b81953632d92833b

if(genpy_INSTALL_DIR AND EXISTS ${CATKIN_DEVEL_PREFIX}/${genpy_INSTALL_DIR}/rosjava_catkin_package_a)
  install(CODE "execute_process(COMMAND \"/usr/bin/python\" -m compileall \"${CATKIN_DEVEL_PREFIX}/${genpy_INSTALL_DIR}/rosjava_catkin_package_a\")")
  # install generated code
  install(
    DIRECTORY ${CATKIN_DEVEL_PREFIX}/${genpy_INSTALL_DIR}/rosjava_catkin_package_a
    DESTINATION ${genpy_INSTALL_DIR}
  )
endif()
if(TARGET std_msgs_generate_messages_py)
  add_dependencies(rosjava_catkin_package_a_generate_messages_py std_msgs_generate_messages_py)
endif()
<<<<<<< HEAD
if(TARGET geometry_msgs_generate_messages_py)
  add_dependencies(rosjava_catkin_package_a_generate_messages_py geometry_msgs_generate_messages_py)
endif()
=======
>>>>>>> 26a60b4c68b3a2a261a9b928b81953632d92833b
