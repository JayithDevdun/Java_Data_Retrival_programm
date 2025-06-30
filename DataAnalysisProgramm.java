/*************************************************************************************************************
 *Author  :Jayith Devdun                                                                                     *
 *Date    :18 Octomber 2023                                                                                  *
 *Purpose :Retrive data from csv file and store it in a 2D Array and Perform various analysis according to it*/ 
 /*************************************************************************************************************/


import java.io.*;
import java.util.*;

class Team{
        //class fields
	private String TeamName;
	private String TeamCode;
	private int Goals;
	private int GoalsAgainst;
	private String Team;
	
public Team(String TeamName, String TeamCode, int Goals, int GoalsAgainst, String Team)
    {   
        //with parameter constructor
        this.TeamName = TeamName;
        this.TeamCode = TeamCode;
        this.Goals =  Goals;
        this.GoalsAgainst = GoalsAgainst;
        this.Team  = Team;
    }
    // Following are the Accessors (getters)
    public String getTeamName()
    {
        return TeamName;
    }

    public String getTeamCode()
    {
        return TeamCode;
    }

    public int getGoals()
    {
        return Goals;
    }

    public int getGoalsAgainst()
    {
        return GoalsAgainst;
    }

    public String getTeam()
    {
        return Team;
    }
    public int calculateNetGoals(){
    	return Goals-GoalsAgainst;
    }
}
public class DataAnalysisProgramm{
	public static void main(String[] args){
	do{
	    Scanner sc = new Scanner(System.in);
	    System.out.println("Welcome to the FIFA WWC Analysis Program");
	    System.out.println("Enter the name of the file containing the data:");
	    String fileName = sc.nextLine();
	    //create objects after reading csv file
	    Team[] teams = readDataFromFile(fileName);
	    //ask user to enter overall analysis or group analysis
	    System.out.println("An overall analysis or a Group analysis? (A:overall  G:group):");
            char Choice = sc.nextLine().toUpperCase().charAt(0);
            if (Choice == 'A') {
               performOverallAnalysis(teams);
            }else if (Choice == 'G') {
            //ask user to enter group for group analysis
            System.out.println("Enter the group (A, B, C, or D) for group analysis:");
            String groupChoice = sc.nextLine().toUpperCase();
            performGroupAnalysis(teams, groupChoice);
            }
            //ask user want to exit
            System.out.println("Would you like to exit?");
            String decition = sc.nextLine();
            //In here compare two string values using egualsIgnoreCase function
            if(decition.equalsIgnoreCase("yes")){
                //this is break statement, it's not much good to use but I used it in here
            	break;
            }
             //In here compare two string values using egualsIgnoreCase function
            else if(decition.equalsIgnoreCase("y")){
                break;
            }
             //In here compare two string values using egualsIgnoreCase function
             else if(decition.equalsIgnoreCase("YES")){
               break;
             }
              //In here compare two string values using egualsIgnoreCase function
              else if(decition.equalsIgnoreCase("Y")){
               break;          
              }
            
	    
        }while(true);
         System.out.println("Bye! Have a Good Day");
    }
        private static Team[] readDataFromFile(String fileName) {
          Team[] teams = new Team[100]; //can adjest bast on your data
          int index = 0;
          try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
             String line;
             // Skip the header line
             br.readLine();
             while ((line = br.readLine()) != null) {
               String[] data = line.split(",");
               //store teams data in Array
               if (data.length == 5) {
                  String TeamName = data[0];
                  String TeamCode = data[1];
                  int Goals = Integer.parseInt(data[2]);
                  int GoalsAgainst = Integer.parseInt(data[3]);
                  String Team = data[4];
                  teams[index] = new Team(TeamName, TeamCode, Goals, GoalsAgainst, Team);
                  index++;
               }
            }
        } catch (IOException e) {
        e.printStackTrace();
          }  
           return Arrays.copyOf(teams, index);
}


