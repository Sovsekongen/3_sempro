package jdbcsoftware;

import org.ros.concurrent.CancellableLoop;
import org.ros.namespace.GraphName;
import org.ros.node.AbstractNodeMain;
import org.ros.node.ConnectedNode;
import org.ros.node.NodeMain;
import org.ros.node.topic.Publisher;
import org.ros.*;


public class Talker extends AbstractNodeMain 
{

 
  @Override
  public GraphName getDefaultNodeName() 
  {
    return GraphName.of("rosjava/talker");
  }

  @Override
  public void onStart(final ConnectedNode connectedNode) 
  {
    final Publisher<String> publisher = connectedNode.newPublisher("chatter", "String");
    // This CancellableLoop will be canceled automatically when the node shuts
    // down.
    connectedNode.executeCancellableLoop(new CancellableLoop() 
    {
      private int sequenceNumber;

      @Override
      protected void setup() 
      {
        sequenceNumber = 0;
      }

      @Override
      protected void loop() throws InterruptedException 
      {
        String str = publisher.newMessage();
        str.setData("Hello world! " + sequenceNumber);
        publisher.publish(str);
        sequenceNumber++;
        Thread.sleep(1000);
      }
    });
  }
}