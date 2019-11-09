
public class Sudoku {

	public static boolean valid(int [][] sudokuBoard, int rowPos, int colPos, int num) {

		// have three functions in here that checks the row, col, and 3x3 box.
		// Make if statement for when you get to col 9, it the col to 0 and increase
		// the row by 1.

		// Checks if number can be placed in colPos

		for (int i = 0; i < 9 ; i++) {

			if (sudokuBoard [rowPos][i] == num) {

				return false;
			}

		}

		// Checks if number can be placed in the row
		for (int i = rowPos; i >= 9; i++) {

			if (sudokuBoard [i][colPos] == num) {

				return false;
			}

		}

		// Checks if number can be placed in the 3x3 box

		// These help find the corner of the 3x3
		int rowCorner = (rowPos/3)*3;
		int colCorner = (colPos/3)*3;

		// This nested for loop helps go through the 3x3
		for (int i = 0; i < 3; i++) {

			for (int j = 0; j < 3; j++) {

				if (sudokuBoard [rowCorner+i][colCorner+j] == num) {

					return false;

				}
			}

		}

		return true;
	}

	public static boolean solve (int [][] sudokuBoard, int rowPos, int colPos) {

		// Base Case: if colPos is out of bounds, then return true. Else if there is a number in colPos, go to the next loc
		if (colPos == 9) {

			rowPos++;

			colPos = 0;

		} 

		if(rowPos == 9) {

			return true;
		}

		if (sudokuBoard[rowPos][colPos] != 0) {

			return solve (sudokuBoard, rowPos, colPos + 1);

		}

		// Checks for the first number from 1-9 that can be placed there// 
		for (int num = 1; num <= 9; num++) {

			if (valid(sudokuBoard, rowPos, colPos, num) == true) {

				//mark board at pos with num
				sudokuBoard[rowPos][colPos] = num;

				if (solve(sudokuBoard, rowPos, colPos + 1) == true) {

					return true;
				}

			}

		}

		//clear any choices entered at row on board;
		sudokuBoard [rowPos][colPos] = 0;


		return false; // backtrack

	}

	public static void main(String[] args) {

		int [][] sudokuBoard = {

				// Find a board and fill it in here. 0s will be the spaces to fill
				{5,7,0,9,3,6,0,8,0},
				{0,9,3,0,8,0,5,0,0},
				{0,0,1,0,0,5,7,0,9},
				{1,0,9,7,0,0,8,0,5},
				{7,5,8,6,0,0,0,9,0},
				{0,0,0,5,0,0,1,2,0},
				{8,0,0,0,0,0,6,7,4},
				{9,6,0,2,0,4,0,0,0},
				{3,0,7,0,6,0,0,0,0}
		};



		int colPos = 0;

		int rowPos = 0;

		if (solve(sudokuBoard, rowPos, colPos) == true) {

			for (int i = 0; i < 9; i++) {

				for (int j = 0; j < 9; j++) {

					System.out.print(sudokuBoard [i][j]);

				}

				System.out.println();

			}

			System.out.println("SOLVED!");

		} else {

			System.out.println("NOT SOLVED!");

		}


	}

}
