
// This program solves the famous coding problem, The Eight Queens Problem

public class The_Eight_Queens_Problem {
	
	public static boolean valid(int [][] chessBoard, int row, int colPos) {
		
		
		// Checks rows
		for (int i = colPos; i >= 0 ; i--) {
			
			// checks if the location to the left of the queen, 1, has another queen there
			if (chessBoard [row][i] == 1) {
				
				return false;
			}
			
		}
		
		// Checks to the upper left diagonal
		for (int i = 0; (colPos - i >= 0) && (row - i >= 0); i ++){
			
			if (chessBoard [row - i][colPos - i] == 1) {
				
				return false;
			}
			
		}
		
		// Checks to the lower left diagonal
		for (int i = 0; (colPos - i >= 0) && (row + i < 8); i++ ) {
			
			if (chessBoard [row + i][colPos - i] == 1) {
				
				
				return false;
			}
			
		}
		
		
		return true; 
		
	}
	
	public static boolean solve (int [][] chessBoard, int colPos) {
		
		
		// If we try and place a queen in a column 
		// that doesn't exist, then return true
		if(colPos == 8) {
			
		return true;
		
		}
		
		// For each possible choice, choice is the row
		for (int row = 0; row < 8; row++) {
			
			if (valid(chessBoard, row, colPos) == true) {
				
				   //Mark board at colPos with rowChoice
					chessBoard [row][colPos] = 1;
		
					if (solve(chessBoard, colPos + 1) == true) {
						
							return true;
					}
					
					// replace the 1 with a 0
					//clear any choices entered at colPos on board;
					chessBoard [row][colPos] = 0;
				}
		}
		
		return false; // backtrack
		
	}
	
	// Finds the factorial of a number, currently 5!
	public static long fact(int n) {
		
		if (n == 0 || n == 1) {
			
			return 1;
			
		} else {
			
			return n * fact(n - 1);
			
		}
		
	}

	public static void main (String [] args) {
		
		// row x column 
		int [][] chessBoard = new int [8][8]; 
		
		int colPos = 0;
		
		if (solve(chessBoard, colPos) == true) {
			
			for (int i = 0; i < 8; i++) {
				
				for (int j = 0; j < 8; j++) {
					
					System.out.print(chessBoard [i][j]);
					
				}
				
				System.out.println();
				
			}
			
			System.out.println("SOLVED!");
			
		} else {
			
			System.out.println("NOT SOLVED!");
			
		}
		

	}

}