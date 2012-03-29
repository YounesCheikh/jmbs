package jmbs.client.Graphics;

import javax.swing.JFrame;

import jmbs.client.SysConf;

public class ConnectionFrame extends JFrame {

	/**
	 * Display a frame contain the connection panel
	 */
	private static final long serialVersionUID = 6941716821811760066L;
	
	/**
	 * Create the frame.
	 * 
	 * @param MainWindow
	 *            , need this to display it after a successed connection
	 */
	public ConnectionFrame(MainWindow w) {
		setResizable(false);
		setLocationRelativeTo(null);
		this.setTitle("Connect to JMBS!");
		this.setSize(430, 430);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//this.setLocationRelativeTo(null);
		new SysConf().centerThisFrame(this);

		ConnectionPanel cp = new ConnectionPanel(w, this);
		this.getContentPane().add(cp);
		// this.setVisible(true);
	}

}
