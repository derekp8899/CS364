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
import javax.jms.Session;

public class Waiter implements Runnable{
    
    private boolean stop;
    private boolean[] forks;
    private MessageConsumer consumer;
    private MessageProducer aProd;
    private MessageProducer bProd;
    private MessageProducer cProd;
    private MessageProducer dProd;
    private MessageProducer eProd;
    private Session session;

    public Waiter(MessageProducer alpha, MessageProducer bravo, MessageProducer charlie, MessageProducer delta, 
		       MessageProducer echo, MessageConsumer inQueue, Session sess){
	aProd = alpha;
	bProd = bravo;
	cProd = charlie;
	dProd = delta;
	eProd = echo;
	consumer = inQueue;
	stop = false;
	forks = new boolean[5];
	for(int i = 0; i < 5; i++){
	    forks[i] = true;
	}
	session = sess;
    }
    
    public void run(){

	while(!stop){
	    try{
		Message message = consumer.receive();
		TextMessage msg = (TextMessage)message;
		String msgTxt = msg.getText();
		int id = -1;//set defaults as sentinel values
		String action = "";
		try{
		    id = Integer.parseInt(msgTxt.split(",")[0]);
		    action = msgTxt.split(",")[1];
		}
		catch(Exception e){
		    System.out.println("Exception while trying to parse message\n--EXITING--");
		    System.exit(1);
		}
		if(action.equals("pickup")){
		    System.out.println("waiter: " + action + id);
		    pickUp(id);
		}
		else{
		    release(id);
		}
	    }
	    catch(JMSException e){
		System.out.println("Exception while trying to consume message\n--EXITING--");
		System.exit(1);
	    }

	}

    }

    public void setStop(boolean flag){
	stop = flag;
    }

    private void pickUp(int id){
	String msg;
	try{
	    if(id == 0){
		if(forks[0]&&forks[1]){
		    forks[0] = false;
		    forks[1] = false;
		    msg = "true";
		    TextMessage textMes = session.createTextMessage(msg);
		    aProd.send(textMes);
		    System.out.println("waiter: " + id + "sent true");
		}
		else{
		    msg = "false";
		    TextMessage textMes = session.createTextMessage(msg);
		    aProd.send(textMes);
		}
	    }
	    else if(id == 1){
		if(forks[2]&&forks[1]){
		    forks[1] = false;
		    forks[2] = false;
		    msg = "true";
		    TextMessage textMes = session.createTextMessage(msg);
		    bProd.send(textMes);
		}
		else{
		    msg = "false";
		    TextMessage textMes = session.createTextMessage(msg);
		    bProd.send(textMes);
		}
	    }
	    else if(id == 2){
		if(forks[2]&&forks[3]){
		    forks[2] = false;
		    forks[3] = false;
		    msg = "true";
		    TextMessage textMes = session.createTextMessage(msg);
		    cProd.send(textMes);
		}
		else{
		    msg = "false";
		    TextMessage textMes = session.createTextMessage(msg);
		    cProd.send(textMes);
		}
	    }
	    else if(id == 3){
		if(forks[3]&&forks[4]){
		    forks[3] = false;
		    forks[4] = false;
		    msg = "true";
		    TextMessage textMes = session.createTextMessage(msg);
		    dProd.send(textMes);
		}
		else{
		    msg = "false";
		    TextMessage textMes = session.createTextMessage(msg);
		    dProd.send(textMes);
		}
	    }
	    else if(id == 4){
		if(forks[0]&&forks[4]){
		    forks[0] = false;
		    forks[4] = false;
		    msg = "true";
		    TextMessage textMes = session.createTextMessage(msg);
		    eProd.send(textMes);
		}
		else{
		    msg = "false";
		    TextMessage textMes = session.createTextMessage(msg);
		    eProd.send(textMes);
		}
	    }
	}
	catch(JMSException e){
	    System.out.println("Exception while attempting to send message\n--EXITING--");
	    System.exit(1);
	}
    }
    private void release(int id){
	String msg;
	try{
	    if(id == 0){
		forks[0] = true;
		forks[1] = true;
		msg = "true";
		TextMessage textMes = session.createTextMessage(msg);
		aProd.send(textMes);
	    }
	    else if(id == 1){
		forks[2] = true;
		forks[1] = true;
		msg = "true";
		TextMessage textMes = session.createTextMessage(msg);
		bProd.send(textMes);
	    }
	    else if(id == 2){
		forks[2] = true;
		forks[3] = true;
		msg = "true";
		TextMessage textMes = session.createTextMessage(msg);
		cProd.send(textMes);
	    }
	    else if(id == 3){
		forks[3] = true;
		forks[4] = true;
		msg = "true";
		TextMessage textMes = session.createTextMessage(msg);
		dProd.send(textMes);
	    }
	    else if(id == 4){
		forks[0] = true;
		forks[4] = true;
		msg = "true";
		TextMessage textMes = session.createTextMessage(msg);
		eProd.send(textMes);
	    }
	}
	catch(JMSException e){
	    System.out.println("Exception while attempting to send message\n--EXITING--");
	    System.exit(1);
	}
    }
}
