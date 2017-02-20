
import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) {
		FantasyTeam fantasyTeam = populateFantasyRoster("FantasyRoster.txt");
		ArrayList<Player> list = fantasyTeam.getPlayers();
		ArrayList<NFLTeam> liveTeams = generateGamesFromSnap("GameSnap.txt");
		ArrayList<NFLTeam> fullRosters = populateFullRosters("NFLRosters.txt");

		
		liveTeams.sort(null);
		liveTeams = reverse(liveTeams);
		liveTeams = removeDefensiveTeams(liveTeams);
		
		for(NFLTeam aTeam: liveTeams){
			System.out.println(aTeam.toString());
		}
	}
	
	public static FantasyTeam populateFantasyRoster(String filename){
		File file = new File(filename);
		FantasyTeam team = new FantasyTeam();
		
		try{
			Scanner scnr = new Scanner(file);
			
			while(scnr.hasNextLine()){
				String line = scnr.nextLine();

				//format: fName lName team pos
				String[] parts = line.split(" ");
				NFLTeam nTeam = new NFLTeam(parts[2].toUpperCase());
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
	
	public static ArrayList<NFLTeam> generateGamesFromSnap(String filename){
		try{
			File file = new File(filename);
			Scanner scnr = new Scanner(file);
			
			ArrayList<NFLTeam> teams = new ArrayList<NFLTeam>();
			int lineCase = 0;
			
			while(scnr.hasNextLine()){
				String line = scnr.nextLine();
				/*GameSnap has two text sections, the first is the 
				 * list of teams playing, and the second is a sample
				 * of potential field position
				 * */
				switch(line){
				case "#Games":
					lineCase = 1;
					line = scnr.nextLine();
					break;
				case "#Snapshot":
					lineCase = 2;
					line = scnr.nextLine();
					break;
				default:
					break;
				}
				
				if(lineCase == 1){
					//Format: tName, vs., tName2
					String[] parsedLine = line.split(" ");
					
					NFLTeam team1 = new NFLTeam(parsedLine[0]);
					NFLTeam team2 = new NFLTeam(parsedLine[2]);
					
					/*For purposes of later comparing NFL Teams with fantasy
					 * players, also keep a data member for the acronym (ie.
					 * first few letters of a teams name*/
					team1.setAcronym(parsedLine[0]);
					team2.setAcronym(parsedLine[2]);
					
					/*Override default boolean values for 
					 * newly constructed NFLTeams		*/
					team1.setPlaying(true);
					team1.setOpponent(team2);
					
					team2.setPlaying(true);
					team2.setOpponent(team1);
					
					teams.add(team1);
					teams.add(team2);
					
				}
				else{
					//Format: Offense: tName position int
					String[] parsedLine = line.split(" ");
					NFLTeam team = getTeamFromName(teams, parsedLine[1]);
					team.setOffense(true);
					team.setFieldPosition(Integer.parseInt(parsedLine[3]));
				}
			}
			
			return teams;
		}catch(FileNotFoundException e){
			System.out.println("File could not be opened");
			return null;
		}
	}

	private static NFLTeam getTeamFromName(ArrayList<NFLTeam> teams, String name){
		for(NFLTeam team : teams){
			if(team.toString().equals(name)){
				return team;
			}
		}
		
		return null;
	}
	
	public static ArrayList<NFLTeam> reverse(ArrayList<NFLTeam> list) {
	    for(int i = 0, j = list.size() - 1; i < j; i++) {
	        list.add(i, list.remove(j));
	    }
	    return list;
	}
	
	public static ArrayList<NFLTeam> populateFullRosters(String filename){
		try{
			File file = new File(filename);
			Scanner scnr = new Scanner(file);
			ArrayList<NFLTeam> teams = new ArrayList<NFLTeam>();
			
			/*Because the first line is the name of an nfl team,
			 * we don't have to worry about adding a player to
			 * a null team
			 */
			
			NFLTeam team = null;
			
			while(scnr.hasNextLine()){
				String line = scnr.nextLine();
				if(line.contains("#")){
					if(team != null){
						teams.add(team);
					}
					/*create a new team without the # in the name*/
					team = new NFLTeam(line.substring(1, line.length()));
					/*Similarly to populating from the snap, use an acronym
					 * for ease of use in the main method*/
					if(line.substring(1,4).equals("NEW")){
						/*NEW E, NEW Y*/
						team.setAcronym(line.substring(1, 4) + line.charAt(6));
					}
					else{
						team.setAcronym(line.substring(1, 4));
					}
				}
				else{
					team.addPlayer(line);
				}
			}
			
			
			
			scnr.close();
			return teams;
		}catch(FileNotFoundException e){
			return null;
		}
		//return null;
	}
	
	public static ArrayList<NFLTeam> removeDefensiveTeams(ArrayList<NFLTeam> teams){
		ArrayList<NFLTeam> retu = new ArrayList<NFLTeam>();
		
		for(NFLTeam team : teams){
			/*Teams list will be passed in sorted, so when we get to 
			 * a team not on offense, break the loop for O(N/2) complexity*/
			if(!team.getOffense())
				break;
			retu.add(team);
		}
		
		return retu;
	}
}
