; Auto-generated. Do not edit!


(cl:in-package beginner_tutorials-msg)


;//! \htmlinclude urmsg.msg.html

(cl:defclass <urmsg> (roslisp-msg-protocol:ros-message)
  ((header
    :reader header
    :initarg :header
    :type std_msgs-msg:Header
    :initform (cl:make-instance 'std_msgs-msg:Header))
   (pickUpTime
    :reader pickUpTime
    :initarg :pickUpTime
    :type cl:integer
    :initform 0)
   (throwTime
    :reader throwTime
    :initarg :throwTime
    :type cl:integer
    :initform 0))
)

(cl:defclass urmsg (<urmsg>)
  ())

(cl:defmethod cl:initialize-instance :after ((m <urmsg>) cl:&rest args)
  (cl:declare (cl:ignorable args))
  (cl:unless (cl:typep m 'urmsg)
    (roslisp-msg-protocol:msg-deprecation-warning "using old message class name beginner_tutorials-msg:<urmsg> is deprecated: use beginner_tutorials-msg:urmsg instead.")))

(cl:ensure-generic-function 'header-val :lambda-list '(m))
(cl:defmethod header-val ((m <urmsg>))
  (roslisp-msg-protocol:msg-deprecation-warning "Using old-style slot reader beginner_tutorials-msg:header-val is deprecated.  Use beginner_tutorials-msg:header instead.")
  (header m))

(cl:ensure-generic-function 'pickUpTime-val :lambda-list '(m))
(cl:defmethod pickUpTime-val ((m <urmsg>))
  (roslisp-msg-protocol:msg-deprecation-warning "Using old-style slot reader beginner_tutorials-msg:pickUpTime-val is deprecated.  Use beginner_tutorials-msg:pickUpTime instead.")
  (pickUpTime m))

(cl:ensure-generic-function 'throwTime-val :lambda-list '(m))
(cl:defmethod throwTime-val ((m <urmsg>))
  (roslisp-msg-protocol:msg-deprecation-warning "Using old-style slot reader beginner_tutorials-msg:throwTime-val is deprecated.  Use beginner_tutorials-msg:throwTime instead.")
  (throwTime m))
(cl:defmethod roslisp-msg-protocol:serialize ((msg <urmsg>) ostream)
  "Serializes a message object of type '<urmsg>"
  (roslisp-msg-protocol:serialize (cl:slot-value msg 'header) ostream)
  (cl:write-byte (cl:ldb (cl:byte 8 0) (cl:slot-value msg 'pickUpTime)) ostream)
  (cl:write-byte (cl:ldb (cl:byte 8 8) (cl:slot-value msg 'pickUpTime)) ostream)
  (cl:write-byte (cl:ldb (cl:byte 8 16) (cl:slot-value msg 'pickUpTime)) ostream)
  (cl:write-byte (cl:ldb (cl:byte 8 24) (cl:slot-value msg 'pickUpTime)) ostream)
  (cl:write-byte (cl:ldb (cl:byte 8 0) (cl:slot-value msg 'throwTime)) ostream)
  (cl:write-byte (cl:ldb (cl:byte 8 8) (cl:slot-value msg 'throwTime)) ostream)
  (cl:write-byte (cl:ldb (cl:byte 8 16) (cl:slot-value msg 'throwTime)) ostream)
  (cl:write-byte (cl:ldb (cl:byte 8 24) (cl:slot-value msg 'throwTime)) ostream)
)
(cl:defmethod roslisp-msg-protocol:deserialize ((msg <urmsg>) istream)
  "Deserializes a message object of type '<urmsg>"
  (roslisp-msg-protocol:deserialize (cl:slot-value msg 'header) istream)
    (cl:setf (cl:ldb (cl:byte 8 0) (cl:slot-value msg 'pickUpTime)) (cl:read-byte istream))
    (cl:setf (cl:ldb (cl:byte 8 8) (cl:slot-value msg 'pickUpTime)) (cl:read-byte istream))
    (cl:setf (cl:ldb (cl:byte 8 16) (cl:slot-value msg 'pickUpTime)) (cl:read-byte istream))
    (cl:setf (cl:ldb (cl:byte 8 24) (cl:slot-value msg 'pickUpTime)) (cl:read-byte istream))
    (cl:setf (cl:ldb (cl:byte 8 0) (cl:slot-value msg 'throwTime)) (cl:read-byte istream))
    (cl:setf (cl:ldb (cl:byte 8 8) (cl:slot-value msg 'throwTime)) (cl:read-byte istream))
    (cl:setf (cl:ldb (cl:byte 8 16) (cl:slot-value msg 'throwTime)) (cl:read-byte istream))
    (cl:setf (cl:ldb (cl:byte 8 24) (cl:slot-value msg 'throwTime)) (cl:read-byte istream))
  msg
)
(cl:defmethod roslisp-msg-protocol:ros-datatype ((msg (cl:eql '<urmsg>)))
  "Returns string type for a message object of type '<urmsg>"
  "beginner_tutorials/urmsg")
(cl:defmethod roslisp-msg-protocol:ros-datatype ((msg (cl:eql 'urmsg)))
  "Returns string type for a message object of type 'urmsg"
  "beginner_tutorials/urmsg")
(cl:defmethod roslisp-msg-protocol:md5sum ((type (cl:eql '<urmsg>)))
  "Returns md5sum for a message object of type '<urmsg>"
  "a2e9cc88ef89af17cbcd6d11a52269cb")
(cl:defmethod roslisp-msg-protocol:md5sum ((type (cl:eql 'urmsg)))
  "Returns md5sum for a message object of type 'urmsg"
  "a2e9cc88ef89af17cbcd6d11a52269cb")
(cl:defmethod roslisp-msg-protocol:message-definition ((type (cl:eql '<urmsg>)))
  "Returns full string definition for message of type '<urmsg>"
  (cl:format cl:nil "Header header~%uint32 pickUpTime~%uint32 throwTime~%~%================================================================================~%MSG: std_msgs/Header~%# Standard metadata for higher-level stamped data types.~%# This is generally used to communicate timestamped data ~%# in a particular coordinate frame.~%# ~%# sequence ID: consecutively increasing ID ~%uint32 seq~%#Two-integer timestamp that is expressed as:~%# * stamp.sec: seconds (stamp_secs) since epoch (in Python the variable is called 'secs')~%# * stamp.nsec: nanoseconds since stamp_secs (in Python the variable is called 'nsecs')~%# time-handling sugar is provided by the client library~%time stamp~%#Frame this data is associated with~%# 0: no frame~%# 1: global frame~%string frame_id~%~%~%"))
(cl:defmethod roslisp-msg-protocol:message-definition ((type (cl:eql 'urmsg)))
  "Returns full string definition for message of type 'urmsg"
  (cl:format cl:nil "Header header~%uint32 pickUpTime~%uint32 throwTime~%~%================================================================================~%MSG: std_msgs/Header~%# Standard metadata for higher-level stamped data types.~%# This is generally used to communicate timestamped data ~%# in a particular coordinate frame.~%# ~%# sequence ID: consecutively increasing ID ~%uint32 seq~%#Two-integer timestamp that is expressed as:~%# * stamp.sec: seconds (stamp_secs) since epoch (in Python the variable is called 'secs')~%# * stamp.nsec: nanoseconds since stamp_secs (in Python the variable is called 'nsecs')~%# time-handling sugar is provided by the client library~%time stamp~%#Frame this data is associated with~%# 0: no frame~%# 1: global frame~%string frame_id~%~%~%"))
(cl:defmethod roslisp-msg-protocol:serialization-length ((msg <urmsg>))
  (cl:+ 0
     (roslisp-msg-protocol:serialization-length (cl:slot-value msg 'header))
     4
     4
))
(cl:defmethod roslisp-msg-protocol:ros-message-to-list ((msg <urmsg>))
  "Converts a ROS message object to a list"
  (cl:list 'urmsg
    (cl:cons ':header (header msg))
    (cl:cons ':pickUpTime (pickUpTime msg))
    (cl:cons ':throwTime (throwTime msg))
))
