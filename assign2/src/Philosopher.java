/*

Derek Popowski
derek.a.popowski@und.edu
CSci 364
Assignment 2
Philosopher object

*/

public class Philosopher implements Runnable{//Philosopher objects that implement Runnable interface

    private String name;//philosopher's name
    private int fork1, fork2;//the 'left' and 'right' forks needed to eat
    private int thoughts, meals;//counters for thoughts and meals
    private long time;//total time the thread is run
    private boolean stop;//run loop stop flag
    private long critTime;//time spent checking if the forks and available and possible picking them up
    private long tempTime;//time in critical section for each loop iteration

    public void run(){//called when thread.start is called in main

	//init loop vars
	tempTime = 0;
	critTime = 0;
	time = System.nanoTime();

	while(!stop){//while stop condition is not set in main

	    thoughts++;//inc thought counter
	    System.out.println(name + " is thinking");//used for time variance
	    tempTime = System.nanoTime();//start timer for critical section

	    if(Driver.waiter(fork1,fork2)==true){//if both forks are available pick them up and start eating

		tempTime = System.nanoTime() - tempTime;//stop critical timer
		critTime += tempTime;//update total critical time
		meals++;//inc meal counter
		System.out.println(name + " is eating");//used for time variance
		Driver.releaseForks(fork1,fork2);//put down the forks in use

	    }
	    else{//if forks are not available

		tempTime = System.nanoTime() - tempTime;//stop critical timer
		critTime += tempTime;//update total critical time

	    }

 	}

	time = System.nanoTime() - time;//stop total time
	
    }
    
    public Philosopher(String inName, int forkId1, int forkId2){//constructor to initalize instance variables

	//initialize all object variables
	this.name = inName;
	this.fork1 = forkId1;
	this.fork2 = forkId2;
	this.stop = false;
	this.thoughts = 0;
	this.meals = 0;
	this.time = 0;
	this.critTime = 0;

    }
    //All getters and setters for instance variables
    
    public String getName(){

	return this.name;
	
    }

    public int getThoughts(){

	return this.thoughts;

    }

    public int getMeals(){

	return this.meals;

    }

    public long getTime(){

	return this.time;

    }

    public boolean getStop(){

	return this.stop;

    }

    public void setStop(boolean in){

	this.stop = in;

    }

    public long getCritTime(){

	return this.critTime;

    }
    
}
