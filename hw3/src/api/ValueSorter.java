/**
 * PrimeChecker.java
 */
package api;

import java.util.Arrays;

/**
 * A not particularly efficient way to test if an integer is prime.
 * 
 * @author david apostal
 */
public class ValueSorter extends Worker {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String[] value;
    private boolean prime = true;
    private String[] sorted;
    /**
     * @param id
     */
    public ValueSorter(int id, String[] val) {
	super(id);
	value = new String[val.length -1];
	sorted = new String[val.length -1];
	for(int i = 1; i < val.length; i++)	    
	    value[i-1] = val[i];
	for(int i = 1; i < val.length; i++)	    
	    sorted[i-1] = val[i];

	for(String t:value)
	    System.out.println("DEBUG: "+t);
	System.out.println("DEBUG SIZE: " + value.length);
    }
    
    /* (non-Javadoc)
     * @see hw3.Worker#doWork()
     */
    @Override
    public void doWork() {
	Arrays.sort(sorted);
    }
    
    public String getWorkResults() {
	String rtString;
	rtString = "Input: ";
	for(int i = 0 ; i < 3;i++)
	    rtString = rtString + value[i];
	rtString = rtString + " Output: ";
	for(int i = 0 ; i < 3;i++)
	    rtString = rtString + sorted[i];

	return rtString;
	
    }
    
    /**
     * Test harness
     * @param args There are no arguments.
     */
    public static void main(String[] args) {
	int num = Integer.parseInt(args[0]);
	int taskId = 1;
	PrimeChecker pc = new PrimeChecker(taskId, num);
	pc.doWork();
	System.out.println(num + " is prime. " +  pc.getWorkResults());
    }
}
