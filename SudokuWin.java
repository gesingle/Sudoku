package csc143.sudoku;

import javax.swing.*;

import java.awt.event.*;

public class SudokuWin extends JFrame implements WindowListener {

	JFileChooser filechoose;
	SudokuBase base;

	public SudokuWin(SudokuBase b) {

		super();
		setTitle("Sudoku");
		filechoose = new JFileChooser();
		base = b;
		addWindowListener(this);
	}

	public void windowClosing(WindowEvent e) {

		SudokuSerializer.quitsave(base, this);
	}

	public void windowActivated(WindowEvent arg0) {
	}

	public void windowClosed(WindowEvent arg0) {
	}

	public void windowDeactivated(WindowEvent arg0) {
	}

	public void windowDeiconified(WindowEvent arg0) {
	}

	public void windowIconified(WindowEvent arg0) {
	}

	public void windowOpened(WindowEvent arg0) {

	}
}
