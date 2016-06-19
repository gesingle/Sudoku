package csc143.sudoku;

import java.awt.BasicStroke;
import java.awt.Color;

import javax.swing.JPanel;

/**
 * Class creates a Sudoku cell with appropriate value representation
 * 
 * @author GSingletary
 * @version Assigment 4: Sudoku Graphic Output
 */
@SuppressWarnings("serial")
public class SudokuCell extends JPanel {

	// fields
	public int value;
	private boolean given;
	private boolean selected;
	public int row;
	public int col;
	private SudokuBase base;
	private boolean forpanel;

	/**
	 * Constructor
	 * 
	 * @param basein SudokuBase passed in
	 * @param r number of rows
	 * @param c number of columns
	 */
	public SudokuCell(SudokuBase basein, int r, int c) {

		setPreferredSize(new java.awt.Dimension(50, 50));
		row = r;
		col = c;
		base = basein;
	}

	public SudokuCell(int n) {
		value = n;
		forpanel = true;
		setPreferredSize(new java.awt.Dimension(50, 50));
		
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return col;
	}

	public void setSelected(boolean o) {

		selected = o;
		repaint();
	}

	public int getValue() {
		return value;
	}
	
	/**
	 * Override for paintComponent - Creates a graphic representation of the
	 * cells value
	 * 
	 * @param g
	 *            Graphics
	 */
	public void paintComponent(java.awt.Graphics g) {

		super.paintComponent(g);
		// Graphic2d to create thick lines
		java.awt.Graphics2D g2d = (java.awt.Graphics2D) g;

		// cell border
		g2d.setStroke(new BasicStroke(1));
		g2d.drawRect(1, 1, 49, 49);
		g2d.setStroke(new BasicStroke(3));

		if (!forpanel) {
			if (((row / base.rows + col / base.columns) % 2) == 0) {
				setBackground(Color.LIGHT_GRAY);
			} else {
				setBackground(Color.white);
			}
		}

		if (!forpanel) {
			for (int i = 0; i < base.size; i++) {
				for (int j = 0; j < base.size; j++) {
					if (row == i && col == j) {
						value = base.getValue(i, j);
						given = base.isGiven(i, j);
					}
				}
			}
		}

		if (selected) {
			setBackground(Color.YELLOW);
		}

		// change foreground if given
		if (given) {
			g2d.setColor(new Color(0, 128, 0));
		}

		// draw appropriate graphic
		if (value == 1) {
			g2d.drawRect(10, 10, 30, 30);
		} else if (value == 2) {
			g2d.drawRect(10, 10, 30, 30);
			g2d.drawRect(15, 15, 20, 20);
		} else if (value == 3) {
			g2d.drawRect(10, 10, 30, 30);
			g2d.drawRect(15, 15, 20, 20);
			g2d.drawRect(20, 20, 10, 10);
		} else if (value == 4) {
			g2d.drawOval(10, 10, 30, 30);
		} else if (value == 5) {
			g2d.drawOval(10, 10, 30, 30);
			g2d.drawOval(15, 15, 20, 20);
		} else if (value == 6) {
			g2d.drawOval(10, 10, 30, 30);
			g2d.drawOval(15, 15, 20, 20);
			g2d.drawOval(20, 20, 10, 10);
		} else if (value == 7) {
			g2d.drawLine(10, 10, 10, 40);
		} else if (value == 8) {
			g2d.drawLine(10, 10, 10, 40);
			g2d.drawLine(20, 10, 20, 40);
		} else if (value == 9) {
			g2d.drawLine(10, 10, 10, 40);
			g2d.drawLine(20, 10, 20, 40);
			g2d.drawLine(30, 10, 30, 40);
		} else if (value == 10) {
			g2d.drawLine(10, 10, 10, 40);
			g2d.drawLine(20, 10, 20, 40);
			g2d.drawLine(30, 10, 30, 40);
			g2d.drawLine(40, 10, 40, 40);
		} else if (value == 11) {
			g2d.drawLine(10, 20, 40, 20);
		} else if (value == 12) {
			g2d.drawLine(10, 20, 40, 20);
			g2d.drawLine(10, 30, 40, 30);
		}
	}

}
