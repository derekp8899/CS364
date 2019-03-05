/**  Derek Popowski
 *   derek.a.popowski@und.edu
 *   CSci 364
 *   Program 3 - FractionReducer worker Class
 *   Fraction Reucer class implementing the Worker abstract class
 */
package api;

/**
 *
 * 
 * @author Derek Popowski
 */
public class FractionReducer extends Worker {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private int[] value;
    private int[] reduced;
    
    /**
     * @param id
     */
    public FractionReducer(int id, int[] val) {
	super(id);
	value = val;
	reduced = new int[val.length];//init the reduced values to zero
    }
    
    /* (non-Javadoc)
     * @see hw3.Worker#doWork()
     */
    @Override
    public void doWork() {
	int GCF = 1;
	if(value[0] < value[1]){
	    for(int i = 1; i <= value[0];i++){
		if((value[0]%i) == 0 && (value[1]%i) == 0)
		    GCF = i;
	    }
	}
	else{
	    for(int i = 1; i <= value[1];i++){
		if((value[1]%i) == 0 && (value[0]%i) == 0)
		    GCF = i;
	    }
	}
	reduced[0] = value[0]/GCF;
	reduced[1] = value[1]/GCF;
    }
    
    public String getWorkResults() {
	String rtString;
	rtString = "ID: " + super.getTaskId() + ", Input: " + value[0] + " " + value[1];
	rtString = rtString + ". Output: " + reduced[0] +"/" + reduced[1];
	return rtString;
	}
    
    /**
     * Test harness
     * @param args There are no arguments.
     */
    public static void main(String[] args) {
	return;
    }
}
