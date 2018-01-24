/* Author: Jonathan Stone
 * Published under MIT license. 
 * 
 */


import java.util.Scanner;

public class MontyHall {

	private static int numRuns; //number of iterations to run
	
	public static void main(String[] args) throws InterruptedException {
		
		System.out.println("Welcome to the Monty Hall problem simulation." +
			" A random door will be selected for you upon each iteration.\n\n" +
			"How many iterations would you like to perform? ");
		
		Scanner sc = new Scanner(System.in);
		numRuns = sc.nextInt();
		
		System.out.println("\nWould you like to switch doors?\n"
			+ "Type 'y' or 'yes' to switch, or enter any character to remain at selected door.");
		
		String yayNay = sc.next();
		sc.close();
		boolean changeDoors = false; //choice is invariant across all runs once set
		if (yayNay.equalsIgnoreCase("y") | yayNay.equalsIgnoreCase("yes")) {
			changeDoors = true;
		}
		
		Thread.sleep(400); //pauses execution (in milliseconds). Requires "throws InterruptedException" clause
		System.out.print("\nApplying Law of Large Numbers by running " + numRuns + 
				" iterations to converge win ratio to \nexpected value "
				+ "(66% if door switched, 33% if not).\n\n");
		Thread.sleep(1000);
		
		System.out.println("Percentage of wins over " + numRuns + 
                      " runs is " + goLoop(changeDoors) + "%.");
	}
	
	//prints a cute "Working" message...with ellipses!
	private static void printWorking() throws InterruptedException {
		
		System.out.print("Working");
		Thread.sleep(400);
		System.out.print(".");
		Thread.sleep(400);
		System.out.print(".");
		Thread.sleep(400);
		System.out.println(".");
	}
	
	//run the simulation
	private static double goLoop(boolean changeDoors) throws InterruptedException {
		
		printWorking();
		int numWins = 0;	
		long startTime = System.currentTimeMillis();
		double winRatio = 0;
		
		for (int i = 0; i < numRuns; i++) {	
			
			if (i > 0) { //don't blow stuff up by dividing by 0
				winRatio = (double) numWins / i; 
			}
			
			long elapsedTime = System.currentTimeMillis() - startTime;
			
			if (elapsedTime > 3000) {
				printWorking();
				startTime = System.currentTimeMillis(); //reset stopwatch
			}
			
		    int winningDoor = (int) (Math.random() * 3 + 1); //gets random number in range [1,3]
	        int playerChooses = (int) (Math.random() * 3 + 1); //select random door for contestant
	        int hostShows = 0;
	        
	        if (playerChooses == winningDoor) {
	        	double hostRand = Math.random(); //used by host to decide which non-selected door to open
	        	switch (playerChooses) {
	        	case 1: 
	        		if (hostRand < 0.5) { 
	        			hostShows = 2;
	        		} else {
	        			hostShows = 3;
	        		}
	        		break;
	        	case 2: 
	        		if (hostRand < 0.5) { 
	        			hostShows = 1;
		        		} else {
	        			hostShows = 3;
		        		}
	        		break;
	        	case 3: 
	        		if (hostRand < 0.5) {
	        			hostShows = 1;
		        		} else {
	        			hostShows = 2;
		        		}
	        		break;
	        	}
	        } else { //player didnt choose winning door	        	
	        	switch (playerChooses){
	        	case 1:
	        		if (winningDoor == 2) {
	        			hostShows = 3;
	        		} else {
	        			hostShows = 2;
	        		}	
	        		break;
	        	case 2:
	        		if (winningDoor == 1) {
	        			hostShows = 3;
	        		} else {
	        			hostShows = 1;
	        		}	 
	        		break;
	        	case 3:
	        		if (winningDoor == 2) {
	        			hostShows = 1;
	        		} else {
	        			hostShows = 2;
	        		}	 
	        		break;
	        	}	        	
	        }
	        
	        //change doors if required
	        if (changeDoors) {
	        	playerChooses = performDoorChange(playerChooses, hostShows);
	        }
	        
	        if (playerChooses == winningDoor) {
	        	numWins++;
	        }  
		}		
		return winRatio * 100;
	}

	private static int performDoorChange(int playerChooses, int hostShows) {
		switch (playerChooses) {
		case 1:
			if (hostShows == 2) {
				playerChooses = 3;
			} else {
				playerChooses = 2;
			}		
			break;
		case 2:
			if (hostShows == 1) {
				playerChooses = 3;
			} else {
				playerChooses = 1;
			}	
			break;
		case 3:
			if (hostShows == 1) {
				playerChooses = 2;
			} else {
				playerChooses = 1;
			}	
			break;
		}	
		return playerChooses;
	}
}
