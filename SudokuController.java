package csc143.sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
/**
 * Controller for Sudoku game
 * 
 * @author GSingletary
 * @version Assignment 5: Sudoku Input Handling
 */
public class SudokuController extends JPanel implements MouseListener,
		ActionListener {

	private SudokuBase base;
	private SudokuView view;
	JFrame win;
	private JPanel numselector;
	private JPanel setupbtns;
	private JPanel setup;
	private JButton newboard;
	private JButton setgivens;
	private JButton cancel;
	private JButton cancel2;
	private JTextField rows;
	private JTextField cols;
	protected NewBoard boardmaker;

	/**
	 * Constructor - Sets up the GUI for user input
	 * 
	 * @param b SudokuBase to be used
	 * @param v SudoView to be used
	 */
	public SudokuController(SudokuBase b, SudokuView v, NewBoard nb) {

		base = b;
		view = v;
		boardmaker = nb;
		setLayout(new BorderLayout());

		// panel to hold number choices
		numselector = new JPanel();
		numselector.setLayout(new GridLayout());
		numselector.setPreferredSize(new Dimension(base.size * 53, 52));

		// add SudokuCells to numselector
		for (int i = 1; i <= base.size; i++) {
			SudokuCell cell = new SudokuCell(i);
			cell.addMouseListener(this);
			cell.setBackground(Color.green);
			numselector.add(cell);
		}
		// put numselctor in a JPanel and add to Controller
		JPanel selholder = new JPanel();
		selholder.add(numselector);
		add(selholder, BorderLayout.SOUTH);

		// Set-Givens and Cancel options for set-up mode
		setupbtns = new JPanel();
		setgivens = new JButton("Set Givens");
		setgivens.setSize(20, 20);
		setgivens.addActionListener(this);
		cancel = new JButton("Cancel");
		cancel.addActionListener(this);
		cancel.setSize(20, 20);
		if (b.isSetup) {
			setupbtns.add(setgivens);
			setupbtns.add(cancel);
		}
		add(setupbtns);

		// Panel with new game options
		win = new JFrame("Set-Up");
		win.setLocation(323, 0);
		setup = new JPanel();
		win.setSize(225, 175);
		rows = new JTextField();
		rows.addActionListener(this);
		rows.setColumns(2);
		cols = new JTextField();
		cols.addActionListener(this);
		cols.setColumns(2);
		JLabel r = new JLabel("Rows: ");
		JLabel c = new JLabel("Columns: ");
		newboard = new JButton("Create New Board");
		newboard.addActionListener(this);
		cancel2 = new JButton("Cancel");
		cancel2.addActionListener(this);
		setup.add(r);
		setup.add(rows);
		setup.add(c);
		setup.add(cols);
		setup.add(newboard);
		setup.add(cancel2);
		win.add(setup);
		win.setVisible(false);

		SudokuMenu menu = new SudokuMenu(this, b);
		boardmaker.win.setJMenuBar(menu);
	}

	/**
	 * mousePressed override
	 * 
	 * @param e
	 *            MouseEvent
	 */
	public void mousePressed(MouseEvent e) {

		SudokuCell cell = (SudokuCell) e.getSource();
		cell.setBackground(Color.magenta);
	}

	public void mouseReleased(MouseEvent e) {

	}

	/**
	 * mouseEntered override - changes background color of graphical buttons on
	 * mouseover
	 * 
	 * @param e
	 *            MouseEvent
	 */
	public void mouseEntered(MouseEvent e) {

		SudokuCell cell = (SudokuCell) e.getSource();
		cell.setBackground(Color.cyan);
	}

	/**
	 * mouseExited override - returns graphical button background color to
	 * default on mouse exit
	 * 
	 * @param e
	 *            MouseEvent
	 */
	public void mouseExited(MouseEvent e) {
		
		SudokuCell cell = (SudokuCell) e.getSource();
		cell.setBackground(Color.green);
	}

	/**
	 * mouseClicked override - provides functionality for graphical buttons
	 * 
	 * @param e
	 *            MouseEvent
	 */
	public void mouseClicked(MouseEvent e) {

		SudokuCell cell = (SudokuCell) e.getSource();
		cell.setBackground(Color.magenta);
		int value = cell.getValue();
		// change value of non given selected cell
		if (!base.isGiven(view.selected.getRow(), view.selected.getColumn())) {
			base.setValue(view.selected.getRow(), view.selected.getColumn(),
					value);
		}
		// system beep if user tries to change a given value
		else if (base
				.isGiven(view.selected.getRow(), view.selected.getColumn())) {
			Toolkit.getDefaultToolkit().beep();
		}
		cell.setBackground(Color.cyan);

	}

	/**
	 * actionPerformed override - provides functionality for newboard, newgame,
	 * setgivens, cancel, and cancel 2 buttons
	 * 
	 * @param e
	 *            ActionEvent
	 */
	public void actionPerformed(ActionEvent e) {

		////if (e.getSource() == newgame) {
			//win.setVisible(true);

		//}

		if (e.getSource() == newboard) {

			int r = Integer.parseInt(rows.getText());
			int c = Integer.parseInt(cols.getText());
			// warnings for bad user input
			if (r < 1 || r > 12) {
				Object[] options = { "OK" };
				JOptionPane.showOptionDialog(null,
						"Row value must be a positve integer.", "Warning",
						JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			} else if (c < 1 || c > 12) {
				Object[] options = { "OK" };
				JOptionPane.showOptionDialog(null,
						"Column value must be a positive integer.", "Warning",
						JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			} else if (r * c > 12) {
				Object[] options = { "OK" };
				JOptionPane.showOptionDialog(null,
						"Rows times Columns cannot be greater than 12.",
						"Warning", JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			} else {
				int a = Integer.parseInt(rows.getText());
				int b = Integer.parseInt(cols.getText());
				boardmaker.makeNewBoard(a, b);
				boardmaker.saveGame(view);
				win.dispose();
			}
		}

		else if (e.getSource() == setgivens) {
			base.fixGivens();
			setupbtns.setVisible(false);
			boardmaker.win.setTitle("Sudoku - NORMAL PLAY");
			boardmaker.win.setBackground(Color.white);
		}

		else if (e.getSource() == cancel) {
			SwingUtilities.windowForComponent(this).dispose();
		}

		else if (e.getSource() == cancel2) {
			win.dispose();
		}
	}
}
