package jdbcsoftware;

import org.ros.namespace.GraphName;
import org.ros.node.AbstractNodeMain;
import org.ros.node.ConnectedNode;
import org.apache.commons.logging.Log;

public class Listener extends AbstractNodeMain 
{

  @Override
  public GraphName getDefaultNodeName() 
  {
    return GraphName.of("rosjava/listener");
  }

  @Override
  public void onStart(ConnectedNode connectedNode) 
  {
    final Log log = connectedNode.getLog();
    Subscriber<std_msgs.String> subscriber = connectedNode.newSubscriber("chatter", std_msgs.String._TYPE);
    subscriber.addMessageListener(new MessageListener<std_msgs.String>() 
    {
      @Override
      public void onNewMessage(std_msgs.String message) 
      {
        log.info("I heard: \"" + message.getData() + "\"");
      }
    });
  }
}
