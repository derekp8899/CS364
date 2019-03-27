/*

Derek Popowski
derek.a.popowski@und.edu
CSci 364 Concurrent and Distributed Programming
Homework #4 (Dining Philosophers Revisited)

Philosopher
implements Runnable
uses JMS messaging to determine when forks are available

*/

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.TextMessage;
import javax.jms.Session;

public class Philosopher implements Runnable{

    private boolean stop;
    private int id;
    private MessageConsumer consumer;
    private MessageProducer waiter;
    private long timeTot;
    private long timeCrit;
    private long tempTime;
    private int meals;
    private int thoughts;
    private Session session;

    public Philosopher(MessageConsumer inQueue, MessageProducer wait, Session sess,int id){
	this.stop = false;
	this.id = id;
	this.consumer = inQueue;
	this.waiter = wait;
	timeTot = 0;
	timeCrit = 0;
	meals = 0;
	thoughts = 0;
	session = sess;
    }
	
    public void run(){
	
	timeTot = System.nanoTime();//total time timer
	while(!stop){
	    thoughts++;//do one think cycle
	    tempTime = System.nanoTime();//start timer
	    try{
		String msg = id + ",pickup";
		TextMessage txtMsg = session.createTextMessage(msg);
		waiter.send(txtMsg);
		System.out.println(id + " send pickup req");
		Message inMsg = consumer.receive();
		TextMessage inTxtMsg = (TextMessage)inMsg;
		String msgTxt = inTxtMsg.getText();
		System.out.println(id + "rec " + msgTxt);
		if(msgTxt.equals("true")){
		    meals++;
		    timeCrit = System.nanoTime() - tempTime;
		    msg = id + ",release";
		    txtMsg = session.createTextMessage(msg);
		    waiter.send(txtMsg);
		    System.out.println(id + "sent release");
		}
		else{
		    timeCrit = System.nanoTime() - tempTime;
		}
	    }
	    catch(JMSException e){
		System.out.println("Caught JMSException in Philosopher " + id + " \n--EXITING--");
		System.exit(1);
	    }
	}
	timeTot = System.nanoTime() - timeTot;

    }

    public void setStop(boolean flag){
	stop = flag;
    }
    
    public String toString(){
	String rtStr;
	rtStr = "Philosopher ID: " + id + "\n";
	rtStr = rtStr + "   Thoughts: " + thoughts +  " Meals: " + meals +  "\n";
	rtStr = rtStr + "   Critical time(ns): " + timeCrit + "\n";
	rtStr = rtStr + "   Total Time(ns): " + timeTot + "\n";
	rtStr = rtStr + "   Time Ratio -- Critical/Total: " + (double)timeCrit/timeTot;
	return rtStr;
	
    }
}
