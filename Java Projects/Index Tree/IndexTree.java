import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// This program uses an Index Tree, that I created, to go and sort word locations in 100 pgs of 
// The Sonnets, by William Shakespeare

// No generics needed in this class casue we know what we are getting
public class IndexTree {

	// This is the root.
	// Your root does not use generics because you know your nodes
	// hold strings, an int, and a list of integers
	private IndexNode root;
	
	// Constructor
	public IndexTree() {
		
		this.root = null;
		
	}
	
	// Wrapper method
	// it takes in two pieces of data rather than one and 
	// calls my recursive add method
	public void add(String word, int lineNumber){
		
		this.root = add(this.root, word, lineNumber);
		
	}
	
	
	
	// The recursive method for add
	// When you add the word to the index, if it already exists, 
	// you want to add it to the IndexNode that already exists
	// otherwise make a new indexNode
	private IndexNode add(IndexNode root, String word, int lineNumber) {
		
		if (root == null) {
			
			return new IndexNode(word, lineNumber);
			
		}
		
		int comparison = word.compareTo(root.word);
		
		if (comparison == 0) {

			root.list.add(lineNumber);
			
			root.occurrences++;
			
			return root;
			
		}
		
		if (comparison < 0) {
			
			root.left = add(root.left, word, lineNumber);
			
			return root;
			
		} else {
			
			root.right = add(root.right, word, lineNumber);
			
			return root;
			
		}
		
	}
	
	// Wrapper method
	public boolean contains(String word) {
		
		return contains(this.root, word);
		
	}
	
	
	// returns true if the word is in the index
	public boolean contains(IndexNode root, String word){
		
		if (root == null) {
			
			return false;
			
		}
		
		 int comparison = word.compareTo(root.word);
		 
	        if (comparison == 0) {
	        	
	            return true;
	            
	        } else if (comparison < 0) {
	        	
	            return contains(root.left, word);
	            
	        } else {
	        	
	            return contains(root.right, word);
	        }
	        
	}
	
	// Wrapper method
	public void delete(String word){
		
		this.root = this.delete(this.root, word);
		
	}
	
	// The recursive case
	// remove the word and all the entries for the word
	private IndexNode delete(IndexNode root, String word) {
		
		if (root == null) {
			
			return null;
			
		}
		
		int comparison = word.compareTo(root.word);
			
		if (comparison < 0) {
			
			root.left = delete(root.left, word);
			
			return root;
			
		} else if (comparison > 0) {
			
			root.right = delete(root.right, word);
			
			return root;
			
		} else {
			
			if (root.left == null && root.right == null) {
				
				return null;
				
			} else if (root.left != null && root.right == null) {
				
				return root.left;
				
			} else if (root.left == null && root.right != null) {
				
				return root.right;
				
			} else {
				
				IndexNode current = root.left;
				
				while (current.right != null) {
					
					current = current.right;
					
				}
				
				root.word = current.word;
				
				root.occurrences = current.occurrences;
				
				root.list = current.list;
				
				root.left = delete(root.left, root.word);
				
				return root;
				
			}
			
		}
		
	}
	
	// Wrapper method
	public void printIndex() {
		
		printIndex(this.root);
		
	}
	
	// prints all the words in the index in order 
	// To successfully print it out
	// this should print out each word followed by the number of occurrences and the list of all occurrences
	// each word and its data gets its own line
	public void printIndex(IndexNode root){
		
		if (root == null) {
			
			 System.out.println("");
			 return;
			
		}
		
		printIndex(root.left);
		System.out.println(root.toString());
		printIndex(root.right);
		
	}
	
	public static void main(String[] args){
		
		IndexTree index = new IndexTree();
		
		String fileName = "pg100.txt";
		
		int lineNumber = 0;
		
		try {
			
			Scanner scanner = new Scanner (new File(fileName));
			
			while(scanner.hasNextLine()){
				
				String line = scanner.nextLine();
				
				lineNumber++;
				
				String [] words = line.split("\\s+");
				
				for (String word : words) {
					
					word = word.replaceAll(":", "");
					
					word = word.replaceAll(",", "");
					
					String fixedWord = "";
					
					 for (int i = 0; i < word.length(); i++) {
						
						char c = word.charAt(i);
						
						if ((Character.isAlphabetic(c))) {
							
							fixedWord += c;
							
						}
						
					} 
					
					// Add the word into the tree at this point
					index.add(fixedWord, lineNumber);
			
				}
			}
			
			scanner.close();
			
		} catch (FileNotFoundException e1) {
			
			e1.printStackTrace();
		}
		
		// Removing a word from the index
		index.delete("zone");
		
		index.printIndex();
	}
}