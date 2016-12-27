
public class NFLTeam implements Comparable<NFLTeam> {
	String name;
	boolean isPlaying;
	boolean isOnOffense;
	int fieldPosition; //0 to 100
	
	public NFLTeam(String name){
		this.name = name;
	}
	
	public int compareTo(NFLTeam other)
	{
		/*
		 * Priority goes by:
		 * if not playing - automatic lower priority 
		 * if team is on offense and other isn't - auto higher priority
		 * otherwise: whoever has better field position
		 */
		
		//if both teams not playing -> same priority, otherwise 
		//other team has priority
		if(!this.isPlaying){
			return other.getPlaying() ? -1 : 0;
		}
		
		if(this.isOnOffense && !other.getOffense()){
			return 1;
		}
		
		if(!this.isOnOffense && other.getOffense()){
			return -1;
		}
		
		return this.getPosition() - other.getPosition();
	}	
	
	@Override 
	public String toString(){
		return this.name;
	}
	
	public void setPlaying(boolean status){
		this.isPlaying = status;
	}
	
	public void setOffense(boolean status){
		this.isOnOffense = status;
	}
	
	public void setFieldPosition(int position){
		this.fieldPosition = position;
	}
	
	public boolean getPlaying(){
		return this.isPlaying;
	}
	
	public boolean getOffense(){
		return this.isOnOffense;
	}
	
	public int getPosition(){
		return this.fieldPosition;
	}
}
