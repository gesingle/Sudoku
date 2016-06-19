package csc143.sudoku;

/** 
 * Class implements abstract methods from SudokuBase
 * 
 * @author GSingletary
 * @version Assignment 3: Sudoku Base - Standard
 */
public class SudokuBoard extends SudokuBase {

	SudokuView myview;
	
	/**
	 * Constructor
	 * 
	 * @param r Number of rows
	 * @param c Number of columns
	 * @param o Setup mode indicator
	 */
	public SudokuBoard(int r, int c, boolean o) {
		super(r, c, o);
	}

	/**
	 * Determines the state of a specified row
	 * 
	 * @param n The row
	 * @return The state of the row
	 */
	public State getRowState(int n) {

		int counter = 0;
		int zcounter = 0;

		for (int i = 1; i <= size; i++) {
			for (int j = 0; j < size; j++) {
				if (getValue(n, j) == i) {
					counter++;
				} else if (getValue(n, j) == 0) {
					zcounter++;
				}
			}
			if (counter > 1) {
				return State.ERROR;
			}
			counter = 0;
		}

		if (zcounter > 0) {
			return State.INCOMPLETE;
		}

		return State.COMPLETE;

	}

	/**
	 * Determines the state of a specified column
	 * 
	 * @param n The column
	 * @return The state of the column
	 */
	public State getColumnState(int n) {

		int counter = 0;
		int zcounter = 0;

		for (int i = 1; i <= size; i++) {
			for (int j = 0; j < size; j++) {
				if (getValue(j, n) == i) {
					counter++;
				} else if (getValue(j, n) == 0) {
					zcounter++;
				}
			}
			if (counter > 1) {
				return State.ERROR;
			}
			counter = 0;
		}

		if (zcounter > 0) {
			return State.INCOMPLETE;
		}

		return State.COMPLETE;

	}

	/** 
	 * Determines the state of a specified region
	 * 
	 * @param n The region
	 * @return The state of the region
	 */
	public State getRegionState(int n) {
        
		// variables to count digit occurances and blank cells
		int counter = 0;
		int zcounter = 0;
		// variables for starting row and starting column for iteration
		int startingcol = 0;
		int startingrow = 0;
		// secondary counter
		int temp = 0;
		
		// set starting row
		for (int a = 0; a < size; a += rows) {
			if (n < a + rows) {
				startingrow = a;
				break;
			}
		}
		
		// set starting column
		for(int c = 0; c < columns; c++) {
			for(int d = 0; d < size; d += columns) {
				if(n == temp) {
					startingcol = d;
				}
				temp++;
			}
		}

		// iterate through all possible values
		for (int i = 1; i <= size; i++) {
			// iterate through every row in each region
			for (int j = startingrow; j < startingrow + rows; j++) {
				// iterate through every value within that row
				for (int k = startingcol; k < startingcol + columns; k++) {
					// count every occurance of value i
					if (getValue(j, k) == i) {
						counter++;
					} 
					// count occurances of zero
					else if (getValue(j, k) == 0) {
						zcounter++;
					}
				}
			}
			// return ERROR if there are duplicates
			if (counter > 1) {
				return State.ERROR;
			}
			// reset counter
			counter = 0;
		}
		// return INCOMPLETE if there are blank cells but no errors
		if (zcounter > 0) {
			return State.INCOMPLETE;
		}

		return State.COMPLETE;

	}
	

}