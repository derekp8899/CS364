public class lockEx{

    private list lock = new ArrayList();

    public Object remove(Object item){
	
	synchronized(lock){
	    while(lock.isEmpty){
		lock.wait();
	    }
	    Object x = lock.get(0);
	    return x;
	}
	
    }
    
    public void add(Object item){
	
	synchronized(lock){
	    
	    lock.add(item);
	    lock.notifyAll();
	    
	}
	
    }

}
