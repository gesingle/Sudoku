package csc143.sudoku;

import javax.swing.*;

import java.io.*;
import java.awt.event.*;

/**
 * Class provides menu options for Sudoku Window
 * 
 * @author GSingletary
 */
public class SudokuMenu extends JMenuBar implements ActionListener {

	private SudokuController controller;
	private SudokuBase base;
	private JMenu file;
	private JMenuItem reset;
	private JMenuItem newgame;
	private JMenuItem open;
	private JMenuItem save;
	private JMenuItem saveas;
	private JMenuItem quit;
	private JFileChooser filechoose;

	/** 
	 * Constructor
	 * 
	 * @param c Sudoku controller
	 * @param b SudokuBase
	 */
	public SudokuMenu(SudokuController c, SudokuBase b) {

		super();
		controller = c;
		base = b;
		file = new JMenu("File");
		file.addActionListener(this);
		reset = new JMenuItem("Reset");
		reset.addActionListener(this);
		newgame = new JMenuItem("New");
		newgame.addActionListener(this);
		open = new JMenuItem("Open");
		open.addActionListener(this);
		save = new JMenuItem("Save");
		save.addActionListener(this);
		saveas = new JMenuItem("Save-As");
		saveas.addActionListener(this);
		quit = new JMenuItem("Quit");
		quit.addActionListener(this);
		filechoose = new JFileChooser();

		file.add(newgame);
		file.add(open);
		file.add(save);
		file.add(saveas);
		file.add(quit);
		add(file);
		add(reset);

	}

	/**
	 * Action performed override
	 * 
	 * @param e ActionEvent
	 */
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == newgame) {
			controller.win.setVisible(true);
		}

		else if (e.getSource() == open) {

			SudokuSerializer.open(controller);
		}

		else if (e.getSource() == save) {

			SudokuSerializer.save(base, controller.win);
		}

		else if (e.getSource() == saveas) {
			
			SudokuSerializer.saveas(base, controller.win);
		}

		else if (e.getSource() == quit) {
			
			SudokuSerializer.quitsave(base, controller.win);
			
		}
		

		else if (e.getSource() == reset) {

			int n = JOptionPane.showConfirmDialog(controller.win,
					"Would you to remove all current play values?", "Warning",
					JOptionPane.YES_NO_OPTION);

			if (n == JOptionPane.YES_OPTION) {
				int size = base.size;
				for (int i = 0; i < size; i++) {
					for (int j = 0; j < size; j++) {
						if (!base.isGiven(i, j)) {
							base.setValue(i, j, 0);
						}
					}
				}
			}
		}
	}
}
