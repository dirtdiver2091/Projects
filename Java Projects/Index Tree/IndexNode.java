
import java.util.ArrayList;
import java.util.List;

public class IndexNode  {

	// The word for this entry
	String word;
	
	// The number of occurrences for this word
	int occurrences;
	
	// A list of line numbers for this word.
	List<Integer> list; 
	
	
	
	IndexNode left;
	
	IndexNode right;
	
	
	// Constructors
	// Constructor should take in a word and a line number
	// it should initialize the list and set occurrences to 1
	
	public IndexNode (String word, int lineNumber) {
		
		this.word = word;
		// initialize the list here
		this.list = new ArrayList <>();
		list.add(lineNumber);
		this.occurrences = 1;
		
	}
	
	public String toString(){
		
		return "word: " + word + ", occurrences: " + occurrences + ", appears on lines: " + list.toString();
		
	}
	
}