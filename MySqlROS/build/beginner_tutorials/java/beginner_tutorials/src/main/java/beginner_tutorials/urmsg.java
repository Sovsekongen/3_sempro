package beginner_tutorials;

public interface urmsg extends org.ros.internal.message.Message {
  static final java.lang.String _TYPE = "beginner_tutorials/urmsg";
  static final java.lang.String _DEFINITION = "Header header\nuint32 pickUpTime\nuint32 throwTime\n";
  std_msgs.Header getHeader();
  void setHeader(std_msgs.Header value);
  int getPickUpTime();
  void setPickUpTime(int value);
  int getThrowTime();
  void setThrowTime(int value);
}
