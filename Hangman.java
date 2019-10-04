
//Andrew Pidhajny
//04/19/2019
//Assignment 10. Hangman
//
//This program will allow a person to play hangman with the computer, but the computer will cheat
//
//
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Hangman {

	public static int attempts = 0;
	public static int MAX_ATTEMPTS = 6;

	public static void nonCheatPhase(String actualWord) {

		//System.out.print(actualWord);
		
		Scanner input = new Scanner(System.in);

		String y = "";

		while (attempts != MAX_ATTEMPTS) {

			String userGuess = input.next();

			userGuess.toLowerCase();

			// Checks to see if it is a letter in the word
			if (userGuess.length() == 1) {


				if (actualWord.contains(userGuess)) {

					System.out.println("That is a letter, guess again!");

				} else {

					System.out.println("That is not a letter in the word, guess again!");
					attempts++;

				}
			}

			// Checks to see if it is a word in the list
			if (userGuess.length() > 1) {

				if (actualWord.equals(userGuess)) {

					String answer = "You chose, " + actualWord + ", that is correct!"; 
					
					System.out.println(answer);

				} else if (!(actualWord.equals(userGuess))) {

					System.out.println("That is not the word, guess again!");
					attempts++;

				}
			}
		}
		
		if (attempts == MAX_ATTEMPTS) {
			
			System.out.println("No more guesses :(");
			
		}
	}

	public static String cheatPhase(ArrayList<String> Words) {

		String s = "";

		Scanner input = new Scanner(System.in);
		
		System.out.println("Would you like to start by guessing a word or a letter? (w/l)" + "\n");
		
		String x = input.next();
		
		while ((x.equalsIgnoreCase("l") || x.equalsIgnoreCase("w")) && attempts < MAX_ATTEMPTS){
			
		//These are for letters
		if(x.equalsIgnoreCase("l")) {
			
			System.out.println("Enter Letter: ");
				
			Words = removeByLetter(Words, input.next());
			
			//Words.toString();
			//System.out.println(Words);
			
			if(Words.size() == 1) {
				
				return Words.get(0);
				
			}
			
			System.out.println("That is not a letter in the word!");
			attempts++;
			
			//System.out.println(attempts);
			
		}
			
		//These are for words
		else if(x.equalsIgnoreCase("w")) {
			
			System.out.println("Enter Word: ");
			
			Words = removeByWord(Words, input.next());
			
			if(Words.size() == 1) {
				
				return Words.get(0);
				
				}
			
			System.out.println("That is not the word!");
			attempts++;
			
			}
		
		
		}
		
		return s;
		
	}

	// Works
	public static ArrayList<String> removeByLength(ArrayList<String> Words, int randLength) {

		for (int j = 0; j < Words.size(); j++) {
			for (int i = 0; i < Words.size(); i++) {

				if (Words.get(i).length() != randLength) {

					Words.remove(i);

				}
			}
		}

		return Words;
	}
	
	//Does not work 
	public static ArrayList<String> removeByLetter(ArrayList<String> Words, String letter) {
		
		ArrayList<String> A = new ArrayList<String>();
		
			for (int i = 0; i < Words.size(); i++) {

				if (!(Words.get(i).contains(letter))) {
					
					A.add(Words.get(i));

				}
			}
		
		
		if (A.size() == 0){
			
			Random rand = new Random();
			
			int y = rand.nextInt(Words.size() - 1);
			
			A.add(Words.get(y));
			
			return A;
			
			
		}

		return A;
	}
	
	//Works
	public static ArrayList<String> removeByWord(ArrayList<String> Words, String word) {
		
		ArrayList<String> A = new ArrayList<String>();
		
		for (int i = 0; i < Words.size(); i++) {

				if (Words.get(i).equals(word)) {

					Words.remove(i);

				}
			
		}
		
			if (Words.size() == 0){
			
			Random rand = new Random();
			
			int y = rand.nextInt(A.size() - 1);
			
			Words.add(A.get(y));
			
			return Words;
			
			
		}
			
		return Words;
	}

	public static void main(String arg[]) {

		try {

			Scanner list = new Scanner(new File("word_list.txt"));

			Scanner in = new Scanner(System.in);

			ArrayList<String> Words = new ArrayList<String>();
			
			String outcome = "";

			System.out.println("Do you want to play a game? [y/n]: ");

			String userChoice = in.next();
			

			if (userChoice.equalsIgnoreCase("y")) {


					while (list.hasNext()) {

						String word = list.next();

						Words.add(word);

					}

					list.close();
					
					Random rand = new Random();
					
					//Found random number for a length
					int randLength = rand.nextInt(14-3)+3;
					
					//System.out.println(randLength);
					
					//Edits length of array to only have words the size of the random length
					Words = removeByLength(Words, randLength);
					
					//Words.toString();
					//System.out.println(Words);
					
					System.out.println("I have chosen a word with " + randLength
							+ " letters, you have 6 guesses so guess wisely.");
					
					//keeps cheating until there is one word left, then it returns that word
					String actualWord = cheatPhase(Words);
					
					if (actualWord.equals("")) {
						
						System.out.println("You Lost");
						
						//quit here
					}
					
					//actual word is passed into non-cheat phase and hangman is played like normal 
					nonCheatPhase(actualWord);


			}

			else if (userChoice.equalsIgnoreCase("n")) {

				System.out.println("You are no fun.");

			} else {

				System.out.println("That is not the right character...");

			}
		}

		catch (FileNotFoundException e) {

			System.out.println("File of words to be used in Hangman cannot be read.");

		}
	}
}
