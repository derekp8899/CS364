/**  Derek Popowski
 *   derek.a.popowski@und.edu
 *   CSci 364
 *   Program 3 - Manager Class
 *   Create the messages for the workers to process and sends them to the activemq queue
 */
package manager;
//import arrays
import java.util.ArrayList;
import java.util.Arrays;
//import file IO classes
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
//import JMS classes
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
//import activemq Commections
import org.apache.activemq.ActiveMQConnectionFactory;
//import api from hw3
import api.BrokerConfig;
import api.Worker;

/**
 * @author david and Derek Popowski
 *
 */
public class Manager implements BrokerConfig {
	
	/**
	 * @param args ignored
	 */
	public static void main(String[] args) {
	    String inFile = null;
	    if(args.length<1){
		System.out.println("No input file specified please try again\nUSAGE: Manager [source file]");
		System.exit(-1);
	    } else {
		inFile = args[0];
	    }
	    
	    ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(DEFAULT_BROKER);
	    // make client trust deserializing objects in package 'api'
	    factory.setTrustedPackages(new ArrayList<String>(Arrays.asList("api")));
	    Connection connection = null;
	    try {
		connection = factory.createConnection();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination dataQueue = session.createQueue(DATA_QUEUE);
		Destination workerQueue = session.createQueue(WORKER_QUEUE);
		MessageProducer producer = session.createProducer(dataQueue);
		MessageConsumer consumer = session.createConsumer(workerQueue);
		
		connection.start();
		
		// create the JMS message
		// check the file for the message and send to the queue
		BufferedReader fileReader = null;
		try{
		    fileReader = new BufferedReader(new FileReader(inFile));//for reading lines from the file
		}
		catch(FileNotFoundException e){
		    System.out.println("Specified File Not Found Please Try Again.");
		    System.exit(-1);
		}

		String msgString;//store the current line
		int jobsSent = 0;
		try{
		    while((msgString = fileReader.readLine()) != null){//while there are still lines to be read from the file
			TextMessage textMessage = session.createTextMessage(msgString);
			producer.send(textMessage);
			jobsSent++;
		    }
		    	TextMessage textMessage = session.createTextMessage("done");
			producer.send(textMessage);
		
		}
		catch(IOException e){
		    System.out.println("IOException while trying to read input file\n-EXITING-\n");
		    System.exit(-1);
		}
		for(int i = 0; i < jobsSent; i++){
		    Message message = consumer.receive();
		    if (message instanceof ObjectMessage) {
			ObjectMessage om = (ObjectMessage)message;
			Object obj = om.getObject();
			if (obj instanceof Worker) {
			    Worker in = (Worker)obj;
			    System.out.println(in.getWorkResults());
			} else {
			    System.out.println("Unknown object");
			}
		    } else if (message instanceof TextMessage) {
			TextMessage tm = (TextMessage)message;
			System.out.println(tm.getText());
		    } else {
			System.out.println("Unknown message type");
		    }
		}
		System.out.println("All jobs processed. Exiting.");
		
		connection.close();
	    } catch (JMSException e) {
		e.printStackTrace();
	    } 
	}
}
