import java.util.concurrent.TimeUnit;

public class Driver{
	
	static Fork[] forks = new Fork[5];
	
	public static void main(String[] args) throws InterruptedException{

		Philosopher alpha = new Philosopher("alpha",0,1);
		Philosopher bravo = new Philosopher("bravo",1,2);
		Philosopher charlie = new Philosopher("charlie",2,3);
		Philosopher delta = new Philosopher("delta",3,4);
		Philosopher echo = new Philosopher("echo",4,0);
		String name = alpha.getName();
		System.out.println(name);
		System.out.println(alpha.getStop());
		
		for(int i = 0; i < 5; i++){
			forks[i] = new Fork(i);
			System.out.println(forks[i].getStatus());
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
	
		TimeUnit.SECONDS.sleep(3);
		alpha.setStop(true);
		bravo.setStop(true);
		charlie.setStop(true);
		delta.setStop(true);
		echo.setStop(true);
	
		System.out.println(" alpha thoughts "+alpha.getThoughts()+" alpha meals "+alpha.getMeals());		
		System.out.println(" bravo thoughts "+bravo.getThoughts()+" bravo meals "+bravo.getMeals());
System.out.println(" charlie thoughts "+charlie.getThoughts()+" charlie meals "+charlie.getMeals());
System.out.println(" delta thoughts "+delta.getThoughts()+" delta meals "+delta.getMeals());
System.out.println(" echo thoughts "+echo.getThoughts()+" echo meals "+echo.getMeals());
	}

	public synchronized static boolean waiter(int Fork1, int Fork2){

		if(forks[Fork1].getStatus()==true && forks[Fork2].getStatus()==true){
			
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

