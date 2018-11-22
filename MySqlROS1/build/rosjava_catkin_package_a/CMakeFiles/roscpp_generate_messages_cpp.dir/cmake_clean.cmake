<<<<<<< HEAD
file(REMOVE_RECURSE
  "std_msgs"
  "ros_comm_msgs"
  "rosjava_test_msgs"
  "actionlib_msgs"
)
=======
>>>>>>> 26a60b4c68b3a2a261a9b928b81953632d92833b

# Per-language clean rules from dependency scanning.
foreach(lang )
  include(CMakeFiles/roscpp_generate_messages_cpp.dir/cmake_clean_${lang}.cmake OPTIONAL)
endforeach()
