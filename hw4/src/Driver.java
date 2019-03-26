/*

Derek Popowski
derek.a.popowski@und.edu
CSci 364 Concurrent and Distributed Programming
Homework #4 (Dining Philosophers Revisited)

Driver
Contains mains and JMS setup

*/

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Driver{

    public static void main(String[] args)throws InterruptedException{
	
	ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
	Connection connection = null;
	try{
	    connection = factory.createConnection();
	    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	    Destination waiterQueue = session.createQueue("hw4.queue.Waiter");
	    Destination alphaQueue = session.createQueue("hw4.queue.Alpha");
	    Destination bravoQueue = session.createQueue("hw4.queue.Bravo");
	    Destination charlieQueue = session.createQueue("hw4.queue.Charlie");
	    Destination deltaQueue = session.createQueue("hw4.queue.Delta");
	    Destination echoQueue = session.createQueue("hw4.queue.Echo");
	    MessageProducer alphaProd = session.createProducer(waiterQueue);
	    MessageProducer bravoProd = session.createProducer(waiterQueue);
	    MessageProducer charlieProd = session.createProducer(waiterQueue);
	    MessageProducer deltaProd = session.createProducer(waiterQueue);
	    MessageProducer echoProd = session.createProducer(waiterQueue);
	    MessageProducer waitA = session.createProducer(alphaQueue);
	    MessageProducer waitB = session.createProducer(bravoQueue);
	    MessageProducer waitC = session.createProducer(charlieQueue);
	    MessageProducer waitD = session.createProducer(deltaQueue);
	    MessageProducer waitE = session.createProducer(echoQueue);
	    MessageConsumer alphaCons = session.createConsumer(alphaQueue);
	    MessageConsumer bravoCons = session.createConsumer(bravoQueue);
	    MessageConsumer charlieCons = session.createConsumer(charlieQueue);
	    MessageConsumer deltaCons = session.createConsumer(deltaQueue);
	    MessageConsumer echoCons = session.createConsumer(echoQueue);
	    MessageConsumer waitCons = session.createConsumer(waiterQueue);

	    connection.start();
	    Philosopher alpha;
	    Philosopher bravo;
	    Philosopher charlie;
	    Philosopher delta;
	    Philosopher echo;
	    Waiter waiter;

	    Thread.sleep(100000);
	    
	}
	catch (JMSException e){
	    System.out.println("Exception while attempting jms initialization\n--EXITING--");
	    System.exit(1);
	}
	
    }

}
