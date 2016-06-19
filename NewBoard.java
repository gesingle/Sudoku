package csc143.sudoku;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Class creates a new board, view, and controller
 * 
 * @author GSingletary
 * @version Assignment 5: Sudoku Input Handling
 */
public class NewBoard {

	JFrame win;
	SudokuView savedgame;

	/**
	 * Constructor - instantiates the fields
	 * 
	 * @param window
	 *            Current Sudoku winow
	 */
	public NewBoard(JFrame window) {

		win = window;
	}

	/**
	 * Removes old view and controller and adds new one to Sudoku window
	 * 
	 * @param r rows
	 * @param c columns
	 */
	public void makeNewBoard(int r, int c) {

		// create new board
		SudokuBoard temp = new SudokuBoard(r, c, true);
		// remove old content from window
		win.getContentPane().removeAll();
		// new view and controller
		SudokuView newview = new SudokuView(temp);
		SudokuController ctrl = new SudokuController(temp, newview, this);
		// add view and controller to window
		JPanel cntr = new JPanel();
		cntr.add(newview);
		win.add(cntr);
		win.add(ctrl, BorderLayout.NORTH);
		// update board setting and color to indicate Setup Mode
		win.setTitle("Sudoku - SETUP MODE");
		win.setBackground(Color.BLUE);
		win.pack();
	}

	public void makeSavedBoard(SudokuBase base) {
		// remove old content from window
		win.getContentPane().removeAll();
		// new view and controller
		SudokuView newview = new SudokuView(base);
		SudokuController ctrl = new SudokuController(base, newview, this);
		// add view and controller to window
		JPanel cntr = new JPanel();
		cntr.add(newview);
		win.add(cntr);
		win.add(ctrl, BorderLayout.NORTH);
		win.pack();
	}

	public void saveGame(SudokuView v) {
		savedgame = v;
	}
}
