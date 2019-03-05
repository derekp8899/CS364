/**
 * Derek Popowski
 * derek.a.popowski@und.edu
 * CSci365 Concurrent and Distributed Programming
 * ValueSorter.java
 */
package api;

import java.util.Arrays;

/**
 * Sorts the input array
 * 
 * @author Derek Popowski
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
     * @param id,val
     */
    public ValueSorter(int id, String[] val) {
	super(id);
	value = new String[val.length -1];
	sorted = new String[val.length -1];
	for(int i = 1; i < val.length; i++)//to store the original values    
	    value[i-1] = val[i];
	for(int i = 1; i < val.length; i++)//to store the sorted values
	    sorted[i-1] = val[i];
    }
    
    /* (non-Javadoc)
     * @see hw3.Worker#doWork()
     */
    @Override
    public void doWork() {
	Arrays.sort(sorted);//sort the array with standard Array.sort method
    }
    
    public String getWorkResults() {//generate the output string
	String rtString;
	rtString = "ID: " + super.getTaskId()+ ", " +"Input: ";
	for(int i = 0 ; i < 3;i++)
	    rtString = rtString + value[i] + " ";
	rtString = rtString + ". Output: ";
	for(int i = 0 ; i < 3;i++)
	    rtString = rtString + sorted[i] + " ";

	return rtString;
    }
    
    /**
     * Test harness
     * @param args There are no arguments.
     */
    public static void main(String[] args) {
    }
}
