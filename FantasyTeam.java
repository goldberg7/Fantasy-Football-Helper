import java.util.ArrayList;

public class FantasyTeam {
	
	private ArrayList<Player> players_list;
	int numPlayers;
	
	
	public FantasyTeam(){
		players_list = new ArrayList<Player>();
		
		numPlayers = 0;
	}
	
	public ArrayList<Player> getPlayers(){
		return players_list;
	}
	
	public void addPlayer(Player player){
		players_list.add(player);
	}
}
