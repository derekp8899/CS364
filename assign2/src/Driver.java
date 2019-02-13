/*

Derek Popowski
derek.a.popowski@und.edu
CSci 364
Assignment 2
Dining Philosophers Arbitrator

*/


import java.util.concurrent.TimeUnit;

public class Driver{
	
	static Fork[] forks = new Fork[5];
	
	public static void main(String[] args) throws InterruptedException{

	    int sleepTime = 0;
	    
	    if(args.length < 1){

		System.out.println("Usage: Java Driver [Runtime(ms)]");
		return;

	    }
	    try{
		
		sleepTime = Integer.parseInt(args[0]);

	    }
	    catch (NumberFormatException e){
		    
		    System.out.println("Invalid sleep time");
		    return;

	    }
	    Philosopher alpha = new Philosopher("alpha",0,1);
	    Philosopher bravo = new Philosopher("bravo",1,2);
	    Philosopher charlie = new Philosopher("charlie",2,3);
	    Philosopher delta = new Philosopher("delta",3,4);
	    Philosopher echo = new Philosopher("echo",4,0);
	    
	    for(int i = 0; i < 5; i++){
		forks[i] = new Fork(i);
	    }
	    
	    
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
	    
	    //	    TimeUnit.SECONDS.sleep(3);
	    Thread.sleep(sleepTime);
	    alpha.setStop(true);
	    bravo.setStop(true);
	    charlie.setStop(true);
	    delta.setStop(true);
	    echo.setStop(true);

	    Thread.sleep(1);//ensure all threads exit before printing stats
	    
	    System.out.println("-Alpha Stats-");
	    System.out.println("  Thoughts: " + alpha.getThoughts() + " Meals: " + alpha.getMeals());
	    System.out.println("  Total time (ns): " + alpha.getTime());
	    System.out.println("  Critical time (ns): " + alpha.getCritTime());
	    double ratio =(double) alpha.getCritTime() / alpha.getTime();
	    System.out.println("  Time ratio Critical / Total: " + ratio);

	    System.out.println("-Bravo Stats-");
	    System.out.println("  Thoughts: " + bravo.getThoughts() + " Meals: " + bravo.getMeals());
	    System.out.println("  Total time (ns): " + bravo.getTime());
	    System.out.println("  Critical time (ns): " + bravo.getCritTime());
	    ratio =(double) bravo.getCritTime() / bravo.getTime();
	    System.out.println("  Time ratio Critical / Total: " + ratio);
	    
	    System.out.println("-Charlie Stats-");
	    System.out.println("  Thoughts: " + charlie.getThoughts() + " Meals: " + charlie.getMeals());
	    System.out.println("  Total time (ns): " + charlie.getTime());
	    System.out.println("  Critical time (ns): " + charlie.getCritTime());
	    ratio =(double) charlie.getCritTime() / charlie.getTime();
	    System.out.println("  Time ratio Critical / Total: " + ratio);
	    
	    System.out.println("-Delta Stats-");
	    System.out.println("  Thoughts: " + delta.getThoughts() + " Meals: " + delta.getMeals());
	    System.out.println("  Total time (ns): " + delta.getTime());
	    System.out.println("  Critical time (ns): " + delta.getCritTime());
	    ratio =(double) delta.getCritTime() / delta.getTime();
	    System.out.println("  Time ratio Critical / Total: " + ratio);

	    System.out.println("-Echo Stats-");
	    System.out.println("  Thoughts: " + echo.getThoughts() + " Meals: " + echo.getMeals());
	    System.out.println("  Total time (ns): " + echo.getTime());
	    System.out.println("  Critical time (ns): " + echo.getCritTime());
	    ratio =(double) echo.getCritTime() / echo.getTime();
	    System.out.println("  Time ratio Critical / Total: " + ratio);
	    	    
	}
    
    public synchronized static boolean waiter(int Fork1, int Fork2){
	
	if((forks[Fork1].getStatus())==true && (forks[Fork2].getStatus())==true){
	    
	    forks[Fork1].setStatus(false);
	    forks[Fork2].setStatus(false);
	    return true;
	    
	}
	else
	    return false;
	
    }
    public static void releaseForks(int Fork1, int Fork2){
	
	forks[Fork1].setStatus(true);
	forks[Fork2].setStatus(true);
	
    }
    
}

