import java.util.concurrent.TimeUnit;

public class Philosopher implements Runnable{

    private String name;
    private int fork1, fork2;
    private int thoughts, meals;
    private long time;
    private boolean stop;
    private long critTime;
    private long tempTime;
    public void run(){

	int i = 0;
	tempTime = 0;
	critTime = 0;
	time = System.nanoTime();
	while(!stop){
	    
	    tempTime = System.nanoTime();
	    if(Driver.waiter(fork1,fork2)==true){

		tempTime = System.nanoTime() - tempTime;
		critTime += tempTime;
		meals++;
		Driver.releaseForks(fork1,fork2);
		System.out.println(name + " is eating");

	    }		
	    else{

		tempTime = System.nanoTime() - tempTime;
		critTime += tempTime;
		thoughts++;
		System.out.println(name + " is thinking");

	    }
		
	}

	time = System.nanoTime() - time;
	
    }
    
    public Philosopher(String inName, int forkId1, int forkId2){

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
