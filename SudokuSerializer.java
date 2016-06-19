package csc143.sudoku;

import java.io.*;

import javax.swing.*;
import javax.tools.JavaFileManager.Location;

/**
 * Provides serialization for SudokuBase objects and allows for saving and
 * opening games
 * 
 * @author GSingletary
 *
 */
public class SudokuSerializer {

	/**
	 * Standard save method. Saves to previous game file if one exists or
	 * prompts for a new file if it doesn't
	 * 
	 * @param base
	 *            Current game's SudokuBase
	 * @param win
	 *            Current game's window
	 */
	public static void save(SudokuBase base, JFrame win) {

		JFileChooser filechoose = new JFileChooser();
		File saveFile = null;
		// Prompt user for save file if one does not exist
		if (base.savefile == null) {
			int x = filechoose.showSaveDialog(win);
			if (x == JFileChooser.APPROVE_OPTION) {
				saveFile = filechoose.getSelectedFile();
				try {
					FileOutputStream output = new FileOutputStream(saveFile);
					ObjectOutputStream oos = new ObjectOutputStream(output);
					oos.writeObject(base);
					oos.close();
				} catch (FileNotFoundException e1) {
					JOptionPane.showMessageDialog(win, "File not found.");
					e1.printStackTrace();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(win, "Error saving file.");
					e1.printStackTrace();
				} finally {
				}
			}
			base.savefile = saveFile;
		}

		// save to previous game file if one already exists for current game
		else {
			try {
				FileOutputStream output = new FileOutputStream(base.savefile);
				ObjectOutputStream oos = new ObjectOutputStream(output);
				oos.writeObject(base);
				oos.close();
			} catch (FileNotFoundException e1) {
				JOptionPane.showMessageDialog(win, "File not found.");
				e1.printStackTrace();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(win, "Error saving file.");
				e1.printStackTrace();
			}
		}
	}

	/**
	 * Saveas method. Allows user to create a new save file
	 * 
	 * @param base
	 *            Current game's SudokuBase
	 * @param win
	 *            Current game's window
	 */
	public static void saveas(SudokuBase base, JFrame win) {

		JFileChooser filechoose = new JFileChooser();
		File saveFile = null;
		// Prompt user for save file
		int x = filechoose.showSaveDialog(win);
		if (x == JFileChooser.APPROVE_OPTION) {
			saveFile = filechoose.getSelectedFile();
			try {
				FileOutputStream output = new FileOutputStream(saveFile);
				ObjectOutputStream oos = new ObjectOutputStream(output);
				oos.writeObject(base);
				oos.close();
			} catch (FileNotFoundException e1) {
				JOptionPane.showMessageDialog(win, "File not found.");
				e1.printStackTrace();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(win, "Error saving file.");
				e1.printStackTrace();
			} finally {
			}
		}
		base.savefile = saveFile;
	}

	/**
	 * Provides support for saving when a close operation is intiated by user
	 * 
	 * @param base Current game's SudokuBase
	 * @param win Current game's window
	 */
	public static void quitsave(SudokuBase base, JFrame win) {

		JFileChooser filechoose = new JFileChooser();

		// Ask user if they would like to save before closing
		int n = JOptionPane.showConfirmDialog(win,
				"Would you to save before closing?", "Warning",
				JOptionPane.YES_NO_OPTION);

		// Save game to new or current file if requested
		if (n == JOptionPane.YES_OPTION) {

			File saveFile = null;
			if (base.savefile == null) {
				int x = filechoose.showSaveDialog(win);
				if (x == JFileChooser.APPROVE_OPTION) {
					saveFile = filechoose.getSelectedFile();
					try {
						FileOutputStream output = new FileOutputStream(saveFile);
						ObjectOutputStream oos = new ObjectOutputStream(output);
						oos.writeObject(base);
						oos.close();
					} catch (FileNotFoundException e1) {
						JOptionPane.showMessageDialog(win, "File not found.");
						e1.printStackTrace();
					} catch (IOException e1) {
						JOptionPane
								.showMessageDialog(win, "Error saving file.");
						e1.printStackTrace();
					} finally {
					}
					base.savefile = saveFile;
					System.exit(0);
				} else if (x == JFileChooser.CANCEL_OPTION) {
					win.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
				}
			}

			else {
				try {
					FileOutputStream output = new FileOutputStream(
							base.savefile);
					ObjectOutputStream oos = new ObjectOutputStream(output);
					oos.writeObject(base);
					oos.close();
				} catch (FileNotFoundException e1) {
					JOptionPane.showMessageDialog(win, "File not found.");
					e1.printStackTrace();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(win, "Error saving file.");
					e1.printStackTrace();
				}
			}
		}

		else if (n == JOptionPane.NO_OPTION) {
			win.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		}
	}
	
	/**
	 * Provides support for opening a previously saved game
	 * 
	 * @param controller SudokuController
	 */
	public static void open(SudokuController controller) {
		
		File openFile = null;
		JFileChooser filechoose = new JFileChooser();
		int x = filechoose.showOpenDialog(controller.win);
		if (x == JFileChooser.APPROVE_OPTION) {
			openFile = filechoose.getSelectedFile();
			try {
				FileInputStream input = new FileInputStream(openFile);
				ObjectInputStream ois = new ObjectInputStream(input);
				SudokuBase savedbase = (SudokuBase) ois.readObject();
				controller.boardmaker.makeSavedBoard(savedbase);
				ois.close();
			} catch (FileNotFoundException e1) {
				JOptionPane.showMessageDialog(controller.win,
						"File not found");
				e1.printStackTrace();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(controller.win,
						"Error opening file.");
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				JOptionPane.showMessageDialog(controller.win,
						"Error opening file.");
				e1.printStackTrace();
			} finally {
			}
		}
	}
}
