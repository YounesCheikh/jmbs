package jmbs.client.dataTreatment;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 * A different methods that used with frames
 * 
 * @author Younes CHEIKH
 * @author Benjamin Babic
 */
public class FramesConf {

	/**
	 * Center the Dialog in the middle of the screen
	 * 
	 * @param window
	 *            the dialog frame
	 */
	public static void centerThisDialog(JDialog window) {
		// Get the size of thescreen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		// Determine the new location of the window
		int w = window.getSize().width;
		int h = window.getSize().height;
		int x = (dim.width - w) / 2;
		int y = (dim.height - h) / 2;
		// Move the window
		window.setLocation(x, y);
	}

	/**
	 * Center the Frame in the middle of the screen
	 * 
	 * @param window
	 *            The frame
	 */
	public static void centerThisFrame(JFrame window) {
		// Get the size of thescreen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		// Determine the new location of the window
		int w = window.getSize().width;
		int h = window.getSize().height;
		int x = (dim.width - w) / 2;
		int y = (dim.height - h) / 2;
		// Move the window
		window.setLocation(x, y);
	}

	public static void mouveThisFrame(JFrame frame) {
		Point p = frame.getLocationOnScreen();
		int movedX = 35, maxX = 80;

		for (int i = 0; i < 4; i++) {
			for (int x = p.x; x < p.x + maxX; x += movedX) {
				frame.setLocation(x, p.y);
			}

			for (int x = p.x + maxX; x > p.x; x -= movedX) {
				frame.setLocation(x, p.y);
			}

			for (int x = p.x; x > p.x - maxX; x -= movedX) {
				frame.setLocation(x, p.y);
			}

			for (int x = p.x - maxX; x < p.x; x += movedX) {
				frame.setLocation(x, p.y);
			}
		}
	}
}
