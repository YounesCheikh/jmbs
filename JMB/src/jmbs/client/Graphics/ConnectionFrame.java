package jmbs.client.Graphics;

import javax.swing.JFrame;

public class ConnectionFrame extends JFrame {

	/**
	 * Display a frame contain the connection panel
	 */
	private static final long serialVersionUID = 6941716821811760066L;



	/**
	 * Create the frame.
	 * @param MainWindow , need this to display it after a successed connection
	 */
	public ConnectionFrame(MainWindow w) {
		setResizable(false);
		this.setTitle("Connect to JMBS!");
		this.setSize(320, 240);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setLocationRelativeTo(null);
		
		ConnectionPanel cp = new ConnectionPanel(w,this);
		this.getContentPane().add(cp);
		//this.setVisible(true);
	}

}
