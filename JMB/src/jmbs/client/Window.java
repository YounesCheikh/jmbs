package jmbs.client;

import javax.swing.JFrame;

public class Window extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7012509669536189891L;

	public Window() {
		this.setTitle("Create new user");
		this.setSize(300, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		// Le conteneur principal
		CUPanel content = new CUPanel();
		//content.setPreferredSize(new Dimension(400, 400));
		//content.setSize(400, 400);
		// JPanel content = new JPanel();
		// On ajoute le conteneur
		this.setContentPane(content);
		this.setVisible(true);
	}
}