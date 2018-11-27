; Auto-generated. Do not edit!


(cl:in-package rosjava_catkin_package_a-msg)


;//! \htmlinclude Urmsg.msg.html

(cl:defclass <Urmsg> (roslisp-msg-protocol:ros-message)
  ((pickUpTime
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

(cl:defclass Urmsg (<Urmsg>)
  ())

(cl:defmethod cl:initialize-instance :after ((m <Urmsg>) cl:&rest args)
  (cl:declare (cl:ignorable args))
  (cl:unless (cl:typep m 'Urmsg)
    (roslisp-msg-protocol:msg-deprecation-warning "using old message class name rosjava_catkin_package_a-msg:<Urmsg> is deprecated: use rosjava_catkin_package_a-msg:Urmsg instead.")))

(cl:ensure-generic-function 'pickUpTime-val :lambda-list '(m))
(cl:defmethod pickUpTime-val ((m <Urmsg>))
  (roslisp-msg-protocol:msg-deprecation-warning "Using old-style slot reader rosjava_catkin_package_a-msg:pickUpTime-val is deprecated.  Use rosjava_catkin_package_a-msg:pickUpTime instead.")
  (pickUpTime m))

(cl:ensure-generic-function 'throwTime-val :lambda-list '(m))
(cl:defmethod throwTime-val ((m <Urmsg>))
  (roslisp-msg-protocol:msg-deprecation-warning "Using old-style slot reader rosjava_catkin_package_a-msg:throwTime-val is deprecated.  Use rosjava_catkin_package_a-msg:throwTime instead.")
  (throwTime m))
(cl:defmethod roslisp-msg-protocol:serialize ((msg <Urmsg>) ostream)
  "Serializes a message object of type '<Urmsg>"
  (cl:write-byte (cl:ldb (cl:byte 8 0) (cl:slot-value msg 'pickUpTime)) ostream)
  (cl:write-byte (cl:ldb (cl:byte 8 8) (cl:slot-value msg 'pickUpTime)) ostream)
  (cl:write-byte (cl:ldb (cl:byte 8 16) (cl:slot-value msg 'pickUpTime)) ostream)
  (cl:write-byte (cl:ldb (cl:byte 8 24) (cl:slot-value msg 'pickUpTime)) ostream)
  (cl:write-byte (cl:ldb (cl:byte 8 0) (cl:slot-value msg 'throwTime)) ostream)
  (cl:write-byte (cl:ldb (cl:byte 8 8) (cl:slot-value msg 'throwTime)) ostream)
  (cl:write-byte (cl:ldb (cl:byte 8 16) (cl:slot-value msg 'throwTime)) ostream)
  (cl:write-byte (cl:ldb (cl:byte 8 24) (cl:slot-value msg 'throwTime)) ostream)
)
(cl:defmethod roslisp-msg-protocol:deserialize ((msg <Urmsg>) istream)
  "Deserializes a message object of type '<Urmsg>"
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
(cl:defmethod roslisp-msg-protocol:ros-datatype ((msg (cl:eql '<Urmsg>)))
  "Returns string type for a message object of type '<Urmsg>"
  "rosjava_catkin_package_a/Urmsg")
(cl:defmethod roslisp-msg-protocol:ros-datatype ((msg (cl:eql 'Urmsg)))
  "Returns string type for a message object of type 'Urmsg"
  "rosjava_catkin_package_a/Urmsg")
(cl:defmethod roslisp-msg-protocol:md5sum ((type (cl:eql '<Urmsg>)))
  "Returns md5sum for a message object of type '<Urmsg>"
  "e40cf962e57959925868757d085c8b52")
(cl:defmethod roslisp-msg-protocol:md5sum ((type (cl:eql 'Urmsg)))
  "Returns md5sum for a message object of type 'Urmsg"
  "e40cf962e57959925868757d085c8b52")
(cl:defmethod roslisp-msg-protocol:message-definition ((type (cl:eql '<Urmsg>)))
  "Returns full string definition for message of type '<Urmsg>"
  (cl:format cl:nil "uint32 pickUpTime~%uint32 throwTime~%~%~%"))
(cl:defmethod roslisp-msg-protocol:message-definition ((type (cl:eql 'Urmsg)))
  "Returns full string definition for message of type 'Urmsg"
  (cl:format cl:nil "uint32 pickUpTime~%uint32 throwTime~%~%~%"))
(cl:defmethod roslisp-msg-protocol:serialization-length ((msg <Urmsg>))
  (cl:+ 0
     4
     4
))
(cl:defmethod roslisp-msg-protocol:ros-message-to-list ((msg <Urmsg>))
  "Converts a ROS message object to a list"
  (cl:list 'Urmsg
    (cl:cons ':pickUpTime (pickUpTime msg))
    (cl:cons ':throwTime (throwTime msg))
))
