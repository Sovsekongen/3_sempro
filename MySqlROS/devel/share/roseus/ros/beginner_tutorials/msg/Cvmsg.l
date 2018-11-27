;; Auto-generated. Do not edit!


(when (boundp 'beginner_tutorials::Cvmsg)
  (if (not (find-package "BEGINNER_TUTORIALS"))
    (make-package "BEGINNER_TUTORIALS"))
  (shadow 'Cvmsg (find-package "BEGINNER_TUTORIALS")))
(unless (find-package "BEGINNER_TUTORIALS::CVMSG")
  (make-package "BEGINNER_TUTORIALS::CVMSG"))

(in-package "ROS")
;;//! \htmlinclude Cvmsg.msg.html
(if (not (find-package "GEOMETRY_MSGS"))
  (ros::roseus-add-msgs "geometry_msgs"))
(if (not (find-package "STD_MSGS"))
  (ros::roseus-add-msgs "std_msgs"))


(defclass beginner_tutorials::Cvmsg
  :super ros::object
  :slots (_header _pose _color _shape _pic _radius _exeTime ))

(defmethod beginner_tutorials::Cvmsg
  (:init
   (&key
    ((:header __header) (instance std_msgs::Header :init))
    ((:pose __pose) (instance geometry_msgs::Pose :init))
    ((:color __color) "")
    ((:shape __shape) "")
    ((:pic __pic) "")
    ((:radius __radius) 0)
    ((:exeTime __exeTime) 0)
    )
   (send-super :init)
   (setq _header __header)
   (setq _pose __pose)
   (setq _color (string __color))
   (setq _shape (string __shape))
   (setq _pic (string __pic))
   (setq _radius (round __radius))
   (setq _exeTime (round __exeTime))
   self)
  (:header
   (&rest __header)
   (if (keywordp (car __header))
       (send* _header __header)
     (progn
       (if __header (setq _header (car __header)))
       _header)))
  (:pose
   (&rest __pose)
   (if (keywordp (car __pose))
       (send* _pose __pose)
     (progn
       (if __pose (setq _pose (car __pose)))
       _pose)))
  (:color
   (&optional __color)
   (if __color (setq _color __color)) _color)
  (:shape
   (&optional __shape)
   (if __shape (setq _shape __shape)) _shape)
  (:pic
   (&optional __pic)
   (if __pic (setq _pic __pic)) _pic)
  (:radius
   (&optional __radius)
   (if __radius (setq _radius __radius)) _radius)
  (:exeTime
   (&optional __exeTime)
   (if __exeTime (setq _exeTime __exeTime)) _exeTime)
  (:serialization-length
   ()
   (+
    ;; std_msgs/Header _header
    (send _header :serialization-length)
    ;; geometry_msgs/Pose _pose
    (send _pose :serialization-length)
    ;; string _color
    4 (length _color)
    ;; string _shape
    4 (length _shape)
    ;; string _pic
    4 (length _pic)
    ;; uint32 _radius
    4
    ;; uint32 _exeTime
    4
    ))
  (:serialize
   (&optional strm)
   (let ((s (if strm strm
              (make-string-output-stream (send self :serialization-length)))))
     ;; std_msgs/Header _header
       (send _header :serialize s)
     ;; geometry_msgs/Pose _pose
       (send _pose :serialize s)
     ;; string _color
       (write-long (length _color) s) (princ _color s)
     ;; string _shape
       (write-long (length _shape) s) (princ _shape s)
     ;; string _pic
       (write-long (length _pic) s) (princ _pic s)
     ;; uint32 _radius
       (write-long _radius s)
     ;; uint32 _exeTime
       (write-long _exeTime s)
     ;;
     (if (null strm) (get-output-stream-string s))))
  (:deserialize
   (buf &optional (ptr- 0))
   ;; std_msgs/Header _header
     (send _header :deserialize buf ptr-) (incf ptr- (send _header :serialization-length))
   ;; geometry_msgs/Pose _pose
     (send _pose :deserialize buf ptr-) (incf ptr- (send _pose :serialization-length))
   ;; string _color
     (let (n) (setq n (sys::peek buf ptr- :integer)) (incf ptr- 4) (setq _color (subseq buf ptr- (+ ptr- n))) (incf ptr- n))
   ;; string _shape
     (let (n) (setq n (sys::peek buf ptr- :integer)) (incf ptr- 4) (setq _shape (subseq buf ptr- (+ ptr- n))) (incf ptr- n))
   ;; string _pic
     (let (n) (setq n (sys::peek buf ptr- :integer)) (incf ptr- 4) (setq _pic (subseq buf ptr- (+ ptr- n))) (incf ptr- n))
   ;; uint32 _radius
     (setq _radius (sys::peek buf ptr- :integer)) (incf ptr- 4)
   ;; uint32 _exeTime
     (setq _exeTime (sys::peek buf ptr- :integer)) (incf ptr- 4)
   ;;
   self)
  )

(setf (get beginner_tutorials::Cvmsg :md5sum-) "76c45fb61e7f729ef565aaafd3f52a41")
(setf (get beginner_tutorials::Cvmsg :datatype-) "beginner_tutorials/Cvmsg")
(setf (get beginner_tutorials::Cvmsg :definition-)
      "Header header
geometry_msgs/Pose pose
string color
string shape
string pic
uint32 radius
uint32 exeTime

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

================================================================================
MSG: geometry_msgs/Pose
# A representation of pose in free space, composed of position and orientation. 
Point position
Quaternion orientation

================================================================================
MSG: geometry_msgs/Point
# This contains the position of a point in free space
float64 x
float64 y
float64 z

================================================================================
MSG: geometry_msgs/Quaternion
# This represents an orientation in free space in quaternion form.

float64 x
float64 y
float64 z
float64 w

")



(provide :beginner_tutorials/Cvmsg "76c45fb61e7f729ef565aaafd3f52a41")


