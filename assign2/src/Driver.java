/*

Derek Popowski
derek.a.popowski@und.edu
CSci 364
Assignment 2
Dining Philosophers Arbitrator

Driver object that hold the main method

USAGE - java Driver [Runtime(ms)]

*/

public class Driver{//Driver that holds main method
	
    private static Fork[] forks = new Fork[5]; //array of forks to control with philosophers can eat
    
    public static void main(String[] args) throws InterruptedException{//main
	
	int sleepTime = 0;//variable to hold input command line args
	
	if(args.length < 1){//check if command line args were provided
	    
	    System.out.println("Usage: Java Driver [Runtime(ms)]");//if no args were input
	    return;//exit
	    
	}
	try{//try to parse input args
	    
	    sleepTime = Integer.parseInt(args[0]);
	    
	}
	catch (NumberFormatException e){//if args was not a number
	    
	    System.out.println("Invalid sleep time");//errors message
	    return;//exit
	    
	}
	//create the philosopher objects
	Philosopher alpha = new Philosopher("alpha",0,1);
	Philosopher bravo = new Philosopher("bravo",1,2);
	Philosopher charlie = new Philosopher("charlie",2,3);
	Philosopher delta = new Philosopher("delta",3,4);
	Philosopher echo = new Philosopher("echo",4,0);

	//initialize the forks
	for(int i = 0; i < 5; i++){
	    forks[i] = new Fork(i);
	}
	
	//start the threads and call the run() method
	Thread thread1 = new Thread(alpha);
	thread1.start();
	Thread thread2 = new Thread(bravo);
	thread2.start();
	Thread thread3 = new Thread(charlie);
	thread3.start();
	Thread thread4 = new Thread(delta);
	thread4.start();
	Thread thread5 = new Thread(echo);
	thread5.start();
	
	Thread.sleep(sleepTime);//sleep for input time
	//call the stop methods
	alpha.setStop(true);
	bravo.setStop(true);
	charlie.setStop(true);
	delta.setStop(true);
	echo.setStop(true);
	
	Thread.sleep(1);//ensure all threads exit before printing stats

	//Print stats for alpha
	System.out.println("-Alpha Stats-");
	System.out.println("  Thoughts: " + alpha.getThoughts() + " Meals: " + alpha.getMeals());
	System.out.println("  Total time (ns): " + alpha.getTime());
	System.out.println("  Critical time (ns): " + alpha.getCritTime());
	double ratio =(double) alpha.getCritTime() / alpha.getTime();
	System.out.println("  Time ratio Critical / Total: " + ratio);

	//print stats for bravo
	System.out.println("-Bravo Stats-");
	System.out.println("  Thoughts: " + bravo.getThoughts() + " Meals: " + bravo.getMeals());
	System.out.println("  Total time (ns): " + bravo.getTime());
	System.out.println("  Critical time (ns): " + bravo.getCritTime());
	ratio =(double) bravo.getCritTime() / bravo.getTime();
	System.out.println("  Time ratio Critical / Total: " + ratio);

	//print stats for charlie
	System.out.println("-Charlie Stats-");
	System.out.println("  Thoughts: " + charlie.getThoughts() + " Meals: " + charlie.getMeals());
	System.out.println("  Total time (ns): " + charlie.getTime());
	System.out.println("  Critical time (ns): " + charlie.getCritTime());
	ratio =(double) charlie.getCritTime() / charlie.getTime();
	System.out.println("  Time ratio Critical / Total: " + ratio);

	//print stats for delta
	System.out.println("-Delta Stats-");
	System.out.println("  Thoughts: " + delta.getThoughts() + " Meals: " + delta.getMeals());
	System.out.println("  Total time (ns): " + delta.getTime());
	System.out.println("  Critical time (ns): " + delta.getCritTime());
	ratio =(double) delta.getCritTime() / delta.getTime();
	System.out.println("  Time ratio Critical / Total: " + ratio);

	//print stats for echo
	System.out.println("-Echo Stats-");
	System.out.println("  Thoughts: " + echo.getThoughts() + " Meals: " + echo.getMeals());
	System.out.println("  Total time (ns): " + echo.getTime());
	System.out.println("  Critical time (ns): " + echo.getCritTime());
	ratio =(double) echo.getCritTime() / echo.getTime();
	System.out.println("  Time ratio Critical / Total: " + ratio);
	
    }
    
    public synchronized static boolean waiter(int Fork1, int Fork2){//method to ensure forks are picked up if only both are available
	
	if((forks[Fork1].getStatus())==true && (forks[Fork2].getStatus())==true){//check if both forks are available

	    //pick up the forks
	    forks[Fork1].setStatus(false);
	    forks[Fork2].setStatus(false);
	    return true;
	    
	}
	else//else dont pick up either
	    return false;
	
    }
    public static void releaseForks(int Fork1, int Fork2){//put down the forks
	
	forks[Fork1].setStatus(true);
	forks[Fork2].setStatus(true);
	
    }
    
}

