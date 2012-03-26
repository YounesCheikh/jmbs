package jmbs.client;

import jmbs.client.Graphics.ConnectionFrame;
import jmbs.client.Graphics.MainWindow;

public class MainClient {
	private static MainWindow window;
	/**
	 * The Connection Window
	 */
	private static ConnectionFrame cf;

	public static void main(String[] args) {

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				cf = new ConnectionFrame(window);
				cf.setVisible(true);
			}
		});
	}
}
