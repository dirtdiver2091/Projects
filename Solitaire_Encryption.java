
import java.util.*;

public class Solitaire_Encryption {
	
	public static char encryptChar(char c, int key) {
		
		int alphaValue = c - 'a';
		
		int encryptedAlphaValue = (alphaValue + key) % 26;
		
		
		return (char) (encryptedAlphaValue + 'a');
	}
	
	public static char decryptChar (char c, int key) {
		
		int alphaValue = c - 'a';
		
		int decryptedAlphaValue = (alphaValue + (26 - key)) % 26;
		
		return (char) (decryptedAlphaValue + 'a');
	}
	
	// USE SET AND GET TO SWAP THE VALUES FOR STEP 1 AND 2, SO FIX THESE TWO AND IT SHOULD BE GOOD
	// changed the 27s to jokerA, so see if that works
	public static CircularLinkedList <Integer> jokerASwap (CircularLinkedList <Integer> deckOfCards, int jokerA) {
		
		int x = 0;
		
		for (int i = 0; i < deckOfCards.size(); i++) {
			
			if (deckOfCards.get(i) == jokerA) {
				
				// What's in location i + 1 is still there
				x = deckOfCards.get(i + 1);
				
				// x is 2
				//System.out.println(x);
				
				// i is 18
				//System.out.println(i);
				
				// i is 19
				//System.out.println(i + 1);
				
				//System.out.println(deckOfCards);
			
				deckOfCards.set(i, x);
			
				deckOfCards.set(i + 1, jokerA);
				
				break;
				
			} else if (deckOfCards.get(i + 1) == null) {
				
				x = deckOfCards.get(0);
				
				deckOfCards.set(i, x);
				
				deckOfCards.set(0, jokerA);
				
				break;
				
			}

			
		}
		
		//System.out.println(deckOfCards);
		
		return deckOfCards;
		
	}
	
	// Issue with whole method and possibly jokerASwap method too
	// coppied jokerASwap to here so change it up to suit jokerB
	public static CircularLinkedList <Integer> jokerBMove (CircularLinkedList <Integer> deckOfCards, int jokerB) {
		
		int x = 0;
		
		for (int i = 0; i < deckOfCards.size(); i++) {
			
			if (deckOfCards.get(i) == 28) {
				
				if (deckOfCards.get(i + 1) == null) {
					
					deckOfCards.add(1, 28);
					
					break;
					
				} else if (deckOfCards.get(i + 2) == null) {
					
					deckOfCards.add(2, 28);
					
					break;
					
				} else {
					
					deckOfCards.add(i + 3, 28);
					
					deckOfCards.remove(i);
					
					break;
					
				}
			} 

			
		}
		
		//System.out.println(deckOfCards);
		
		return deckOfCards;
		
	}
	
	public static CircularLinkedList <Integer> tripleCut (CircularLinkedList <Integer> deckOfCards) {
		
		CircularLinkedList <Integer> topDeckOfCards = new CircularLinkedList <Integer> ();
		
		CircularLinkedList <Integer> bottomDeckOfCards = new CircularLinkedList <Integer> ();
		
		CircularLinkedList <Integer> middleDeckOfCards = new CircularLinkedList <Integer> ();
		
		// Pulling numbers from top deck and putting in top deck Circular linked list
		while(deckOfCards.get(0) <27) {
			
			topDeckOfCards.add(deckOfCards.remove(0));
			
		}
		
		
		middleDeckOfCards.add(deckOfCards.remove(0));
		
		while(deckOfCards.get(0) <27) {
			
			middleDeckOfCards.add(deckOfCards.remove(0));
			
		}
		middleDeckOfCards.add(deckOfCards.remove(0));
		
		// For bottom deck, do, while its not empty or null...
		while (deckOfCards.size() > 0){
			
			int num = deckOfCards.remove(0);
			
			bottomDeckOfCards.add(num);
			
			
		}
		
		// Adding the bottom deck to the top of the deck
		while (bottomDeckOfCards.size() > 0) {
			
			deckOfCards.add(bottomDeckOfCards.remove(0));
			
		}
		
		while (middleDeckOfCards.size() > 0) {
			
			deckOfCards.add(middleDeckOfCards.remove(0));
			
		}
		
		while (topDeckOfCards.size() > 0) {
			
			deckOfCards.add(topDeckOfCards.remove(0));
			
		}
		
		return deckOfCards;
	}
	
