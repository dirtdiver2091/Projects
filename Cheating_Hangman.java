import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.util.*;


public class Cheating_Hangman {
	
	// Made global to help the recursive call in wordLengthCheck
	static int lengthChosen = 0;
	
	// This method isnt needed 
	/* public static boolean letterCheck(Set <Character> guessedLetters, char letterGuess) {
		
		if (guessedLetters.contains(letterGuess)) {
			
			return false;
			
			
			
		} else {
			
			return true;
			
		}
		
	} */
	
	public static String finalWordFound (String revealedLetters, Map <String, List <String>> wordFamilies) {
		
		List <String> wordLists = new ArrayList <>();
		
		String wordFound = "";
		
		wordLists = wordFamilies.get(revealedLetters);
		
		Random rand = new Random();
		
	    int i = rand.nextInt(wordLists.size());
	    
	    wordFound = wordLists.get(i);
	    
	    wordFound.toLowerCase();
		
		
		return wordFound;
		
	}
	
	public static boolean wordFound (String revealedLetters) {
		
		String hiddenLetter = "_";
		
		for (int i = 0; i < revealedLetters.length(); i++) {
			
			if (revealedLetters.charAt(i) == hiddenLetter.charAt(0)) {
				
				//System.out.println("HIT");
				
				return false;
				
			}
			
		}
		
		return false;
		
	}
	
	public static Map <String, List <String>> getWordFamilies (List <String> wordLists, Set <Character> guessedLetters) {
		
		Map <String, List <String>> wordFamilies = new HashMap <>();
		
		for (int i = 0; i < wordLists.size(); i++) {
			
			String word = wordLists.get(i);
			
			// Family is the key for the specific wordFamily created here
			String family = "";
			
			for (char c : word.toLowerCase().toCharArray()) {
				
				if (guessedLetters.contains(c)) {
					
					family += c;
					family += " ";
					
				} else {
					
					family += "_";
					family += " ";
				}
			}
			
			if (!wordFamilies.containsKey(family)) {
				
				//create a new list and add the key to the list
				List <String> newWordList = new ArrayList <>();
				newWordList.add(word);
				wordFamilies.put(family, newWordList);
				
			} else {
				
				//if the key already exists then take the word and add it to the list
				wordLists = wordFamilies.get(family);
				
				wordLists.add(word);
				
			}
			
		}
		
		return wordFamilies;
		
	}
	
	// This method finds the largest family of words to choose from 
	public static String getBestFamily (Map <String, List <String>> wordFamilies) {
		
		List <String> largestWordList = new ArrayList <>();
		
		// This string is essentially the hidden word
		String largestWordFamilyKey = "";
		
		// for (each family, find the longest one and add it to the largest family variable, then return it)
		// String family is the key here
		for (String family: wordFamilies.keySet()) {
			
			largestWordList = wordFamilies.get(family);
			
			if (wordFamilies.get(family).size() >= largestWordList.size()) {
				
				// update wordLists to the largest array 
				
				largestWordList = wordFamilies.get(family);
				
				largestWordFamilyKey = family;
				
			}
			
		}
		
		return largestWordFamilyKey;
		
	}
	
	public static ArrayList <String> wordLengthCheck (List <String> wordLists) {
		
		Scanner input = new Scanner(System.in);
		
		ArrayList <String> newHiddenWords = new ArrayList<> ();
		
		int newhiddenWordSize = 0;
		
		for (int i = 0; i < wordLists.size(); i++) {
			
			String word = wordLists.get(i);
		
			if (word.length() == lengthChosen) {
				
				newHiddenWords.add(word);
				
				newhiddenWordSize++;
				
			} 
			
		}
		
		if (newhiddenWordSize <= 0) {
			
			System.out.println("There isn't a word of that length, choose another length: ");
			
			lengthChosen = input.nextInt();
			
			wordLengthCheck(wordLists);
			
			
		}
		
		return newHiddenWords;
		
	}
	
	

