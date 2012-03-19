package jmbs.client.Graphics;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.util.regex.Pattern;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConnectionPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8035127754370206526L;
	private JTextField emailTextField;
	private JPasswordField passwordField;
	private JLabel respLabel; /* this will be used to say to the user if there is a wrong password or email */
	/**
	 * the main window
	 */
	private MainWindow w;
	/**
	 * the connection frame, used to stop displaying it after a connection successed
	 */
	private ConnectionFrame cf;
	/**
	 * Create the panel.
	 * @param w the main window
	 * @param cf the connection frame
	 */
	public ConnectionPanel(MainWindow w, ConnectionFrame cf) {
		this.w = w;
		this.cf = cf;
		JLabel lblConnectToJmbs = new JLabel("Connect to JMBS");
		lblConnectToJmbs.setFont(new Font("Dialog", Font.BOLD, 16));
		
		JLabel lblEmailAdress = new JLabel("Email Adress:");
		
		JLabel lblPassword = new JLabel("Password:");
		
		emailTextField = new JTextField();
		emailTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkConnection();
			}
		});
		emailTextField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkConnection();
			}
		});
		
		respLabel = new JLabel(""); /* as default */
		
		JCheckBox chckbxRememberMe = new JCheckBox("Remember me");
		
		
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkConnection();
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(91)
					.addComponent(lblConnectToJmbs, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(95))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(6)
							.addComponent(respLabel))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblEmailAdress)
								.addComponent(lblPassword))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
								.addComponent(emailTextField, GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
								.addComponent(chckbxRememberMe))))
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(218, Short.MAX_VALUE)
					.addComponent(btnConnect)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addComponent(lblConnectToJmbs)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmailAdress)
						.addComponent(emailTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(respLabel)
					.addGap(18)
					.addComponent(chckbxRememberMe)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnConnect)
					.addContainerGap(19, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		

	}
	
	/**
	 * verify the email
	 * @param mail adress mail
	 * @return true if the user has entred a right email adress
	 */
	private boolean verification (String mail) {
		boolean correctMail = Pattern.matches("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$", mail);
		return (correctMail);
	}
	
	/**
	 * verify the password length
	 * @param passwdLength the password length
	 * @return true if password length >= 4
	 */
	private boolean verification (int passwdLength) {
		boolean correctPasswd = (passwdLength >= 4);
		return (correctPasswd);
	}
	
	/**
	 * this methode update the response Label 'respLabel'
	 */
	private void checkConnection() {
		if (!verification(emailTextField.getText())) {
			respLabel.setText("Enter a valide email please!");
			respLabel.setForeground(Color.red);
			//putElement(0, 3, respLabel);
		}
		else if (!verification(passwordField.getPassword().length)) {
			respLabel.setText("password less than 4 chars !");
			respLabel.setForeground(Color.red);
			//putElement(0, 3, respLabel);
		}
		else {
			respLabel.setText("Connection to serveur...");
			respLabel.setForeground(new Color(0,100,0));
			cf.setVisible(false);
			this.getMainWindow().getFrame().setVisible(true);
			//putElement(0, 3, respLabel);
			//System.out.println("Vrai");
		}
	}
	
	/**
	 * @return return the main windows of this program
	 */
	private MainWindow getMainWindow() {
		return w;
	}
	
}
