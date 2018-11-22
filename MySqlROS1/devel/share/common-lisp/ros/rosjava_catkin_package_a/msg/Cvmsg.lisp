; Auto-generated. Do not edit!


(cl:in-package rosjava_catkin_package_a-msg)


;//! \htmlinclude Cvmsg.msg.html

(cl:defclass <Cvmsg> (roslisp-msg-protocol:ros-message)
  ((header
    :reader header
    :initarg :header
    :type std_msgs-msg:Header
    :initform (cl:make-instance 'std_msgs-msg:Header))
   (pose
    :reader pose
    :initarg :pose
    :type geometry_msgs-msg:Pose
    :initform (cl:make-instance 'geometry_msgs-msg:Pose))
   (color
    :reader color
    :initarg :color
    :type cl:string
    :initform "")
   (shape
    :reader shape
    :initarg :shape
    :type cl:string
    :initform "")
   (pic
    :reader pic
    :initarg :pic
    :type cl:string
    :initform "")
   (radius
    :reader radius
    :initarg :radius
    :type cl:float
    :initform 0.0)
   (exeTime
    :reader exeTime
    :initarg :exeTime
    :type cl:float
    :initform 0.0))
)

(cl:defclass Cvmsg (<Cvmsg>)
  ())

(cl:defmethod cl:initialize-instance :after ((m <Cvmsg>) cl:&rest args)
  (cl:declare (cl:ignorable args))
  (cl:unless (cl:typep m 'Cvmsg)
    (roslisp-msg-protocol:msg-deprecation-warning "using old message class name rosjava_catkin_package_a-msg:<Cvmsg> is deprecated: use rosjava_catkin_package_a-msg:Cvmsg instead.")))

(cl:ensure-generic-function 'header-val :lambda-list '(m))
(cl:defmethod header-val ((m <Cvmsg>))
  (roslisp-msg-protocol:msg-deprecation-warning "Using old-style slot reader rosjava_catkin_package_a-msg:header-val is deprecated.  Use rosjava_catkin_package_a-msg:header instead.")
  (header m))

(cl:ensure-generic-function 'pose-val :lambda-list '(m))
(cl:defmethod pose-val ((m <Cvmsg>))
  (roslisp-msg-protocol:msg-deprecation-warning "Using old-style slot reader rosjava_catkin_package_a-msg:pose-val is deprecated.  Use rosjava_catkin_package_a-msg:pose instead.")
  (pose m))

(cl:ensure-generic-function 'color-val :lambda-list '(m))
(cl:defmethod color-val ((m <Cvmsg>))
  (roslisp-msg-protocol:msg-deprecation-warning "Using old-style slot reader rosjava_catkin_package_a-msg:color-val is deprecated.  Use rosjava_catkin_package_a-msg:color instead.")
  (color m))

(cl:ensure-generic-function 'shape-val :lambda-list '(m))
(cl:defmethod shape-val ((m <Cvmsg>))
  (roslisp-msg-protocol:msg-deprecation-warning "Using old-style slot reader rosjava_catkin_package_a-msg:shape-val is deprecated.  Use rosjava_catkin_package_a-msg:shape instead.")
  (shape m))

(cl:ensure-generic-function 'pic-val :lambda-list '(m))
(cl:defmethod pic-val ((m <Cvmsg>))
  (roslisp-msg-protocol:msg-deprecation-warning "Using old-style slot reader rosjava_catkin_package_a-msg:pic-val is deprecated.  Use rosjava_catkin_package_a-msg:pic instead.")
  (pic m))

(cl:ensure-generic-function 'radius-val :lambda-list '(m))
(cl:defmethod radius-val ((m <Cvmsg>))
  (roslisp-msg-protocol:msg-deprecation-warning "Using old-style slot reader rosjava_catkin_package_a-msg:radius-val is deprecated.  Use rosjava_catkin_package_a-msg:radius instead.")
  (radius m))

(cl:ensure-generic-function 'exeTime-val :lambda-list '(m))
(cl:defmethod exeTime-val ((m <Cvmsg>))
  (roslisp-msg-protocol:msg-deprecation-warning "Using old-style slot reader rosjava_catkin_package_a-msg:exeTime-val is deprecated.  Use rosjava_catkin_package_a-msg:exeTime instead.")
  (exeTime m))
(cl:defmethod roslisp-msg-protocol:serialize ((msg <Cvmsg>) ostream)
  "Serializes a message object of type '<Cvmsg>"
  (roslisp-msg-protocol:serialize (cl:slot-value msg 'header) ostream)
  (roslisp-msg-protocol:serialize (cl:slot-value msg 'pose) ostream)
  (cl:let ((__ros_str_len (cl:length (cl:slot-value msg 'color))))
    (cl:write-byte (cl:ldb (cl:byte 8 0) __ros_str_len) ostream)
    (cl:write-byte (cl:ldb (cl:byte 8 8) __ros_str_len) ostream)
    (cl:write-byte (cl:ldb (cl:byte 8 16) __ros_str_len) ostream)
    (cl:write-byte (cl:ldb (cl:byte 8 24) __ros_str_len) ostream))
  (cl:map cl:nil #'(cl:lambda (c) (cl:write-byte (cl:char-code c) ostream)) (cl:slot-value msg 'color))
  (cl:let ((__ros_str_len (cl:length (cl:slot-value msg 'shape))))
    (cl:write-byte (cl:ldb (cl:byte 8 0) __ros_str_len) ostream)
    (cl:write-byte (cl:ldb (cl:byte 8 8) __ros_str_len) ostream)
    (cl:write-byte (cl:ldb (cl:byte 8 16) __ros_str_len) ostream)
    (cl:write-byte (cl:ldb (cl:byte 8 24) __ros_str_len) ostream))
  (cl:map cl:nil #'(cl:lambda (c) (cl:write-byte (cl:char-code c) ostream)) (cl:slot-value msg 'shape))
  (cl:let ((__ros_str_len (cl:length (cl:slot-value msg 'pic))))
    (cl:write-byte (cl:ldb (cl:byte 8 0) __ros_str_len) ostream)
    (cl:write-byte (cl:ldb (cl:byte 8 8) __ros_str_len) ostream)
    (cl:write-byte (cl:ldb (cl:byte 8 16) __ros_str_len) ostream)
    (cl:write-byte (cl:ldb (cl:byte 8 24) __ros_str_len) ostream))
  (cl:map cl:nil #'(cl:lambda (c) (cl:write-byte (cl:char-code c) ostream)) (cl:slot-value msg 'pic))
  (cl:let ((bits (roslisp-utils:encode-single-float-bits (cl:slot-value msg 'radius))))
    (cl:write-byte (cl:ldb (cl:byte 8 0) bits) ostream)
    (cl:write-byte (cl:ldb (cl:byte 8 8) bits) ostream)
    (cl:write-byte (cl:ldb (cl:byte 8 16) bits) ostream)
    (cl:write-byte (cl:ldb (cl:byte 8 24) bits) ostream))
  (cl:let ((bits (roslisp-utils:encode-single-float-bits (cl:slot-value msg 'exeTime))))
    (cl:write-byte (cl:ldb (cl:byte 8 0) bits) ostream)
    (cl:write-byte (cl:ldb (cl:byte 8 8) bits) ostream)
    (cl:write-byte (cl:ldb (cl:byte 8 16) bits) ostream)
    (cl:write-byte (cl:ldb (cl:byte 8 24) bits) ostream))
)
(cl:defmethod roslisp-msg-protocol:deserialize ((msg <Cvmsg>) istream)
  "Deserializes a message object of type '<Cvmsg>"
  (roslisp-msg-protocol:deserialize (cl:slot-value msg 'header) istream)
  (roslisp-msg-protocol:deserialize (cl:slot-value msg 'pose) istream)
    (cl:let ((__ros_str_len 0))
      (cl:setf (cl:ldb (cl:byte 8 0) __ros_str_len) (cl:read-byte istream))
      (cl:setf (cl:ldb (cl:byte 8 8) __ros_str_len) (cl:read-byte istream))
      (cl:setf (cl:ldb (cl:byte 8 16) __ros_str_len) (cl:read-byte istream))
      (cl:setf (cl:ldb (cl:byte 8 24) __ros_str_len) (cl:read-byte istream))
      (cl:setf (cl:slot-value msg 'color) (cl:make-string __ros_str_len))
      (cl:dotimes (__ros_str_idx __ros_str_len msg)
        (cl:setf (cl:char (cl:slot-value msg 'color) __ros_str_idx) (cl:code-char (cl:read-byte istream)))))
    (cl:let ((__ros_str_len 0))
      (cl:setf (cl:ldb (cl:byte 8 0) __ros_str_len) (cl:read-byte istream))
      (cl:setf (cl:ldb (cl:byte 8 8) __ros_str_len) (cl:read-byte istream))
      (cl:setf (cl:ldb (cl:byte 8 16) __ros_str_len) (cl:read-byte istream))
      (cl:setf (cl:ldb (cl:byte 8 24) __ros_str_len) (cl:read-byte istream))
      (cl:setf (cl:slot-value msg 'shape) (cl:make-string __ros_str_len))
      (cl:dotimes (__ros_str_idx __ros_str_len msg)
        (cl:setf (cl:char (cl:slot-value msg 'shape) __ros_str_idx) (cl:code-char (cl:read-byte istream)))))
    (cl:let ((__ros_str_len 0))
      (cl:setf (cl:ldb (cl:byte 8 0) __ros_str_len) (cl:read-byte istream))
      (cl:setf (cl:ldb (cl:byte 8 8) __ros_str_len) (cl:read-byte istream))
      (cl:setf (cl:ldb (cl:byte 8 16) __ros_str_len) (cl:read-byte istream))
      (cl:setf (cl:ldb (cl:byte 8 24) __ros_str_len) (cl:read-byte istream))
      (cl:setf (cl:slot-value msg 'pic) (cl:make-string __ros_str_len))
      (cl:dotimes (__ros_str_idx __ros_str_len msg)
        (cl:setf (cl:char (cl:slot-value msg 'pic) __ros_str_idx) (cl:code-char (cl:read-byte istream)))))
    (cl:let ((bits 0))
      (cl:setf (cl:ldb (cl:byte 8 0) bits) (cl:read-byte istream))
      (cl:setf (cl:ldb (cl:byte 8 8) bits) (cl:read-byte istream))
      (cl:setf (cl:ldb (cl:byte 8 16) bits) (cl:read-byte istream))
      (cl:setf (cl:ldb (cl:byte 8 24) bits) (cl:read-byte istream))
    (cl:setf (cl:slot-value msg 'radius) (roslisp-utils:decode-single-float-bits bits)))
    (cl:let ((bits 0))
      (cl:setf (cl:ldb (cl:byte 8 0) bits) (cl:read-byte istream))
      (cl:setf (cl:ldb (cl:byte 8 8) bits) (cl:read-byte istream))
      (cl:setf (cl:ldb (cl:byte 8 16) bits) (cl:read-byte istream))
      (cl:setf (cl:ldb (cl:byte 8 24) bits) (cl:read-byte istream))
    (cl:setf (cl:slot-value msg 'exeTime) (roslisp-utils:decode-single-float-bits bits)))
  msg
)
(cl:defmethod roslisp-msg-protocol:ros-datatype ((msg (cl:eql '<Cvmsg>)))
  "Returns string type for a message object of type '<Cvmsg>"
  "rosjava_catkin_package_a/Cvmsg")
(cl:defmethod roslisp-msg-protocol:ros-datatype ((msg (cl:eql 'Cvmsg)))
  "Returns string type for a message object of type 'Cvmsg"
  "rosjava_catkin_package_a/Cvmsg")
(cl:defmethod roslisp-msg-protocol:md5sum ((type (cl:eql '<Cvmsg>)))
  "Returns md5sum for a message object of type '<Cvmsg>"
  "3a0d5b8744bf7210b3fb7f726b3cf141")
(cl:defmethod roslisp-msg-protocol:md5sum ((type (cl:eql 'Cvmsg)))
  "Returns md5sum for a message object of type 'Cvmsg"
  "3a0d5b8744bf7210b3fb7f726b3cf141")
(cl:defmethod roslisp-msg-protocol:message-definition ((type (cl:eql '<Cvmsg>)))
  "Returns full string definition for message of type '<Cvmsg>"
  (cl:format cl:nil "Header header~%geometry_msgs/Pose pose~%string color~%string shape~%string pic~%float32 radius~%float32 exeTime~%~%================================================================================~%MSG: std_msgs/Header~%# Standard metadata for higher-level stamped data types.~%# This is generally used to communicate timestamped data ~%# in a particular coordinate frame.~%# ~%# sequence ID: consecutively increasing ID ~%uint32 seq~%#Two-integer timestamp that is expressed as:~%# * stamp.sec: seconds (stamp_secs) since epoch (in Python the variable is called 'secs')~%# * stamp.nsec: nanoseconds since stamp_secs (in Python the variable is called 'nsecs')~%# time-handling sugar is provided by the client library~%time stamp~%#Frame this data is associated with~%# 0: no frame~%# 1: global frame~%string frame_id~%~%================================================================================~%MSG: geometry_msgs/Pose~%# A representation of pose in free space, composed of position and orientation. ~%Point position~%Quaternion orientation~%~%================================================================================~%MSG: geometry_msgs/Point~%# This contains the position of a point in free space~%float64 x~%float64 y~%float64 z~%~%================================================================================~%MSG: geometry_msgs/Quaternion~%# This represents an orientation in free space in quaternion form.~%~%float64 x~%float64 y~%float64 z~%float64 w~%~%~%"))
(cl:defmethod roslisp-msg-protocol:message-definition ((type (cl:eql 'Cvmsg)))
  "Returns full string definition for message of type 'Cvmsg"
  (cl:format cl:nil "Header header~%geometry_msgs/Pose pose~%string color~%string shape~%string pic~%float32 radius~%float32 exeTime~%~%================================================================================~%MSG: std_msgs/Header~%# Standard metadata for higher-level stamped data types.~%# This is generally used to communicate timestamped data ~%# in a particular coordinate frame.~%# ~%# sequence ID: consecutively increasing ID ~%uint32 seq~%#Two-integer timestamp that is expressed as:~%# * stamp.sec: seconds (stamp_secs) since epoch (in Python the variable is called 'secs')~%# * stamp.nsec: nanoseconds since stamp_secs (in Python the variable is called 'nsecs')~%# time-handling sugar is provided by the client library~%time stamp~%#Frame this data is associated with~%# 0: no frame~%# 1: global frame~%string frame_id~%~%================================================================================~%MSG: geometry_msgs/Pose~%# A representation of pose in free space, composed of position and orientation. ~%Point position~%Quaternion orientation~%~%================================================================================~%MSG: geometry_msgs/Point~%# This contains the position of a point in free space~%float64 x~%float64 y~%float64 z~%~%================================================================================~%MSG: geometry_msgs/Quaternion~%# This represents an orientation in free space in quaternion form.~%~%float64 x~%float64 y~%float64 z~%float64 w~%~%~%"))
(cl:defmethod roslisp-msg-protocol:serialization-length ((msg <Cvmsg>))
  (cl:+ 0
     (roslisp-msg-protocol:serialization-length (cl:slot-value msg 'header))
     (roslisp-msg-protocol:serialization-length (cl:slot-value msg 'pose))
     4 (cl:length (cl:slot-value msg 'color))
     4 (cl:length (cl:slot-value msg 'shape))
     4 (cl:length (cl:slot-value msg 'pic))
     4
     4
))
(cl:defmethod roslisp-msg-protocol:ros-message-to-list ((msg <Cvmsg>))
  "Converts a ROS message object to a list"
  (cl:list 'Cvmsg
    (cl:cons ':header (header msg))
    (cl:cons ':pose (pose msg))
    (cl:cons ':color (color msg))
    (cl:cons ':shape (shape msg))
    (cl:cons ':pic (pic msg))
    (cl:cons ':radius (radius msg))
    (cl:cons ':exeTime (exeTime msg))
))
