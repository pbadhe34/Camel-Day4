package com.app;

  
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

public class JMSPublisher {

	public static void main(String[] args) throws JMSException, IOException {
		Connection connection = null;
		try {
			// start with embedded built-in activeMQ JMS provider
			// ConnectionFactory connectionFactory = new
			// ActiveMQConnectionFactory("vm://localhost");

			// connect to external activeMQ JMS provider
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
					"tcp://localhost:61616");

			//admin console  is at http//localhost:8161
			connection = connectionFactory.createConnection();
			int autoAck = Session.AUTO_ACKNOWLEDGE;
			Session session = connection.createSession(false, autoAck);
			Destination queue = new ActiveMQQueue("appqueue");
			MessageProducer producer = session.createProducer(queue);
			TextMessage messageToSend = session.createTextMessage();

			BufferedReader msgStream = new BufferedReader(
					new InputStreamReader(System.in));
			String line = null;
			boolean quit = false;
			do {
				System.out
						.print("Enter message to send..(\"quit\" to quit): \n");
				line = msgStream.readLine();
				if (line != null && line.trim().length() != 0) {
					messageToSend.setText(line);// set the text property of
												// message to string
					producer.send(messageToSend); // send the the message using
													// sender
					System.out.println("JMS Message Sent: " + line + "\n");
					quit = line.equalsIgnoreCase("quit");
				}
			} while (!quit);

			// Close JMS objects.
			producer.close();
			connection.close();
		} catch (JMSException e) {
			System.out.println("Problem occurred in JMS Producer client  " + e);
			connection.close();
		}
		System.out.println("Message sent by the jms producer..");
	}
}