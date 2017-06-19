/* Author: Jon, aka elzuzax
 * Published under MIT license. 
 * 
 */


import java.util.Scanner;

public class MontyHall {

	public static int numRuns;
	
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
		boolean changeDoors = false;
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
	public static void printWorking() throws InterruptedException {
		
		System.out.print("Working");
		Thread.sleep(400);
		System.out.print(".");
		Thread.sleep(400);
		System.out.print(".");
		Thread.sleep(400);
		System.out.println(".");
	}
	
	//run the simulation
	public static double goLoop(boolean changeDoors) throws InterruptedException {
		
		printWorking();
		int numWins = 0;	
		long startTime = System.currentTimeMillis();
		double winRatio = 0;
		
		for (int i = 0; i < numRuns; i++) {	
			if (i > 0) { //don't blow stuff up by dividing by zero
				winRatio = (double) numWins / i; // set break point here and in debugger, note convergence to either 1/3 or 2/3
			}
		
			long elapsedTime = System.currentTimeMillis() - startTime;
			
			if (elapsedTime > 3000) {
				printWorking();
				startTime = System.currentTimeMillis(); //reset stopwatch
			}
			
		    int winningDoor = (int) (Math.random() * 3 + 1); //gets random number in range [1,3]
	        int iChoose = (int) (Math.random() * 3 + 1); //select random door for contestant
	        int hostShows = 0;
	        
	        if (iChoose == winningDoor) {
	        	if (iChoose == 1) {
	        		if (Math.random() < 0.5) { //50% chance that host shows either door (which contains junk)
	        			hostShows = 2;
	        		} else {
	        			hostShows = 3;
	        		}
	        	}
	        	if (iChoose == 2) {
	        		if (Math.random() < 0.5) {
	        			hostShows = 1;
	        		} else {
	        			hostShows = 3;
	        		}
	        	}
	        	if (iChoose == 3) {
	        		if (Math.random() < 0.5) {
	        			hostShows = 1;
	        		} else {
	        			hostShows = 2;
	        		}
	        	}
	        } else { //player didnt choose winning door
	        	if (iChoose == 1 && winningDoor == 2) {
	        		hostShows = 3;
	        	}
	        	if (iChoose == 1 && winningDoor == 3) {
	        		hostShows = 2; 	        		
	        	}
	        	if (iChoose == 2 && winningDoor == 1) {
	        		hostShows = 3;
	        	}
	        	if (iChoose == 2 && winningDoor == 3) {
	        		hostShows = 1; 	        		
	        	}
	        	if (iChoose == 3 && winningDoor == 1) {
	        		hostShows = 2;
	        	}
	        	if (iChoose == 3 && winningDoor == 2) {
	        		hostShows = 1; 	        		
	        	}	        	
	        }
	        
	        //change doors if required
	        if (changeDoors) {
	        	if (iChoose == 1 && hostShows == 2) {
	        		iChoose = 3;
	        	}
	        	else if (iChoose == 1 && hostShows == 3) {
	        		iChoose = 2;
	        	}
	        	else if (iChoose == 2 && hostShows == 1) {
	        		iChoose = 3;
	        	}
	        	else if (iChoose == 2 && hostShows == 3) {
	        		iChoose = 1;
	        	}
	        	else if (iChoose == 3 && hostShows == 1) {
	        		iChoose = 2;
	        	}
	        	else if (iChoose == 3 && hostShows == 2) {
	        		iChoose = 1;
	        	}
	        }
	        
	        if (iChoose == winningDoor) {
	        	numWins++;
	        }  
		}
		
		return winRatio * 100;
	}
}
