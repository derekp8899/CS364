public class interruptEx{

    public static void main(String[] args){

	System.out.println();
	Thread t = Thread.currentThread();
	t.interrupt();
	if(Thread.interrupted()){
	    
	    System.out.println("Thread interrupt");
	    //if the current thread is interrupted
	    
	}
	if(t.isInterrupted()){
	    
	    System.out.println("t interreupt");
	    //if thread 't' is interrupted
	    
	}
	
    }
    
}
