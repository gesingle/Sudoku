package csc143.sudoku;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;

/**
 * Main class for Sudoku game
 * 
 * @author GSingletary
 * @author Dan Jinguji
 * @version Assignment 5: Sudoku Input Handling
 */
public class SudokuMain {
 
	
	/**
	 * Application method
	 * 
	 * @param args Command line arguments
	 */
    public static void main(String[] args) {
        
    	SudokuBoard board = makeBoard();
        //JFrame win = new JFrame("Sudoku");
    	SudokuWin win = new SudokuWin(board);
        win.setBackground(Color.white);
        JPanel cntr = new JPanel();
        SudokuView view = new SudokuView(board);
        NewBoard newboard = new NewBoard(win);
        newboard.saveGame(view);
        SudokuController ctrl = new SudokuController(board, view, newboard);
        cntr.add(view);
        win.add(cntr);
        win.add(ctrl, BorderLayout.NORTH);
        win.pack();
        win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        win.setVisible(true);
    }

    /**
     * Creates a default SudokuBoard for play
     * 
     * @return Default SudokuBoard
     */
    public static SudokuBoard makeBoard() {
        SudokuBoard board = new SudokuBoard(2, 3, false);
        board.setValue(0, 3, 6);
        board.setValue(0, 5, 1);
        board.setValue(1, 2, 4);
        board.setValue(1, 4, 5);
        board.setValue(1, 5, 3);
        board.setValue(2, 3, 3);
        board.setValue(3, 2, 6);
        board.setValue(4, 0, 2);
        board.setValue(4, 1, 3);
        board.setValue(4, 3, 1);
        board.setValue(5, 0, 6);
        board.setValue(5, 2, 1);
        board.fixGivens();
        board.setValue(1, 0, 6);
        board.setValue(3, 1, 5);
        return board;
    }
}