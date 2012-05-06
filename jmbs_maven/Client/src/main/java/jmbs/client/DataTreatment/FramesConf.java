package jmbs.client.DataTreatment;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class FramesConf {

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
}
