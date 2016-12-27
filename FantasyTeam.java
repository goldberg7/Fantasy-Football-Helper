import java.util.ArrayList;

public class FantasyTeam {
	
	private ArrayList<Player> players_list;
	int numPlayers;
	private PriorityQueue<NFLTeam> pq;	
	
	public FantasyTeam(){
		players_list = new ArrayList<Player>();
		pq = new PriorityQueue<NFLTeam>();
		numPlayers = 0;
	}
	
	public ArrayList<Player> getPlayers(){
		return players_list;
	}
	
	public void addPlayer(Player player){
		players_list.add(player);
	}
}
