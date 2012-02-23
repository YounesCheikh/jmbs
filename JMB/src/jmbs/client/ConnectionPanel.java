package jmbs.client;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

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
	private JLabel jStatus ;
	
	public ConnectionPanel() {
		jtMail = new JTextField();
		jpfPass = new JPasswordField();
		//jtMail.setSize(new Dimension(30,30));
		//jpfPass.setPreferredSize(new Dimension(30,30));
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
		c.gridwidth = 3;
		jStatus = new JLabel("");
		
		putElement(0, 3, jStatus);
		putElement(1, 4, jbCreate);
		//this.setBackground(Color.magenta);
		jbCreate.addActionListener(new ButtonListener());
	}
	
	private void putElement(int x, int y, Component obj) {
		c.gridx = x;
		c.gridy = y;
		this.add(obj, c);
	}
	
	private boolean verification (String mail, int passwdLength) {
		boolean correctMail = Pattern.matches("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$", mail);
		boolean correctPasswd = (passwdLength > 8);
		return (correctMail && correctPasswd);
	}
	
	public class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//UserDTO u = submit(jtName.getText(), jtFName.getText(), jtMail.getText(), new HashPassword(passToString(jpfPass.getPassword())).getHashed());
			System.out.println("Email: "+jtMail.getText());
			System.out.println("Password: "+new HashPassword(passToString(jpfPass.getPassword())).getHashed());
			if (!verification(jtMail.getText(), jpfPass.getPassword().length)) {
				jStatus.setText("Email or password not valide!");
				jStatus.setForeground(Color.red);
				putElement(0, 3, jStatus);
			}
			else {
				jStatus.setText("Connection to serveur...");
				jStatus.setForeground(Color.green);
				putElement(0, 3, jStatus);
				//System.out.println("Vrai");
			}
			
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
