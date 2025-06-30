/*************************************************************
 *Author  :Jayith Devdun                                     *
 *Date    :18 Octomber 2023                                  *
 *Purpose :Get team data from user and store it in a csv file*/ 
 /************************************************************/


import java.util.*;
import java.io.*;
public class DataInputProgramm{
	public static void main(String[] args){
	        writeHeaders();//this method called for Add Headers to csv file
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to the FIFA WWC Data Entry Program");
		do{
		System.out.println("How many teams’ data are you planning to enter?");
		int numTeams =sc.nextInt();
		sc.nextLine(); // Consume the newline character
		System.out.println("Enter the data:");
		for(int i=0; i < numTeams; i++){
			String filename = "data01.csv";
			System.out.println("Team Name:");
			String TeamName =sc.nextLine();//get user input
			//for check empty string or not
			while (TeamName.isEmpty()) {
                        System.out.println("Team Name cannot be empty. Please enter a valid Team Name.");
                        System.out.println("Team Name:");
		        TeamName =sc.nextLine();
                        }
                        
			System.out.println("Team code:");
			String TeamCode =sc.nextLine();
			//for check empty string or not
			while(TeamCode.isEmpty()) {
                        System.out.println("Team Code cannot be empty. Please enter a valid Team Code.");
                        System.out.println("Team code:");
		        TeamCode =sc.nextLine();
                        }
                        
			System.out.println("Goals Scored by the Team:");
			int TeamGoals =sc.nextInt();
			//check integers are negative or not
			while(TeamGoals < 0) {
                        System.out.println("Goals Scored cannot be negative. Please enter a non-negative integer.");
                        System.out.println("Goals Scored by the Team:");
			 TeamGoals =sc.nextInt();
                        }
                        
			System.out.println("Goals Scored Against the Team:");
			int GoalsAgainst =sc.nextInt();
			sc.nextLine(); // Consume the newline character
			//check integers are negative or not
			while (GoalsAgainst < 0) {
                        System.out.println("Goals Scored Against cannot be negative. Please enter a non-negative integer.");
                        System.out.println("Goals Scored Against the Team:");
			GoalsAgainst =sc.nextInt();
                        }
                        
			System.out.println("Group");
			String Group =sc.nextLine();
			//check group is include A or B or C or D
			while(!(Group.equals("A") || Group.equals("B") || Group.equals("C") || Group.equals("D"))) {
                        System.out.println("Group is not valid. Please enter one of the predefined values: A, B, C, or D.");
                        System.out.println("Group");
			Group =sc.nextLine();
                        }
                        
                        writeOneRow(filename,TeamName,TeamCode,TeamGoals,GoalsAgainst,Group );
          
		      }
		        //Ask for enter more data
		        System.out.println("Would you like to enter more data (Y or N)? ");
                        String disition = sc.nextLine();
                        if (!disition.equalsIgnoreCase("Y")) {
                        break; // Exit the loop if the user enters anything other than "Y" (case insensitive)
                         }
                         
		  }while (true);
		  //show user to entered all values
		  System.out.println("The current data looks like this:");
		  readfile("data01.csv");
		  //ask user to name the file
		  System.out.println("What would you like to name your csv file");
		  String filename = sc.nextLine();
		   System.out.println("Thank you and have a great day.");
	}
	//to add headers
	private static void writeHeaders(){
	        String filename = "data01.csv";
		FileOutputStream fileStrm = null;
                PrintWriter pw;
                  try
                  {
                     fileStrm = new FileOutputStream(filename,false);
                     pw = new PrintWriter(fileStrm);	 	
                     pw.println("Team Name,Team Code,Goals For,Goals Against,Group");
                     pw.close();					 
                  }
                  catch(IOException e)
                  {			 
                     System.out.println("Error in writing to file: " + e.getMessage());
                  }   
	}
	
	//write all input values to CSV file
	private static void writeOneRow(String pFilename,String pTeamName,String pTeamCode, int pTeamGoals,int    pGoalsAgainst,String pGroup ) 
    {
        FileOutputStream fileStrm = null;
        PrintWriter pw;
        try
        {
            fileStrm = new FileOutputStream(pFilename,true);
            pw = new PrintWriter(fileStrm);	 	
            pw.println(pTeamName + "," + pTeamCode + "," + pTeamGoals + "," + pGoalsAgainst+ "," +pGroup);
            pw.close();					 
        }
        catch(IOException e)
        {			 
            System.out.println("Error in writing to file: " + e.getMessage());
        }   
    }
    
       //Read all data user input to the CSV file
       public static void readfile(String pFileName){
              FileInputStream fileStream =null;
              InputStreamReader rdr;
              BufferedReader buffRdr;
              int lineNum;
              String line;
     try{
          fileStream = new FileInputStream(pFileName);
          rdr        = new InputStreamReader(fileStream );
          buffRdr     = new BufferedReader(rdr);
          lineNum    = 0;
          line       = buffRdr.readLine();
          while(line != null){
              lineNum++;
              System.out.println(line);
              line       = buffRdr.readLine();
          }
               fileStream.close();
          
     }
     catch(IOException errorDetails) {
         if(fileStream != null){
         
         try{
            fileStream.close();
         }
         catch(IOException ex2)
         {}
         
         }
         System.out.println(errorDetails.getMessage());
     
     
     }
     }  
}
