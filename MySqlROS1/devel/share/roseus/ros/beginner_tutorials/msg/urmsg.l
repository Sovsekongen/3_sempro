;; Auto-generated. Do not edit!


(when (boundp 'beginner_tutorials::urmsg)
  (if (not (find-package "BEGINNER_TUTORIALS"))
    (make-package "BEGINNER_TUTORIALS"))
  (shadow 'urmsg (find-package "BEGINNER_TUTORIALS")))
(unless (find-package "BEGINNER_TUTORIALS::URMSG")
  (make-package "BEGINNER_TUTORIALS::URMSG"))

(in-package "ROS")
;;//! \htmlinclude urmsg.msg.html
(if (not (find-package "STD_MSGS"))
  (ros::roseus-add-msgs "std_msgs"))


(defclass beginner_tutorials::urmsg
  :super ros::object
  :slots (_header _pickUpTime _throwTime ))

(defmethod beginner_tutorials::urmsg
  (:init
   (&key
    ((:header __header) (instance std_msgs::Header :init))
    ((:pickUpTime __pickUpTime) 0)
    ((:throwTime __throwTime) 0)
    )
   (send-super :init)
   (setq _header __header)
   (setq _pickUpTime (round __pickUpTime))
   (setq _throwTime (round __throwTime))
   self)
  (:header
   (&rest __header)
   (if (keywordp (car __header))
       (send* _header __header)
     (progn
       (if __header (setq _header (car __header)))
       _header)))
  (:pickUpTime
   (&optional __pickUpTime)
   (if __pickUpTime (setq _pickUpTime __pickUpTime)) _pickUpTime)
  (:throwTime
   (&optional __throwTime)
   (if __throwTime (setq _throwTime __throwTime)) _throwTime)
  (:serialization-length
   ()
   (+
    ;; std_msgs/Header _header
    (send _header :serialization-length)
    ;; uint32 _pickUpTime
    4
    ;; uint32 _throwTime
    4
    ))
  (:serialize
   (&optional strm)
   (let ((s (if strm strm
              (make-string-output-stream (send self :serialization-length)))))
     ;; std_msgs/Header _header
       (send _header :serialize s)
     ;; uint32 _pickUpTime
       (write-long _pickUpTime s)
     ;; uint32 _throwTime
       (write-long _throwTime s)
     ;;
     (if (null strm) (get-output-stream-string s))))
  (:deserialize
   (buf &optional (ptr- 0))
   ;; std_msgs/Header _header
     (send _header :deserialize buf ptr-) (incf ptr- (send _header :serialization-length))
   ;; uint32 _pickUpTime
     (setq _pickUpTime (sys::peek buf ptr- :integer)) (incf ptr- 4)
   ;; uint32 _throwTime
     (setq _throwTime (sys::peek buf ptr- :integer)) (incf ptr- 4)
   ;;
   self)
  )

(setf (get beginner_tutorials::urmsg :md5sum-) "a2e9cc88ef89af17cbcd6d11a52269cb")
(setf (get beginner_tutorials::urmsg :datatype-) "beginner_tutorials/urmsg")
(setf (get beginner_tutorials::urmsg :definition-)
      "Header header
uint32 pickUpTime
uint32 throwTime

================================================================================
MSG: std_msgs/Header
# Standard metadata for higher-level stamped data types.
# This is generally used to communicate timestamped data 
# in a particular coordinate frame.
# 
# sequence ID: consecutively increasing ID 
uint32 seq
#Two-integer timestamp that is expressed as:
# * stamp.sec: seconds (stamp_secs) since epoch (in Python the variable is called 'secs')
# * stamp.nsec: nanoseconds since stamp_secs (in Python the variable is called 'nsecs')
# time-handling sugar is provided by the client library
time stamp
#Frame this data is associated with
# 0: no frame
# 1: global frame
string frame_id

")



(provide :beginner_tutorials/urmsg "a2e9cc88ef89af17cbcd6d11a52269cb")


