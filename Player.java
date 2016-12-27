
public class Player{
	String position;
	String firstName;
	String lastName;
	NFLTeam team;

	public Player(String fName, String lName, NFLTeam team, String position){
		this.firstName = fName;
		this.lastName = lName;
		this.position = position;
		this.team = team;
	}
	
	public String getPosition(){
		return this.position;
	}
	
	public String getName(){
		return this.firstName + " " + this.lastName;
	}
	
	public NFLTeam getTeam(){
		return this.team;
	}
}
