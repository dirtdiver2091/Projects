//Andrew Pidhajny
//03/15/2019
//Assignment 6. Southie_Styles
//
//This program take the script of Jaws, change the words into a Boston accent, and then place the new script into a new file
//
//

import java.util.*;
import java.io.*;
import java.io.FileNotFoundException;

public class Southie_Styles {
	
	public static String Very_to_Wicked(String word) {
		
		word.toLowerCase();
		
		if (word.equals("very")) {
		
		return "wicked";
		}
		else { return word;}
	}
	
	public static String Letter_Change2 (String word) {
		
		word.toLowerCase();
		
			if ((word.charAt(word.length()-1)) == 'a') {
				
				return word += 'r';
			}
			
			return word;
		}
	
		
	
	public static String r_Exceptions(String New_Word) {
		
		String x = New_Word;
		New_Word.toLowerCase();
		String Change = "yah";
		String Change2 = "wah";
		
		if (New_Word.length() < 3) {
			return New_Word + " ";
			
		}
		
		if (New_Word.charAt(New_Word.length()-1) == 'r' && New_Word.charAt(New_Word.length()-2) == 'e' && New_Word.charAt(New_Word.length()-3) == 'e') {
			
			x = New_Word.substring(0,New_Word.length()-1);
			x += Change;
			
		}else if (New_Word.charAt(New_Word.length()-1) == 'r' && New_Word.charAt(New_Word.length()-2) == 'i') {
			
			x = New_Word.substring(0,New_Word.length()-1);
			x += Change;
			
		}
		else {
			
			if (New_Word.charAt(New_Word.length()-1) == 'r' && New_Word.charAt(New_Word.length()-2) == 'o' && New_Word.charAt(New_Word.length()-3) == 'o') {
				
				x = New_Word.substring(0,New_Word.length()-1);
				x += Change2;
				
			}
		}
		x += " ";
		return x;
	}
	
	public static boolean Vowel_Comparison(char i) {
        
		String Vowel = "aeiouAEIOU";
    	
    	for (int k = 0; k < Vowel.length(); k++) {
    		
    		if(Vowel.charAt(k) == Character.toLowerCase(i)) {
    			return true;
    		}
   	
    	}
    	return false;
    		
    }
	
	//If there is a vowel before the letter r, then this will change the letter r to the letter h
	public static String Letter_Change(String word) {
		
		word.toLowerCase();
	
		for (int i = 0; i < word.length()-1; i++) {
			if (((Vowel_Comparison(word.charAt(i))) && (word.charAt(i+1) == 'r'))) {
					word = word.substring(0,i+1) + 'h' + word.substring(i+2);
					} 
				}
		
		return word;
		}

	public static void main(String args[]) throws FileNotFoundException {
		
			Scanner Script = new Scanner(new File("jaws.txt")); 
			PrintStream out = new PrintStream(new File("jaws"));
			
		    String Word = "";
		    String New_Word = "";
		    
		    //PrintStream out = new PrintStream(new File("jaws_script.txt"));

		    while(Script.hasNext()) {
		    	Word = Script.next();
		    	if (Word.length() <= 1) {
		    		New_Word += Word;
		    		New_Word += " ";
		    	}
		    	if ((Word.charAt(Word.length()-1)) == 'a') {
		    		New_Word = Letter_Change2(Word);
		    		out.print(New_Word + " ");
		    	}
		    	else {
		    		New_Word = ((Letter_Change(r_Exceptions((Very_to_Wicked(Word))))));
			    	out.print(New_Word + " ");
		    	}
		    }
		    
		    Script.close();
	    
	    }
	}
