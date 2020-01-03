import java.util.*;

// This program will Encrypt and Decrypt messages using a shortened deck of Cards and a 
// Circular Linked List that I created

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
	
	public static CircularLinkedList <Integer> jokerASwap (CircularLinkedList <Integer> deckOfCards, int jokerA) {
		
		int x = 0;
		
		for (int i = 0; i < deckOfCards.size(); i++) {
			
			if (deckOfCards.get(i) == jokerA) {
				
				// What's in location i + 1 is still there
				x = deckOfCards.get(i + 1);
			
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
		
		return deckOfCards;
		
	}
	
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
			}
			
		}
		
		input.close();
		
	}

}
