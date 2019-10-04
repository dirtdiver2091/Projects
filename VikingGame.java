//Andrew Pidhajny
//2/27/2019
//Assignment 5. Viking Game
//
//This program will run a Viking game, between a user and the computer  
//
//

import java.util.Scanner;
import java.util.Random;


public class VikingGame {
	
	
	public static void gameLoop(int [] counterArray) {
		//This will keep the game going until user quits.
		daRules();
		String choiceNames[] = { "[1] Svartrå", "[2] Tunholmen", "[3] Äpplaro", "[4] Godafton" }; 

		//make userChoice a keyboard input.
		System.out.println("");
		System.out.println("Do you want to play a round? [y/n]: ");
		Scanner kbd = new Scanner(System.in);
		String userChoice = kbd.next();
		
		if (userChoice.equalsIgnoreCase("y")) {
			do {
				int aiRandom = aiRandom();
				int playerMove = playerMove();
				
				System.out.println("PLAYER CHOSE: "+ choiceNames[playerMove - 1]);
				System.out.println("COMPUTER CHOSE: " + choiceNames[aiRandom - 1]);
				
				chooseWinner(playerMove, aiRandom, counterArray);
				
				userChoice = gameQuit();
			} while (!userChoice.equalsIgnoreCase("n"));
			
		}
		System.out.println("Total rounds played: " + counterArray[0]);
		System.out.println("Player Wins: " + counterArray[1]);
		System.out.println("Computer Wins: " + counterArray[2]);
		
	}
	
	//working
	public static String gameQuit() {
		//Will ask the user if they wish to continue the game.
		//Returns true or false for gameQuit boolean
        System.out.println("Do you wish to continue playing? [y/n]: ");
		Scanner kbd = new Scanner(System.in);
        String gameQuit = kbd.next();
		return gameQuit;
	} 
	
	//start
	public static int aiRandom() {
		//This will make the AI choose a random option.
		Random randomChoice = new Random();
		
		int aiChoice = randomChoice.nextInt(4)+1;
		return aiChoice;
		
	}
	
	public static int playerMove() {
		//This will be a selection menu for the player.
		Scanner kbd = new Scanner(System.in);
		System.out.println("Please choose of the four names using the intger value leading the name; [1] Svartrå, [2] Tunholmen, [3] Äpplaro, or [4] Godafton. : ");
        int userChoice = kbd.nextInt();
        	
        if (userChoice < 1 || userChoice > 4) {
        	do {
        		System.out.println("Incorrect choice please re-enter your move.");
        		System.out.println("Please choose of the four names using the intger value leading the name; [1] Svartrå, [2] Tunholmen, [3] Äpplaro, or [4] Godafton. : ");
        		userChoice = kbd.nextInt();
        	} while ( (userChoice < 1) || (userChoice > 4) );
        }
		return userChoice;
	}
	
	public static void chooseWinner(int player, int ai, int [] array) {
		//Returns the winner of round
		if (player == ai) {
			scoreBoard(2, array);
			System.out.println("Round tied, as per the rules, you lost!");
		}
		
		if (player == 1 && ai == 2) {
			scoreBoard(1, array);
			System.out.println("Congratulations, you won!");
		}
		if (player == 1 && ai == 3) {
			scoreBoard(2, array);
			System.out.println("You lost!");
		}
		if (player == 1 && ai == 4) {
			scoreBoard(2, array);
			System.out.println("You lost!");
		}
		if (player == 2 && ai == 1) {
			scoreBoard(2, array);
			System.out.println("You lost!");
		}
		if (player == 2 && ai == 3) {
			scoreBoard(2, array);
			System.out.println("You lost!");
		}
		if (player == 2 && ai == 4) {
			scoreBoard(1, array);
			System.out.println("Congratulations, you won!");
		}
		if (player == 3 && ai == 1) {
			scoreBoard(1, array);
			System.out.println("Congratulations, you won!");
		}
		if (player == 3 && ai == 2) {
			scoreBoard(1, array);
			System.out.println("Congratulations, you won!");
		}
		if (player == 3 && ai == 4) {
			scoreBoard(2, array);
			System.out.println("You lost!");
		}
		if (player == 4 && ai == 1) {
			scoreBoard(1, array);
			System.out.println("Congratulations, you won!");
		}
		if (player == 4 && ai == 2) {
			scoreBoard(2, array);
			System.out.println("You lost!");
		}
		if (player == 4 && ai == 3) {
			scoreBoard(1, array);
			System.out.println("Congratulations, you won!");
		}
		
	}
	//Pass in 1 if player won and 2 if AI won
	public static void scoreBoard(int winner, int [] array) {
		//This is your win and total count, counter.
		array[winner] = array[winner] +1 ;
		array[0] = array[0] + 1;
		
		
	}
	
	public static void daRules() {
		System.out.println("During each round, players choose a move, which may be either Applaro, Svartrå,Tunholmen, or Godafton. The rules are: ");
		System.out.println("Applaro beats Svartrå and Tunholmen.");
		System.out.println("Svartrå beats Tunholmen.");
		System.out.println("Tunholmen beats Godafton.");
		System.out.println("Godafton beats Applaro and Svartrå.");
		System.out.println("The computer wins in the event of a tie.");
	}
	
	public static void main(String[] args) {
		//0=Total, 1=Player, 2=AI
		int[] count_array = {0, 0, 0}; 
		gameLoop(count_array);
	}
	
	
}
