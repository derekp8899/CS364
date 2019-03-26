/*

Derek Popowski
derek.a.popowski@und.edu
CSci 364 Concurrent and Distributed Programming
Homework #4 (Dining Philosophers Revisited)

Waiter class
contains fork usage data and signals philosophers when they are available to pickup with jms messages

*/

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.TextMessage;

public class Waiter implements Runnable{
    
    private boolean stop;
    private boolean[] forks;
    private MessageConsumer consumer;
    private MessageProducer aProd;
    private MessageProducer bProd;
    private MessageProducer cProd;
    private MessageProducer dProd;
    private MessageProducer eProd;

    public void Waiter(MessageProducer alpha, MessageProducer bravo, MessageProducer charlie, MessageProducer delta, 
		       MessageProducer echo, MessageConsumer inQueue){
	aProd = alpha;
	bProd = bravo;
	cProd = charlie;
	dProd = delta;
	eProd = echo;
	consumer = inQueue;
	stop = false;
	forks = new boolean[5];

    }
    
    public void run(){

	while(!stop){

	    

	}

    }

    public void setStop(boolean flag){
	stop = flag;
    }

    private void pickUp(int id){
	if(id == 0){
	    if(forks[0]&&forks[1]){

	    }
	    else{

	    }
	}
	else if(id == 1){
	    if(forks[2]&&forks[1]){

	    }
	    else{

	    }
	}
	else if(id == 2){
	    if(forks[2]&&forks[3]){

	    }
	    else{

	    }
	}
	else if(id == 3){
	    if(forks[3]&&forks[4]){

	    }
	    else{

	    }
	}
	else if(id == 4){
	    if(forks[0]&&forks[4]){

	    }
	    else{

	    }
	}
    }
    private void release(int id){
	if(id == 0){

	}
	else if(id == 1){

	}
	else if(id == 2){

	}
	else if(id == 3){

	}
	else if(id == 4){

	}
    }
}
