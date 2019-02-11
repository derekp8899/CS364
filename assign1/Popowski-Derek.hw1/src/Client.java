/*
Derek Popowski
derek.a.popowski@und.edu
CSCI 364
Program 1
Knock Knock joke client
*/

import java.net.Socket;
import java.net.ServerSocket;
import java.util.Random;
import java.io.*;

public class Client{

    public static void main(String[] args)throws IOException{

	int port = 0;
	long seed = 0;
	String jokeFile;
	String[] setUp = new String[1];
	String[] pLines = new String[1];
	ServerSocket servSock;
	
	if (args.length != 2) {
	    System.err.println("Usage: java KnockServer <host> <port number>");
	    System.exit(1);
	}
	String hostName = args[0];
	try{

	    port = Integer.parseInt(args[1]);

	}
	catch(NumberFormatException e){

	    System.err.println("invalid port number entered");
	    System.err.println("Usage: java KnockServer <port number> <joke file> <random seed>");
	    System.exit(1);
	    
	}

	try{

	    Socket echoSocket = new Socket(hostName, port);
	    PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
	    BufferedReader in =	new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
	    BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
	    out.println("Tell me a joke");
	    System.out.println("Client: Tell me a joke");
	    String inLine = in.readLine();
	    System.out.println("Server: " + inLine);
	    out.println("Who's there?");
	    System.out.println("Client: Who's there?");
	    inLine = in.readLine();
	    System.out.println("Server: " + inLine);
	    System.out.println("Client: " + inLine + " who?");
	    out.println(inLine + " who?");
	    inLine = in.readLine();
	    System.out.println("Server: " + inLine);
	    inLine = in.readLine();
	    System.out.println("Server: " + inLine);
	    
	}
	catch (Exception e) {
	    System.err.println("cannot connect to host " + hostName);
	    System.exit(1);
	} 
	
    }

}
