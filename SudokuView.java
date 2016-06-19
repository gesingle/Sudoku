package csc143.sudoku;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.*;
import javax.swing.*;

/**
 * Creates a graphical output / user interface for Sudoku
 * 
 * @author GSingletary
 * @version Assigment 4: Sudoku Graphic Output - Standard
 */
@SuppressWarnings("serial")
public class SudokuView extends javax.swing.JPanel implements SelectedCell,
		java.awt.event.MouseListener, Observer {

	// fields
	private int size;
	public SudokuCell selected;
	private SudokuCell[] cells;
	protected JPanel grid;

	/**
	 * Constructor
	 * 
	 * @param base
	 *            Sudoku board to apply graphics to
	 */
	public SudokuView(SudokuBase base) {

		// instantiate fields
		size = base.size;
		selected = new SudokuCell(base, 0, 0);
		cells = new SudokuCell[size * size];
	    grid = new JPanel();
	    // set GridLayout for grid JPanel
	    grid.setLayout(new GridLayout(size, size));
	    
		// set Layout and preferred size
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(size * 52, size * 52));
	    
		// create SudokuCells and add them to grid and cells array
	    int y = 0;
	    for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++) {
				SudokuCell cell = new SudokuCell(base, i, j);
				cell.addMouseListener(this);
				grid.add(cell);
				cells[y] = cell;
				y++;
			}
	    }
	  
	    // add grid to this JPanel
	    add(grid);
	    base.addObserver(this);

	    
	}


	/**
	 * Set the selected cell to the given row and column.
	 * 
	 * @param row
	 *            The indicated row
	 * @param col
	 *            The indicated column
	 */
	public void setSelected(int row, int col) {
		
		// cancel previously selected sell
		selected.setSelected(false);
		// set new selected cell
		selected = cells[row * size + col];
		selected.setSelected(true);
		repaint();
	}

	/**
	 * Retrieves the row of the currently selected cell.
	 * 
	 * @return The row in which the selected cell is located.
	 */
	public int getSelectedRow() {

	  return selected.getRow();
		
	}

	/**
	 * Retrieves the column of the currently selected cell.
	 * 
	 * @return The column in which the selected cell is located.
	 */
	public int getSelectedColumn() {

		return selected.getColumn();
	}

	// Overrides for mouseListener
	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	/**
	 * Override for mouseCLicked - sets the selected cell to clicked cell
	 * 
	 * @param e MouseEvent
	 *            
	 */
	public void mouseClicked(MouseEvent e) {
		
		// cancel previously selected cell
		selected.setSelected(false);
		// set new selected cell
		selected = (SudokuCell) e.getSource();
		selected.setSelected(true);
	}
	
	public void update(Observable o, Object n) {
		repaint();
	}
}
