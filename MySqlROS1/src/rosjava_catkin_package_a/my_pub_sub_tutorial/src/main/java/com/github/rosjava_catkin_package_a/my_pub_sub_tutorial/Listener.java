/*
 * Copyright (C) 2014 Viktor.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.github.rosjava.rosjava_catkin_package_a.my_pub_sub_tutorial;

import org.apache.commons.logging.Log;
import org.ros.message.MessageListener;
import org.ros.namespace.GraphName;
import org.ros.node.AbstractNodeMain;
import org.ros.node.ConnectedNode;
import org.ros.node.NodeMain;
import org.ros.node.topic.Subscriber;
<<<<<<< HEAD
import java.sql.*;
import java.util.Scanner;
import geometry_msgs.Vector3;
//import com.github.rosjava.rosjava_catkin_package_a.msg.*;

=======
>>>>>>> 26a60b4c68b3a2a261a9b928b81953632d92833b

/**
 * A simple {@link Subscriber} {@link NodeMain}.
 */
<<<<<<< HEAD

 // rosrun rosjava_catkin_package_a my_pub_sub_tutorial com.github.rosjava.rosjava_catkin_package_a.my_pub_sub_tutorial.Listener
public class Listener extends AbstractNodeMain {

    public String timeStamp;
    public String color;
    public String shape;
    public String pic;
    public int posX;
    public int posY;
    public int cvTime;
    public int radius;
    public int pickUpTime;
    public int throwTime;
    public int counter = 0;

=======
public class Listener extends AbstractNodeMain {

>>>>>>> 26a60b4c68b3a2a261a9b928b81953632d92833b
  @Override
  public GraphName getDefaultNodeName() {
    return GraphName.of("rosjava/listener");
  }

  @Override
  public void onStart(ConnectedNode connectedNode) {
    final Log log = connectedNode.getLog();
<<<<<<< HEAD
    final JDBC jdbc = new JDBC("root","123",log);
//    final Subscriber<msg.Cvmsg> subscriber = connectedNode.newSubscriber("chatter", msg.Cvmsg._TYPE);
//    subscriber.addMessageListener(new MessageListener<msg.Cvmsg>()
    final Subscriber<std_msgs.String> subscriber = connectedNode.newSubscriber("chatter1", std_msgs.String._TYPE);
    subscriber.addMessageListener(new MessageListener<std_msgs.String>()
    {
      @Override
      public void onNewMessage(std_msgs.String message)
      {
        //Logs the message received to terminal
        log.info("I heard: \"" + message.getData() + "\"");

        timeStamp = new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());



        Scanner sc = new Scanner(message.getData()).useDelimiter(";");
        //Extracts publisher form string
        String publisher = sc.next();
        System.out.println("Publisher node: "+publisher);
        //counter == 0 because the program should always recieve values from CV before from UR
        if(publisher.equals("CV") && counter == 0)
        {
            //Define parameter values from CV
            posX    = Integer.parseInt(sc.next());
            posY    = Integer.parseInt(sc.next());
            cvTime  = Integer.parseInt(sc.next());
            radius  = Integer.parseInt(sc.next());
            color   = sc.next();
            shape   = sc.next();
            pic     = sc.next();
            sc.close();
            //Insert data into database
            jdbc.insertOpenCVData(timeStamp,posX,posY,cvTime,radius,color,shape,pic,log);

            counter = 1;
        }
        else if(publisher.equals("UR") && counter == 1)
        {
            //Define parameter values from UR
            pickUpTime  = Integer.parseInt(sc.next());
            throwTime   = Integer.parseInt(sc.next());
            sc.close();
            //Inesert data into database
            jdbc.insertExeTime(pickUpTime,throwTime,log);
            counter = 0;
        }
        else if(publisher.equals("UR") && counter == 0)
        {            
            System.out.println("");
            System.out.println("Recieved message from UR before CV. Shutting down to prevent over writing data in database. Please restart program");
            System.out.println("");

            subscriber.shutdown();
        }
        else
        {
            System.out.println("Very wrong");
        }



=======
    Subscriber<std_msgs.String> subscriber = connectedNode.newSubscriber("chatter", std_msgs.String._TYPE);
    subscriber.addMessageListener(new MessageListener<std_msgs.String>() {
      @Override
      public void onNewMessage(std_msgs.String message) {
        log.info("I heard1: \"" + message.getData() + "\"");
>>>>>>> 26a60b4c68b3a2a261a9b928b81953632d92833b
      }
    });
  }
}
