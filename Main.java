
import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) {
		FantasyTeam team = populateRoster("Roster.txt");
		ArrayList<Player> list = team.getPlayers();
		for(Player p : list){
			System.out.println(p.getName());
			System.out.println(p.getPosition());
			System.out.println(p.getTeam().toString());
			System.out.println();
		}

	}
	
	public static FantasyTeam populateRoster(String filename){
		File file = new File(filename);
		FantasyTeam team = new FantasyTeam();
		
		try{
			Scanner scnr = new Scanner(file);
			
			while(scnr.hasNextLine()){
				String line = scnr.nextLine();

				//format: fName lName team pos
				String[] parts = line.split(" ");
				NFLTeam nTeam = new NFLTeam(parts[2]);
				Player player = new Player(parts[0], parts[1], nTeam, parts[3]);
				team.addPlayer(player);	
				}
				
			
			
			scnr.close();
			return team;
		}catch(FileNotFoundException e){
			System.out.println("File could not be opened");
			return null;
		}
		
	}

}
