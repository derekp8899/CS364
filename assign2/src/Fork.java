/*

Derek Popowski
derek.a.popowski@und.edu
CSci 364
Assignment 2
Fork object

*/

public class Fork{//class to store the status of the forks

    private int ForkId;
    private boolean free;	

    public Fork (int id){//contructor to set fork is and set status to true(not in use)
	
	ForkId = id;
	free = true;
	
    }

    //all getters and setters for instance variables
    public boolean getStatus(){
	
	return free;	
	
    }
    
    public void setStatus(boolean newStatus){
	
	free = newStatus;
	
    }
    
}
