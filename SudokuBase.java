package csc143.sudoku;

import java.io.*;

/**
 * 
 * @author Dan Jinguji
 * @author GSingletary
 *
 * @version Assignment 3: Sudoku Core - Standard
 * 
 *          Class provides support for givens
 */
public abstract class SudokuBase extends java.util.Observable implements Serializable {

	// fields for row, column, and board size
	public final int rows;
	public final int columns;
	public final int size;
	// array to hold the values of the Sudoku board
	private final int[] grid;
	// mask and unmask
	private static final int GIVEN_MASK = 0x00000100;
	private static final int GIVEN_UNMASK = ~GIVEN_MASK;
	// State field for describing row, column, or region status
	public enum State {COMPLETE, INCOMPLETE, ERROR};
	protected boolean isSetup;
	protected File savefile;

	/**
	 * Constructor that instantiates grid array using row and column size
	 * arguments
	 * 
	 * @param layoutRows
	 *            Number of rows
	 * @param layoutColumns
	 *            Number of columns
	 * @param o
	 *            Setup mode indicator
	 */
	public SudokuBase(int layoutRows, int layoutColumns, boolean o) {

		isSetup = o;
		rows = layoutRows;
		columns = layoutColumns;
		size = columns * rows;
		grid = new int[size * size];
	}

	/**
	 * Returns the corresponding grid index for a specific cell on the Sudoku
	 * board
	 * 
	 * @param row
	 *            Row the cell is in
	 * @param col
	 *            Column the cell is in
	 * @return The correct index
	 */
	private int getIndex(int row, int col) {
		if (row < 0 || row >= size || col < 0 || col >= size) {
			String msg = "Error in location";
			throw new IllegalArgumentException(msg);
		}
		return row * size + col;
	}

	/**
	 * Returns the value of a specific cell on the Sudoku baord
	 * 
	 * @param row
	 *            Row the cell is in
	 * @param col
	 *            Column the cell is in
	 * @return The value of the cell
	 */
	public int getValue(int row, int col) {
		return grid[getIndex(row, col)] & GIVEN_UNMASK;
	}

	/**
	 * Sets the value of a specific cell on the Sudoku board and clears givens
	 * bit
	 * 
	 * @param row
	 *            Row the cell is in
	 * @param col
	 *            Column the cell is in
	 * @param value
	 *            Value to be assigned
	 */
	public void setValue(int row, int col, int value) {
		if (value < 0 || value > size) {
			String msg = "Value out of range: " + value;
			throw new IllegalArgumentException(msg);
		}
		if (isGiven(row, col)) {
			String msg = "Cannot set given location: " + row + ", " + col;
			throw new IllegalStateException(msg);
		}
		grid[getIndex(row, col)] = value;
		setChanged();
		notifyObservers();
	}

	/**
	 * Checks the status of a givens bit
	 * 
	 * @param row
	 *            Row the cell is in
	 * @param col
	 *            Column the cell is in
	 * @return Status of the givens bit
	 */
	public boolean isGiven(int row, int col) {
		return (grid[getIndex(row, col)] & GIVEN_MASK) == GIVEN_MASK;
	}

	/**
	 * Sets givens bit on all non-zero board cells
	 */
	public void fixGivens() {
		for (int i = 0; i < grid.length; i++)
			if (grid[i] != 0)
				grid[i] |= GIVEN_MASK;
		setChanged();
		notifyObservers();
	}

	public abstract State getRowState(int n);

	public abstract State getColumnState(int n);

	public abstract State getRegionState(int n);

	/**
	 * Creates a string representation of the board
	 */
	public String toString() {
		String board = "";
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++)
				board += charFor(i, j) + " ";
			board += "\n";
		}
		return board;
	}

	/**
	 * Returns the character representation of a cell value as a string
	 * 
	 * @param i
	 *            Row the cell is in
	 * @param j
	 *            Column the cell is in
	 * @return String with character representation
	 */
	private String charFor(int i, int j) {
		int v = getValue(i, j);
		if (v < 0) {
			return "?";
		} else if (v == 0) {
			return " ";
		} else if (v < 36) {
			return Character.toString(Character.forDigit(v, 36)).toUpperCase();
		} else {
			return "?";
		}
	}

	protected int getRawValue(int row, int col) {
		return grid[getIndex(row, col)];
	}

	protected void setRawValue(int row, int col, int value) {
		grid[getIndex(row, col)] = value;
	}
	
	protected void setSaveFile(File f) {
		savefile = f;
	}

}
