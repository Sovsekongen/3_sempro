file(REMOVE_RECURSE
  "std_msgs"
  "ros_comm_msgs"
  "rosjava_test_msgs"
  "actionlib_msgs"
)

# Per-language clean rules from dependency scanning.
foreach(lang )
  include(CMakeFiles/rosgraph_msgs_generate_messages_cpp.dir/cmake_clean_${lang}.cmake OPTIONAL)
endforeach()
