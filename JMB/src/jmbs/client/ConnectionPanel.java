package jmbs.client;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class ConnectionPanel extends JPanel {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -9038983391385337657L;
	
	private JTextField jtMail;
	private JPasswordField jpfPass;
	private GridBagConstraints c;
	
	public ConnectionPanel() {
		jtMail = new JTextField();
		jpfPass = new JPasswordField();
		JButton jbCreate = new JButton("Connect");
		this.setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		
		JLabel label = new JLabel("JMBS Connect", SwingConstants.CENTER);
		Font police = new Font("Arial", Font.BOLD, 16);
		label.setFont(police);
		label.setForeground(Color.BLUE);
		
		c.insets = new Insets(10, 10, 10, 10);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.gridwidth = 2;
		c.ipady = 0;
		putElement(0, 0, label);
		c.gridwidth = 1;
		c.weightx = 0.5;
		
		putElement(0, 1, new JLabel("E-mail: "));
		putElement(0, 2, new JLabel("Passowrd:"));
		putElement(1, 1, jtMail);
		putElement(1, 2, jpfPass);
		
		putElement(1, 3, jbCreate);
		
		jbCreate.addActionListener(new ButtonListener());
	}
	
	private void putElement(int x, int y, Component obj) {
		c.gridx = x;
		c.gridy = y;
		this.add(obj, c);
	}
	
	public class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//UserDTO u = submit(jtName.getText(), jtFName.getText(), jtMail.getText(), new HashPassword(passToString(jpfPass.getPassword())).getHashed());
			System.out.println(jtMail.getText());
			System.out.println(new HashPassword(passToString(jpfPass.getPassword())).getHashed());
		}
		/*
		public UserDTO submit(String name, String fname, String mail, String psw) {
			return new UserDTO(name, fname, mail, 0, psw, null);
		}
		*/
		
		private String passToString(char[] pass) {
			String tmp = "";
			for (char c : pass) {
				tmp += c;
			}
			return tmp;
		}
	}
}
