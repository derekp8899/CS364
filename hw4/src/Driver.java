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

    private static int delay = 0;

    public static void main(String[] args)throws InterruptedException{
	if(args.length < 1){
	    System.out.println("No runtime specified\nUSAGE: $ java -cp build:lib/activemq-all-5.15.8.jar Driver [runtime]\n--EXITING--");
	    System.exit(1);
	}
	try{
	    delay = Integer.parseInt(args[0]);
	}
	catch(NumberFormatException e){
	    System.out.println("Invalid runtime entered please try again\n--EXITING--");
	    System.exit(1);
	}
	ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
	Connection connection = null;
	try{
	    connection = factory.createConnection();
	    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	    Session session1 = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	    Session session2 = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	    Session session3 = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	    Session session4 = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	    Session session5 = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	    Destination waiterQueue = session.createQueue("hw4.queue.Waiter");
	    Destination alphaQueue = session1.createQueue("hw4.queue.Alpha");
	    Destination bravoQueue = session2.createQueue("hw4.queue.Bravo");
	    Destination charlieQueue = session3.createQueue("hw4.queue.Charlie");
	    Destination deltaQueue = session4.createQueue("hw4.queue.Delta");
	    Destination echoQueue = session5.createQueue("hw4.queue.Echo");
	    MessageProducer alphaProd = session.createProducer(waiterQueue);
	    MessageProducer bravoProd = session.createProducer(waiterQueue);
	    MessageProducer charlieProd = session.createProducer(waiterQueue);
	    MessageProducer deltaProd = session.createProducer(waiterQueue);
	    MessageProducer echoProd = session.createProducer(waiterQueue);
	    MessageProducer waitA = session1.createProducer(alphaQueue);
	    MessageProducer waitB = session2.createProducer(bravoQueue);
	    MessageProducer waitC = session3.createProducer(charlieQueue);
	    MessageProducer waitD = session4.createProducer(deltaQueue);
	    MessageProducer waitE = session5.createProducer(echoQueue);
	    MessageConsumer alphaCons = session1.createConsumer(alphaQueue);
	    MessageConsumer bravoCons = session2.createConsumer(bravoQueue);
	    MessageConsumer charlieCons = session3.createConsumer(charlieQueue);
	    MessageConsumer deltaCons = session4.createConsumer(deltaQueue);
	    MessageConsumer echoCons = session5.createConsumer(echoQueue);
	    MessageConsumer waitCons = session.createConsumer(waiterQueue);

	    connection.start();
	    Philosopher alpha = new Philosopher(alphaCons, alphaProd, session1, 0);
	    Philosopher bravo = new Philosopher(bravoCons, bravoProd, session2, 1);
	    Philosopher charlie = new Philosopher(charlieCons, charlieProd, session3, 2);
	    Philosopher delta = new Philosopher(deltaCons, deltaProd, session4, 3);
	    Philosopher echo = new Philosopher(echoCons, echoProd, session5, 4);
	    Waiter waiter = new Waiter(waitA, waitB, waitC, waitD, waitE, waitCons, session);

	    Thread t0 = new Thread(waiter);
	    Thread t1 = new Thread(alpha);
	    Thread t2 = new Thread(bravo);
	    Thread t3 = new Thread(charlie);
	    Thread t4 = new Thread(delta);
	    Thread t5 = new Thread(echo);
	    	    
	    t0.start();
	    t1.start();
	    t2.start();
	    t3.start();
	    t4.start();
	    t5.start();

	    Thread.sleep(delay);

	    waiter.setStop(true);
	    alpha.setStop(true);
	    bravo.setStop(true);
	    charlie.setStop(true);
	    delta.setStop(true);
	    echo.setStop(true);
	    
	    System.out.println("Sending stop signal to threads.");
	    //Thread.sleep(100);
	    /*
	    System.out.println(alpha.toString());
	    System.out.println(bravo.toString());
	    System.out.println(charlie.toString());
	    System.out.println(delta.toString());
	    System.out.println(echo.toString());
	    */
	    
	    t0.join();
	    t1.join();
	    t2.join();
	    t3.join();
	    t4.join();
	    t5.join();
	    
	    System.out.println("Dinner is done.");
	    
	    System.out.println(alpha.toString());
	    System.out.println(bravo.toString());
	    System.out.println(charlie.toString());
	    System.out.println(delta.toString());
	    System.out.println(echo.toString());

	    connection.close();
	}
	catch (JMSException e){
	    System.out.println("Exception while attempting jms initialization\n--EXITING--");
	    System.exit(1);
	}
	
    }

}
