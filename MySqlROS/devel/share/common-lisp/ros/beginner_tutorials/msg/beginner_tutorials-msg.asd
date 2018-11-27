
(cl:in-package :asdf)

(defsystem "beginner_tutorials-msg"
  :depends-on (:roslisp-msg-protocol :roslisp-utils :geometry_msgs-msg
               :std_msgs-msg
)
  :components ((:file "_package")
    (:file "cvmsger" :depends-on ("_package_cvmsger"))
    (:file "_package_cvmsger" :depends-on ("_package"))
    (:file "urmsg" :depends-on ("_package_urmsg"))
    (:file "_package_urmsg" :depends-on ("_package"))
  ))