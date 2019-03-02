/**
 * 
 */
package worker;

import java.util.ArrayList;
import java.util.Arrays;

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

import api.BrokerConfig;
import api.PrimeChecker;
import api.FractionReducer;
import api.ValueSorter;
import api.Worker;

/**
 * @author david
 *
 */
public class TestWorker implements BrokerConfig {
    
    /**
     * @param args ignored
     */
    public static void main(String[] args) {
	ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(DEFAULT_BROKER);
	
	Connection connection = null;
	try {
	    connection = factory.createConnection();
	    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	    Destination dataQueue = session.createQueue(DATA_QUEUE);
	    Destination workerQueue = session.createQueue(WORKER_QUEUE);
	    MessageProducer producer = session.createProducer(workerQueue);
	    MessageConsumer consumer = session.createConsumer(dataQueue);
	    
	    connection.start();
	    while(true){
		Message message = consumer.receive();
		
		if (message instanceof TextMessage) {
		    TextMessage tm = (TextMessage)message;
		    System.out.println(tm.getText());
		    String msg = tm.getText();
		    String[] msgArgs = msg.split(" ");
		    int workerId = -1;
		    int data1 = 1;
		    int dataArr[] = new int[msgArgs.length - 1];
		    Worker worker = null;
		    try {
			if(msgArgs[0].equals("done")){
			    System.out.println("End of queue reached. Exiting.");
			    connection.close();
			    System.exit(0);
			}
			if(msgArgs.length == 2){
			    workerId = Integer.parseInt(msgArgs[0]);
			    data1 = Integer.parseInt(msgArgs[1]);
			    worker = new PrimeChecker(workerId, data1);
			    worker.doWork();
			}
			if(msgArgs.length == 3){
			    workerId = Integer.parseInt(msgArgs[0]);
			    for(int i = 1; i < msgArgs.length;i++){
				dataArr[i-1] = Integer.parseInt(msgArgs[i]); 
			    }
			    worker = new FractionReducer(workerId, dataArr);
			    worker.doWork();
			}
			if(msgArgs.length == 4){
			    workerId = Integer.parseInt(msgArgs[0]);
			    data1 = Integer.parseInt(msgArgs[1]);
			    worker = new ValueSorter(workerId, data1);
			    worker.doWork();
			}
			/*
			  else{
			  System.out.println("Unrecognized Message: Too many or too few args in the row");
			  System.exit(-1);
			  }
			*/		    
			ObjectMessage objectMessage = session.createObjectMessage(worker);
			producer.send(objectMessage);
		    } catch (NumberFormatException nfe) {
			System.out.println(
					   "Error: Unexpected message data: " + msg);
			System.out.println(nfe.getMessage());
			System.out.println("Unable to send response object");
		    }
		} else {
		    System.out.println("Unexpected message type");
		}
	    }
	} catch (JMSException e) {
	    e.printStackTrace();
	} 
	
    }
    
    private static void print(String msg) {
	System.out.println(msg);
    }
    
}
