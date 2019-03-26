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

public class Philosopher implements Runnable{

    private boolean stop;
    private int id;
    private MessageConsumer consumer;
    private MessageProducer waiter;
    private double timeTot;
    private double timeCrit;
    private int meals;
    private int thoughts;

    public void Philosopher(MessageConsumer inQueue, MessageProducer wait, int id){
	this.stop = false;
	this.id = id;
	this.consumer = inQueue;
	this.waiter = wait;
	timeTot = 0;
	timeCrit = 0;
	meals = 0;
	thoughts = 0;
    }
	
    public void run(){

	while(!stop){
	    
	}

    }

    public void setStop(boolean flag){
	stop = flag;
    }
}
