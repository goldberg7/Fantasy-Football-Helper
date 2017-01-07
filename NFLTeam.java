import java.util.ArrayList;

public class NFLTeam implements Comparable<NFLTeam> {
	private String name;
	private boolean isPlaying;
	private boolean isOnOffense;
	private int fieldPosition; //0 to 100
	private NFLTeam opponent;
	private ArrayList<String> players;
	
	
	public NFLTeam(String name){
		this.name = name;
		
		/* Default init values*/
		this.isPlaying = false;
		this.isOnOffense = false; 
		this.fieldPosition = 0;
		this.opponent = null;
		players = new ArrayList<String>();
		
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
	
	public void addPlayer(String name){
		players.add(name);
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
	
	public void setOpponent(NFLTeam team){
		this.opponent = team;
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
	
	public NFLTeam getOpponent(){
		return this.opponent;
	}
	
	public ArrayList<String> getPlayersList(){
		return this.players;
	}
	
	public void printRoster(){
		for(String player : players){
			System.out.println("\t" + player);
		}
	}
}