public static void performOverallAnalysis(Team[] teams){
        // Sort teams by total goals scored against them in descending order
        Arrays.sort(teams, (team1, team2) -> team2.getGoalsAgainst() - team1.getGoalsAgainst());
    
       System.out.println("Teams Sorted by Total Goals Scored Against Them:");
       for (Team team : teams) {
          int goalsAgainst = team.getGoalsAgainst();
          System.out.println(team.getTeamName() + ": Goals Against = " + goalsAgainst);
       }
         // Sort teams by total goals they scored in descending order
       Arrays.sort(teams, (team1, team2) -> team2.getGoals() - team1.getGoals());
       System.out.println("Teams Sorted by Total Goals Scored:");
       for (Team team : teams) {
          int goalsScored = team.getGoals();
          System.out.println(team.getTeamName() + ": Goals Scored = " + goalsScored);
         }

        // Sort teams by net goals in descending order
        Arrays.sort(teams, (team1, team2) -> team2.calculateNetGoals() - team1.calculateNetGoals());
        System.out.println("Teams Sorted by NetGoals");
	for (Team team : teams) {
            int netGoals = team.calculateNetGoals();
            System.out.println(team.getTeamName() + ": Net Goals = " + netGoals);
        }
         // Find and display the best performing team
       Team bestTeam = teams[0];
       int bestNetGoals = bestTeam.calculateNetGoals();
       for (Team team : teams) {
        int netGoals = team.calculateNetGoals();
        if (netGoals > bestNetGoals) {
            bestTeam = team;
            bestNetGoals = netGoals;
        }
    }
    //Display best performing team
     System.out.println("Best Performing Team: " + bestTeam.getTeamName());
     // Sort teams by net goals in descending order
    Arrays.sort(teams, (team1, team2) -> team2.calculateNetGoals() - team1.calculateNetGoals());

    System.out.println("Teams Sorted by Net Goals:");
    for (Team team : teams) {
        int netGoals = team.calculateNetGoals();
        System.out.println(team.getTeamName() );
    }
        
   }
   private static void performGroupAnalysis(Team[] teams, String selectedGroup) {
    System.out.println("Group Analysis for Group " + selectedGroup + ":");

    int GrouptotalGoals = 0;
    int GroupAgainsttotalGoals = 0;
    // Initialize variables for best performing team
    Team bestTeam = null;
    //get minimum value of netgoals
    int bestNetGoals = Integer.MIN_VALUE;
    //Sort teams by net goals in descending order
    Arrays.sort(teams, (team1, team2) -> team2.calculateNetGoals() - team1.calculateNetGoals());
    for (Team team : teams) {
        if (team.getTeam().equalsIgnoreCase(selectedGroup)) {
            System.out.println("Team Name: " + team.getTeamName());
            System.out.println("Team Code: " + team.getTeamCode());
            System.out.println("Goals Scored: " + team.getGoals());
            System.out.println("Goals Scored Against: " + team.getGoalsAgainst());
            
            int netGoals = team.calculateNetGoals();
            System.out.println("Net Goals: " + netGoals);
            
            // Accumulate goals for the group
            GrouptotalGoals += team.getGoals();
             GroupAgainsttotalGoals += team.getGoalsAgainst();
            System.out.println();
            
            //if netgoals greater than bestnetgoals it store again in bestnetgoals
            if (netGoals > bestNetGoals) {
                bestNetGoals = netGoals;
                bestTeam = team;
            }
        }
    }
    

    System.out.println("Total Goals Scored for Group " + selectedGroup + ": " +  GrouptotalGoals );
    System.out.println("Total Goals Scored Against Group " + selectedGroup + ": " +  GroupAgainsttotalGoals);
    // Display the best performing team (inside the group)
    if (bestTeam != null) {
        System.out.println("Best Performing Team in Group " + selectedGroup + ": " + bestTeam.getTeamName());
    } else {
        System.out.println("No teams found in Group " + selectedGroup);
    }
  }
  
}



 
    
    

   
