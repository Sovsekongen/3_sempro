;; Auto-generated. Do not edit!


(when (boundp 'rosjava_catkin_package_a::Urmsg)
  (if (not (find-package "ROSJAVA_CATKIN_PACKAGE_A"))
    (make-package "ROSJAVA_CATKIN_PACKAGE_A"))
  (shadow 'Urmsg (find-package "ROSJAVA_CATKIN_PACKAGE_A")))
(unless (find-package "ROSJAVA_CATKIN_PACKAGE_A::URMSG")
  (make-package "ROSJAVA_CATKIN_PACKAGE_A::URMSG"))

(in-package "ROS")
;;//! \htmlinclude Urmsg.msg.html


(defclass rosjava_catkin_package_a::Urmsg
  :super ros::object
  :slots (_pickUpTime _throwTime ))

(defmethod rosjava_catkin_package_a::Urmsg
  (:init
   (&key
    ((:pickUpTime __pickUpTime) 0)
    ((:throwTime __throwTime) 0)
    )
   (send-super :init)
   (setq _pickUpTime (round __pickUpTime))
   (setq _throwTime (round __throwTime))
   self)
  (:pickUpTime
   (&optional __pickUpTime)
   (if __pickUpTime (setq _pickUpTime __pickUpTime)) _pickUpTime)
  (:throwTime
   (&optional __throwTime)
   (if __throwTime (setq _throwTime __throwTime)) _throwTime)
  (:serialization-length
   ()
   (+
    ;; uint32 _pickUpTime
    4
    ;; uint32 _throwTime
    4
    ))
  (:serialize
   (&optional strm)
   (let ((s (if strm strm
              (make-string-output-stream (send self :serialization-length)))))
     ;; uint32 _pickUpTime
       (write-long _pickUpTime s)
     ;; uint32 _throwTime
       (write-long _throwTime s)
     ;;
     (if (null strm) (get-output-stream-string s))))
  (:deserialize
   (buf &optional (ptr- 0))
   ;; uint32 _pickUpTime
     (setq _pickUpTime (sys::peek buf ptr- :integer)) (incf ptr- 4)
   ;; uint32 _throwTime
     (setq _throwTime (sys::peek buf ptr- :integer)) (incf ptr- 4)
   ;;
   self)
  )

(setf (get rosjava_catkin_package_a::Urmsg :md5sum-) "e40cf962e57959925868757d085c8b52")
(setf (get rosjava_catkin_package_a::Urmsg :datatype-) "rosjava_catkin_package_a/Urmsg")
(setf (get rosjava_catkin_package_a::Urmsg :definition-)
      "uint32 pickUpTime
uint32 throwTime

")



(provide :rosjava_catkin_package_a/Urmsg "e40cf962e57959925868757d085c8b52")


