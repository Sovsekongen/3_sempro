
(cl:in-package :asdf)

(defsystem "rosjava_catkin_package_a-msg"
  :depends-on (:roslisp-msg-protocol :roslisp-utils :geometry_msgs-msg
               :std_msgs-msg
)
  :components ((:file "_package")
    (:file "Cvmsg" :depends-on ("_package_Cvmsg"))
    (:file "_package_Cvmsg" :depends-on ("_package"))
    (:file "Urmsg" :depends-on ("_package_Urmsg"))
    (:file "_package_Urmsg" :depends-on ("_package"))
  ))