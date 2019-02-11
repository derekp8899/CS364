public class Driver{

    public static void main(String[] args){

	Philosopher alpha = new Philosopher("alpha",1,2);
	String name = alpha.getName();
	System.out.println(name);
	System.out.println(alpha.getStop());
	Thread thread1 = new Thread(alpha);
	thread1.start();
	
    }

}
