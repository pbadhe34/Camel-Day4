 

import java.util.Properties;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

/**
 * This program shows how to establish a connection to
 * and receive messages from a JMS queue. The classes in this
 * package operate on the same JMS queue.  
 * This is asynchronous message listener
 */
 
public class AsyncJMSMessageReceiver implements MessageListener
{  
  private ConnectionFactory qconFactory;
   
  MessageConsumer consumer;
  private Queue queue;
  private boolean quit = false;
  Connection connection;

 /**
  * Message listener interface method.   
  */

  public void onMessage(Message msg)
  {
    try {
      String msgText;
      if (msg instanceof TextMessage) {
        msgText = ((TextMessage)msg).getText();
        //System.out.println("Message Text Received: "+ msgText );
        /*if(msgText.length() > 5)
        	throw new RuntimeException("Large message..");*/
      } else {
        msgText = msg.toString();
      }

      System.out.println("Message Received: "+ msgText );
     //Notify the current thread if quit message is received so to terminate
      if (msgText.equalsIgnoreCase("quit")) {
        synchronized(this) {
          quit = true;
          this.notifyAll(); // Notify main thread to quit
        }
      }
    } catch (JMSException jmse) {
      jmse.printStackTrace();
    }
  }

  /**
   * Creates all the necessary objects for receiving
   * messages from a JMS queue.    
   */

  public void init()
    throws NamingException, JMSException
  {
	  
		// connect to external activeMQ JMS provider
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				"tcp://localhost:61616");

				
		connection = connectionFactory.createConnection();		
		int autoAck = Session.AUTO_ACKNOWLEDGE;
		Session session = connection.createSession(false, autoAck);
       Destination queue = new ActiveMQQueue("incomingOrders");     
       MessageConsumer consumer = session.createConsumer(queue);
   	
       consumer.setMessageListener(this);     
    //start processing messages on the queue connection
       connection.start();  
  }

  /**
   * Closes JMS objects.  
   */
  public void close()throws JMSException
  {
	  consumer.close();     
      connection.close();
  }
/**
  * main() method.  
  */

  public static void main(String[] args) throws Exception 
  {
     
    
	  AsyncJMSMessageReceiver mqr = new AsyncJMSMessageReceiver();
      mqr.init();

      System.out.println("JMS Ready To Receive Messages (To quit, send a \"quit\" message).");

    // Wait to read the messages until a "quit" message has been received.
     synchronized(mqr) 
	 {
       while (! mqr.quit) {
        try 
        {
          mqr.wait();
        } 
        catch (InterruptedException ie) {}
      }
    }
    mqr.close();
  }  

}




