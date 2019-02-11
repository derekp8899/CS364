/*
Derek Popowski
derek.a.popowski@und.edu
CSCI 364
Program 1
Knock Knock joke server
*/

import java.net.Socket;
import java.net.ServerSocket;
import java.util.Random;
import java.io.*;

public class Server{

    public static void main(String[] args)throws IOException{

	int port = 0;
	long seed = 0;
	String jokeFile;
	String[] setUp = new String[1];
	String[] pLines = new String[1];
	ServerSocket servSock;
	
	if (args.length != 3) {
	    System.err.println("Usage: java KnockServer <port number> <joke file> <random seed>");
	    System.exit(1);
	}

	try{

	    port = Integer.parseInt(args[0]);

	}
	catch(NumberFormatException e){

	    System.err.println("invalid port number entered");
	    System.err.println("Usage: java KnockServer <port number> <joke file> <random seed>");
	    System.exit(1);
	    
	}

	try{

	    jokeFile = args [1];
	    FileReader jokes = new FileReader(jokeFile);
	    char[] data = new char[1000];
	    jokes.read(data);
	    int jokeCount = 0;
	    for(char c : data){
		if(c == '\n')
		    jokeCount++;
	    }
	    setUp = new String[jokeCount];
	    pLines = new String[jokeCount];
	    String jokedata = new String(data);
	    String[] splits = jokedata.split(" # |\n",jokeCount*2);
	    int j = 0;
	    for( int i = 0; i < splits.length; i+=2){

		setUp[j] = splits[i];
		pLines[j] = splits[i+1];
		j++;
		
	    }
	    
	}
	catch(FileNotFoundException e){

	    System.err.println("invalid joke file entered");
	    System.err.println("Usage: java KnockServer <port number> <joke file> <random seed>");
	    System.exit(1);
	    
	}
	try{
	    
	    seed = Long.parseLong(args[2]);

	}
	catch(NumberFormatException e){

	    System.err.println("invalid seed number entered");
	    System.err.println("Usage: java KnockServer <port number> <joke file> <random seed>");
	    System.exit(1);
	    
	}
	try{
	    Random rng = new Random(seed);
	    servSock= new ServerSocket(port);
	    while(true){
		/*
		for(String c : setUp)
		    System.out.println(c);
		for(String c : pLines)
		    System.out.println(c);
		*/
		int jokeNum = rng.nextInt(setUp.length);
		System.out.println("waiting for client connection on port " + port);
		Socket clientSock = servSock.accept();
		PrintWriter out = new PrintWriter(clientSock.getOutputStream(),true);
		BufferedReader in = new BufferedReader(new InputStreamReader(clientSock.getInputStream()));
		System.out.println("connection established with a client at \n  " + clientSock.getInetAddress() + " on port " + clientSock.getPort() + "\n");
		
		String inputLine;
		while((inputLine = in.readLine()) != null){
		    
		    if(inputLine.equals("Tell me a joke")){

			System.out.println("Client: " + inputLine);
			out.println("Knock Knock");
			System.out.println("Server: Knock Knock");
			
		    }
		    else if(inputLine.equals("Who's there?")){

			System.out.println("Client: " + inputLine);
			out.println(setUp[jokeNum]);
			System.out.println("Server: " + setUp[jokeNum]);
			
		    }
		    else if(inputLine.equals(setUp[jokeNum] + " who?")){

			System.out.println("Client: " + inputLine);
			out.println(pLines[jokeNum]);
			System.out.println("Server: " + pLines[jokeNum]);
			out.println("Bye.");
			System.out.println("Server: Bye.");
			System.out.println();
	
		    }
		    else
			out.println("invalid input: " + inputLine);
		}
	    }
	    
	}
	catch(IOException e){

	    System.out.println("exception while listening for a client connection");

	}

    }

}
