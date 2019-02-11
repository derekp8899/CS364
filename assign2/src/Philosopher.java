import java.util.concurrent.TimeUnit;

public class Philosopher implements Runnable{

    private String name;
    private int fork1, fork2;
    private int thoughts, meals;
    private double time;
    private boolean stop;
    
    public void run(){

	int i = 0;
	while(i < 100){

	    System.out.println(i);
	    i++;
	}

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

    public double getTime(){

	return this.time;

    }

    public boolean getStop(){

	return this.stop;

    }

    public void setStop(boolean in){

	this.stop = in;

    }
}
