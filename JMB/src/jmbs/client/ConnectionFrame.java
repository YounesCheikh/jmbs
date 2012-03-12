package jmbs.client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

public class ConnectionFrame extends JFrame {

	/**
	 * 
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
