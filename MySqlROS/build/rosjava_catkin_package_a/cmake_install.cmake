# Install script for directory: /home/viktor/Desktop/3_sempro/MySqlROS/src/rosjava_catkin_package_a

# Set the install prefix
if(NOT DEFINED CMAKE_INSTALL_PREFIX)
  set(CMAKE_INSTALL_PREFIX "/home/viktor/Desktop/3_sempro/MySqlROS/install")
endif()
string(REGEX REPLACE "/$" "" CMAKE_INSTALL_PREFIX "${CMAKE_INSTALL_PREFIX}")

# Set the install configuration name.
if(NOT DEFINED CMAKE_INSTALL_CONFIG_NAME)
  if(BUILD_TYPE)
    string(REGEX REPLACE "^[^A-Za-z0-9_]+" ""
           CMAKE_INSTALL_CONFIG_NAME "${BUILD_TYPE}")
  else()
    set(CMAKE_INSTALL_CONFIG_NAME "Debug")
  endif()
  message(STATUS "Install configuration: \"${CMAKE_INSTALL_CONFIG_NAME}\"")
endif()

# Set the component getting installed.
if(NOT CMAKE_INSTALL_COMPONENT)
  if(COMPONENT)
    message(STATUS "Install component: \"${COMPONENT}\"")
    set(CMAKE_INSTALL_COMPONENT "${COMPONENT}")
  else()
    set(CMAKE_INSTALL_COMPONENT)
  endif()
endif()

# Install shared libraries without execute permission?
if(NOT DEFINED CMAKE_INSTALL_SO_NO_EXE)
  set(CMAKE_INSTALL_SO_NO_EXE "1")
endif()

if(NOT CMAKE_INSTALL_COMPONENT OR "${CMAKE_INSTALL_COMPONENT}" STREQUAL "Unspecified")
  file(INSTALL DESTINATION "${CMAKE_INSTALL_PREFIX}/share/rosjava_catkin_package_a/my_pub_sub_tutorial/msg" TYPE FILE FILES
    "/home/viktor/Desktop/3_sempro/MySqlROS/src/rosjava_catkin_package_a/my_pub_sub_tutorial/msg/Cvmsg.msg"
    "/home/viktor/Desktop/3_sempro/MySqlROS/src/rosjava_catkin_package_a/my_pub_sub_tutorial/msg/Urmsg.msg"
    )
endif()

if(NOT CMAKE_INSTALL_COMPONENT OR "${CMAKE_INSTALL_COMPONENT}" STREQUAL "Unspecified")
  file(INSTALL DESTINATION "${CMAKE_INSTALL_PREFIX}/share/rosjava_catkin_package_a/cmake" TYPE FILE FILES "/home/viktor/Desktop/3_sempro/MySqlROS/build/rosjava_catkin_package_a/catkin_generated/installspace/rosjava_catkin_package_a-msg-paths.cmake")
endif()

if(NOT CMAKE_INSTALL_COMPONENT OR "${CMAKE_INSTALL_COMPONENT}" STREQUAL "Unspecified")
  file(INSTALL DESTINATION "${CMAKE_INSTALL_PREFIX}/include" TYPE DIRECTORY FILES "/home/viktor/Desktop/3_sempro/MySqlROS/devel/include/rosjava_catkin_package_a")
endif()

if(NOT CMAKE_INSTALL_COMPONENT OR "${CMAKE_INSTALL_COMPONENT}" STREQUAL "Unspecified")
  file(INSTALL DESTINATION "${CMAKE_INSTALL_PREFIX}/share/roseus/ros" TYPE DIRECTORY FILES "/home/viktor/Desktop/3_sempro/MySqlROS/devel/share/roseus/ros/rosjava_catkin_package_a")
endif()

if(NOT CMAKE_INSTALL_COMPONENT OR "${CMAKE_INSTALL_COMPONENT}" STREQUAL "Unspecified")
  file(INSTALL DESTINATION "${CMAKE_INSTALL_PREFIX}/share/common-lisp/ros" TYPE DIRECTORY FILES "/home/viktor/Desktop/3_sempro/MySqlROS/devel/share/common-lisp/ros/rosjava_catkin_package_a")