	public static CircularLinkedList <Integer> bottomCard (CircularLinkedList <Integer> deckOfCards, int jokerA, int jokerB) {
		
		// Find the last number like i did in step 3, remove it, then do a for loop of the amount of times
		// of the number removed, add those numbers to the end then add the last number back into the end.
		
		
		// At the end of this method, Starting number should be 25 but it is 28. Also the cards were not added to the back of the deck in front of 26, 26 is actually missing.
		//System.out.println(deckOfCards);
		
		int bottomIndex = deckOfCards.get(deckOfCards.size() - 1);
		
		int bottom = deckOfCards.remove(deckOfCards.size() - 1);
		
		int joker = 0;
		
		if (bottom == jokerB) {
			
			joker = -1;
			
		}
		
		for (int i = 0; i < bottomIndex + joker; i++) {
			
			deckOfCards.add(deckOfCards.remove(i));
			
			i--;
			bottomIndex--;
			
		}
		
		deckOfCards.add(bottom);
		
		return deckOfCards;
		
	}
	
	public static int findKey (CircularLinkedList <Integer> deckOfCards) {
		
		//System.out.println(deckOfCards);
		
		
		// Top seems to always be 28??? This step said that the number would be from 1-27, which is
		// carried over from the last step so is the last step wrong???
		
		
		int top = deckOfCards.get(0);
		
		while (top == 27 && top == 28) {
			
			generateKey(deckOfCards);
			
		}
		
		return deckOfCards.get(top);
			
		}
	
	public static int generateKey (CircularLinkedList <Integer> deckOfCards) {
	
		int jokerA = 27;
		
		int jokerB = 28;
		
		int key = 0;
		
		// RUNS TOO MANY TIMES BECAUSE OF THIS WHILE LOOP AND DOESNT PRINT OUT THE ENCRYPTED LETTER.
			
			// Step 1
			deckOfCards = jokerASwap(deckOfCards, jokerA);
			System.out.println(deckOfCards);
			
			// Step 2
			deckOfCards = jokerBMove(deckOfCards, jokerB);
			System.out.println(deckOfCards);
			// Step 3
			deckOfCards = tripleCut(deckOfCards);
			System.out.println(deckOfCards);
			// Step 4
			deckOfCards = bottomCard(deckOfCards, jokerA, jokerB);
			System.out.println(deckOfCards);
			System.out.println();
			// Step 5
			key = findKey(deckOfCards);
			
			// the first key should be 11 but it is 8
			System.out.println(key);
		
		return key;
	}
	
	public static String letterCheck (char c) {
		
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
		String letter = "";
		
		for (int i = 0; i < alphabet.length(); i++) {
			
			if (alphabet.charAt(i) == c) {
				
				letter += c;
				
			}
			
		}
			
		return letter;
	}
	
	public static String onlyLetters (String encryptedMessage) {
		
		for (int i = 0; i < encryptedMessage.length(); i++) {
			
			// This line helps remove all of the characters that aren't letters in the message. 
			encryptedMessage = letterCheck(encryptedMessage.charAt(i));
			
		}
		
		return encryptedMessage;
	}
	
	public static void main (String args []) {
		
		CircularLinkedList <Integer> deckOfCards = new CircularLinkedList <Integer> ();
		
		int [] numbers = {1, 4, 7, 10, 13, 16, 19, 22, 25, 28, 3, 6, 9, 12, 15, 18, 21, 24, 27, 2, 5, 8, 11, 14, 17, 20, 23, 26} ;
		
		for (int i = 0; i < numbers.length; i++) {
			
			deckOfCards.add(numbers[i]);
			
		}
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Do you want to encrypt or decrypt?");
		
		String decision = input.nextLine();
		
		decision = decision.toLowerCase();
		
		if (!decision.equals("encrypt") && !decision.equals("decrypt") ) {
			
			System.out.println("That is not possible");
			
			
			//
		} else if (decision.equals("encrypt")) {
			
			System.out.print("Enter the message you want to encrypt: ");
			
			String message = input.nextLine();
			
			message = message.toLowerCase();
			
			
			String encryptedMessage = "";
			
			for (char c : message.toCharArray()) {
				
				if(Character.isAlphabetic(c)){
					
					int key = generateKey(deckOfCards);
					
					encryptedMessage += encryptChar(c,key);
					
					System.out.println(encryptedMessage);
					
				} else {
					
					encryptedMessage += c;
					
				}
			}
			
			// DECRYPTING
		} else if (decision.equals("decrypt")) {
			
			System.out.print("Enter the message you want to decrypt: ");
			
			String encryptedMessage = input.nextLine();
			
			encryptedMessage = encryptedMessage.toLowerCase();
			
			// reset the deck of cards
			String plainText = "";
			
			for (char c : encryptedMessage.toCharArray()) {
				
				if(Character.isAlphabetic(c)){
					
					int key = generateKey(deckOfCards);
					
					plainText += decryptChar(c,key);
					
					System.out.println(plainText);
					
				} else {
					
					plainText += c;
					
				}
				
				CircularLinkedList <Integer> deckReset = new CircularLinkedList <Integer> ();
				
				for (int i = 0; i < numbers.length; i++) {
					
					deckReset.add(numbers[i]);
					
				}
				
				//deckOfCards = deckReset;
			}
			
		}
		
		input.close();
		
		// snisy
		
	}

}
