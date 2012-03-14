package jmbs.client;

import javax.swing.JFrame;

public class ConnectionFrame extends JFrame {

	/**
	 * Display a frame contain the connection panel
	 */
	private static final long serialVersionUID = 6941716821811760066L;



	/**
	 * Create the frame.
	 */
	public ConnectionFrame() {
		this.setTitle("Connect to JMBS!");
		this.setSize(300, 240);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		ConnectionPanel cp = new ConnectionPanel();
		this.getContentPane().add(cp);
		//this.setVisible(true);
	}

}
