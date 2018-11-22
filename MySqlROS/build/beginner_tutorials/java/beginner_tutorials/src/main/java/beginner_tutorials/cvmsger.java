package beginner_tutorials;

public interface cvmsger extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "beginner_tutorials/cvmsger";
  static final java.lang.String _DEFINITION = "Header header\ngeometry_msgs/Pose pose\nstring color\nstring shape\nstring pic\nuint32 radius\nuint32 exeTime\n";
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  geometry_msgs.Pose getPose();
  void setPose(geometry_msgs.Pose value);
  java.lang.String getColor();
  void setColor(java.lang.String value);
  java.lang.String getShape();
  void setShape(java.lang.String value);
  java.lang.String getPic();
  void setPic(java.lang.String value);
  int getRadius();
  void setRadius(int value);
  int getExeTime();
  void setExeTime(int value);
}
