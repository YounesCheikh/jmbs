package jmbs.client.Graphics;

/*
 * GridBagLayoutDemo.java requires no other files.
 */

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

import jmbs.client.HashPassword;

public class CUPanel extends JPanel {
	/**
	 * this is not enabled currently
	 */
	private static final long serialVersionUID = -1280790436758795330L;

	private JTextField jtName;
	private JTextField jtFName;
	private JTextField jtMail;
	private JPasswordField jpfPass;

	private GridBagConstraints c;

	public CUPanel() {

		jtName = new JTextField();
		jtFName = new JTextField();
		jtMail = new JTextField();
		jpfPass = new JPasswordField();

		JButton jbCreate = new JButton("Create new user");
		this.setLayout(new GridBagLayout());
		c = new GridBagConstraints();

		JLabel label = new JLabel("Create new user", SwingConstants.CENTER);
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
		putElement(0, 1, new JLabel("First Name: "));
		putElement(0, 2, new JLabel("Last Name:"));
		putElement(0, 3, new JLabel("Adress Mail: "));
		putElement(0, 4, new JLabel("Password: "));
		putElement(1, 1, jtName);
		putElement(1, 2, jtFName);
		putElement(1, 3, jtMail);
		putElement(1, 4, jpfPass);
		putElement(1, 5, jbCreate);
		// Action Listener
		jbCreate.addActionListener(new ButtonListener());
	}

	private void putElement(int x, int y, Component obj) {
		c.gridx = x;
		c.gridy = y;
		this.add(obj, c);
	}

	public class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			jmbs.common.User u = submit(jtName.getText(), jtFName.getText(), jtMail.getText(), new HashPassword(passToString(jpfPass.getPassword())).getHashed());
			System.out.println(u.toString() + new HashPassword(passToString(jpfPass.getPassword())).getHashed());
			System.out.println(new HashPassword("coucou").getHashed());
		}

		public jmbs.common.User submit(String name, String fname, String mail, String psw) {
			return new jmbs.common.User(name, fname, mail);
		}

		private String passToString(char[] pass) {
			String tmp = "";
			for (char c : pass) {
				tmp += c;
			}
			return tmp;
		}
	}
}