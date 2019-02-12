public class Fork{

	private int ForkId;
	private boolean free;	

	public Fork (int id){

		ForkId = id;
		free = true;

	}

	public boolean getStatus(){

		return free;	
	
	}

	public void setStatus(boolean newStatus){

		free = newStatus;

	}
}