endif()

if(NOT CMAKE_INSTALL_COMPONENT OR "${CMAKE_INSTALL_COMPONENT}" STREQUAL "Unspecified")
  file(INSTALL DESTINATION "${CMAKE_INSTALL_PREFIX}/share/gennodejs/ros" TYPE DIRECTORY FILES "/home/viktor/Desktop/3_sempro/MySqlROS/devel/share/gennodejs/ros/rosjava_catkin_package_a")
endif()

if(NOT CMAKE_INSTALL_COMPONENT OR "${CMAKE_INSTALL_COMPONENT}" STREQUAL "Unspecified")
  execute_process(COMMAND "/usr/bin/python" -m compileall "/home/viktor/Desktop/3_sempro/MySqlROS/devel/lib/python2.7/dist-packages/rosjava_catkin_package_a")
endif()

if(NOT CMAKE_INSTALL_COMPONENT OR "${CMAKE_INSTALL_COMPONENT}" STREQUAL "Unspecified")
  file(INSTALL DESTINATION "${CMAKE_INSTALL_PREFIX}/lib/python2.7/dist-packages" TYPE DIRECTORY FILES "/home/viktor/Desktop/3_sempro/MySqlROS/devel/lib/python2.7/dist-packages/rosjava_catkin_package_a")
endif()

if(NOT CMAKE_INSTALL_COMPONENT OR "${CMAKE_INSTALL_COMPONENT}" STREQUAL "Unspecified")
  file(INSTALL DESTINATION "${CMAKE_INSTALL_PREFIX}/lib/pkgconfig" TYPE FILE FILES "/home/viktor/Desktop/3_sempro/MySqlROS/build/rosjava_catkin_package_a/catkin_generated/installspace/rosjava_catkin_package_a.pc")
endif()

if(NOT CMAKE_INSTALL_COMPONENT OR "${CMAKE_INSTALL_COMPONENT}" STREQUAL "Unspecified")
  file(INSTALL DESTINATION "${CMAKE_INSTALL_PREFIX}/share/rosjava_catkin_package_a/cmake" TYPE FILE FILES "/home/viktor/Desktop/3_sempro/MySqlROS/build/rosjava_catkin_package_a/catkin_generated/installspace/rosjava_catkin_package_a-msg-extras.cmake")
endif()

if(NOT CMAKE_INSTALL_COMPONENT OR "${CMAKE_INSTALL_COMPONENT}" STREQUAL "Unspecified")
  file(INSTALL DESTINATION "${CMAKE_INSTALL_PREFIX}/share/rosjava_catkin_package_a/cmake" TYPE FILE FILES
    "/home/viktor/Desktop/3_sempro/MySqlROS/build/rosjava_catkin_package_a/catkin_generated/installspace/rosjava_catkin_package_aConfig.cmake"
    "/home/viktor/Desktop/3_sempro/MySqlROS/build/rosjava_catkin_package_a/catkin_generated/installspace/rosjava_catkin_package_aConfig-version.cmake"
    )
endif()

if(NOT CMAKE_INSTALL_COMPONENT OR "${CMAKE_INSTALL_COMPONENT}" STREQUAL "Unspecified")
  file(INSTALL DESTINATION "${CMAKE_INSTALL_PREFIX}/share/rosjava_catkin_package_a" TYPE FILE FILES "/home/viktor/Desktop/3_sempro/MySqlROS/src/rosjava_catkin_package_a/package.xml")
endif()

if(NOT CMAKE_INSTALL_COMPONENT OR "${CMAKE_INSTALL_COMPONENT}" STREQUAL "Unspecified")
  file(INSTALL DESTINATION "${CMAKE_INSTALL_PREFIX}/share/maven/com/github/rosjava/rosjava_catkin_package_a" TYPE DIRECTORY FILES "/home/viktor/Desktop/3_sempro/MySqlROS/devel/share/maven/com/github/rosjava/rosjava_catkin_package_a/")
endif()