	public static void main(String [] args) {
		
		Scanner input = new Scanner (System.in);
		
		Map <String, List <String>> wordFamilies = new HashMap <>();
		
		int guessAmount = 0;
		
		int attempts = 0;
		
		int attemptsLeft = 0;
		
		String revealedLetters = "";
		
		String wrongGuesses = "";
		
		System.out.println("Do you wanna to play a game? [y/n]: ");
		
		String userResponse = input.next();
		
		userResponse.toLowerCase();
		
		while (userResponse.equals("y")) {
			
			// This ArrayList is comprised of the words with the length chosen
			List <String> wordsList = new ArrayList <> ();
			
			//key is length of the word and key value is the ArrayList of all the strings that go to that word
			Map <Integer, List> dictionary = new HashMap <> ();
			
			String fileName = "words.txt";
			
			try {
				
				Scanner fileScanner = new Scanner (new File(fileName));
				
				while (fileScanner.hasNextLine()) {
					
					String line = fileScanner.nextLine();
					
					//line = line.toLowerCase();
					
					wordsList.add(line);
					
					//dictionary.put(lengthChosen, hiddenWords);
					
					//System.out.println(line);
					
					
				}
				
				fileScanner.close();
				
			} catch (FileNotFoundException e1) {
				
				e1.printStackTrace();
				
			}
			
			System.out.println("Ok great! Choose the length of the word you will guess:");
			
			lengthChosen = input.nextInt();
			
			wordsList = wordLengthCheck(wordsList);
			
			dictionary.put(lengthChosen, wordsList);
			
			System.out.println("Wonderful! Now, choose how many guesses you would like:");
			
			guessAmount = input.nextInt();
			
			attemptsLeft = guessAmount;
			
			// print a line saying a word has been chosen, with _ _ _ _ ... as the spacing for a word of the length chosen
			// need to make sure that when they guess a letter, that it prints out where that letter is in those spaces, according
			// to the words in the longest word family
			
			// guess letters out here
			Set <Character> guessedLetters = new HashSet <>();
			
			// While loop to keep game going
			while (attempts != guessAmount && wordFound(revealedLetters) == false) {
				
				System.out.println("Correct letters: " + revealedLetters + ", Wrong guessed: " + wrongGuesses + ", Attempts left: " + attemptsLeft);
				
				// guess letters are used to find the families in here
				System.out.println("Guess a letter: ");
				
				char letterGuess = input.next().charAt(0);
				
				Character.toLowerCase(letterGuess);
				
				if (!(guessedLetters.contains(letterGuess))) {
					
					guessedLetters.add(letterGuess);
					
				} else {
					
					while (guessedLetters.contains(letterGuess)) {
						
						System.out.println("You have already guessed that letter, choose another letter: ");
						
						letterGuess = input.next().charAt(0);
						
					}
					
					guessedLetters.add(letterGuess);
					
				}
				
				wordFamilies = getWordFamilies(wordsList, guessedLetters);
				
				revealedLetters = getBestFamily(wordFamilies);
				
				System.out.println(revealedLetters);
				
				wordsList = wordFamilies.get(revealedLetters);
				
				boolean isRevealed = false;
				
				for (int i = 0; i < revealedLetters.length(); i++) {
					
					if (revealedLetters.charAt(i) == Character.toLowerCase(letterGuess)) {
						
						System.out.println("That letter does exist in the word: " + revealedLetters);
						
						isRevealed = true;
						
						break;
						
					} 
					
				}
				
				if (isRevealed == false) {
					
					System.out.println("Sorry that letter does not exist in the word!");
					
					wrongGuesses += letterGuess;
					
//					attempts++;
//					
//					attemptsLeft = guessAmount - attempts;
					
				}
				
				
				attempts++;
				
				attemptsLeft = guessAmount - attempts;
				
			}
			
			if (attempts == guessAmount) {
				
				String randomWord = finalWordFound(revealedLetters, wordFamilies);
				
				System.out.println("Sorry you lose! The word was: " + randomWord);
				
				System.out.println("Do you want to play again? [y/n]: ");
				
				userResponse = input.next();
				
			} else {
				
				System.out.println("Congrats, you won!");
				
				System.out.println("Do you want to play again? [y/n]: ");
				
				userResponse = input.next();
				
			}
			
			
			
		} 
		
		if (userResponse.equals("n")) {
			
			System.out.println("boring");
			
		} else {
			
			System.out.println("That is not the right input");
			
		}

	}

}

	// how the program should be laid out
	/* Map <integer, List <String>> wordLists;

	while (game is running) {
	
	char guess = input();
	add guess to guessed letters;
	Map <String, List <String>> wordFamilies = getWordFamilies(wordsList, guessedLetters);
	String bestFamily = getBestFamily();
	update the game;
	replace the wordList;
	
} */

// PROBLEMS:
 
// correct letters are being added to wrong guessed
// print statemetns arent working right
// wordFound method does work as it should but it does work. when it hits the return in the check, it gets stuck in a loop

//QUESTIONS:

//   Should i create the ArrayList from a List or ArrayList? doesnt matter
//   Do i have to ask them if they want to guess a word or a letter? no just letters
// How do I do the While loop for while the game is still going?
