package jmbs.client;

import javax.swing.JFrame;

public class Window extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7012509669536189891L;

	public Window() {
		this.setTitle("Create new user");
		this.setSize(600, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		// Le conteneur principal
		//ConnectionPanel content = new ConnectionPanel();
		//PrintMSGS pmsg = new PrintMSGS();
		// On ajoute le conteneur
		//content.setPreferredSize(300,300);
		//this.setContentPane(content);
		//this.setContentPane(pmsg);
		//this.setVisible(true);
	}
}