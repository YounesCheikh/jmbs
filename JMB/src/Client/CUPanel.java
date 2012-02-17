package jmbs.client;

/*
 * GridBagLayoutDemo.java requires no other files.
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.omg.PortableInterceptor.ClientRequestInfo;


public class CUPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1280790436758795330L;
	final static boolean shouldFill = true;
	final static boolean shouldWeightX = true;
	final static boolean RIGHT_TO_LEFT = false;
	
	private JTextField jtName;
	private JTextField jtFName;
	private JTextField jtMail;
	private JPasswordField jpfPass;
	
	private String strName;
	private String strFName;
	private String strMail;
	private String strpass;
	
	
	public CUPanel() {
		// createAndShowGUI();
		if (RIGHT_TO_LEFT) {
			this.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		}

		// JButton button;

		JLabel jlName = new JLabel("First Name: ");
		JLabel jlFName = new JLabel("Last Name:");
		JLabel jlMail = new JLabel("Adress Mail: ");
		JLabel jlPass = new JLabel("Password: ");

		jtName = new JTextField();
		jtFName = new JTextField();
		jtMail = new JTextField();
		jpfPass = new JPasswordField();
		
		JButton jbCreate = new JButton("Create new user");

		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		if (shouldFill) {
			// natural height, maximum width
			c.fill = GridBagConstraints.HORIZONTAL;
		}

		// Start putting the elements
        
		JLabel label = new JLabel("Create new user", SwingConstants.CENTER);
		Font police = new Font("Arial", Font.BOLD, 16);
        label.setFont(police);
        label.setForeground(Color.BLUE);
        
		if (shouldWeightX) {
			c.weightx = 0.5;
		}
		c.insets = new Insets(10, 10, 10, 10);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.ipady = 0;
		this.add(label, c);
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 1;
		this.add(jlName, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 2;
		this.add(jlFName, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 3;
		this.add(jlMail, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 4;
		this.add(jlPass, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 1;
		this.add(jtName, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 2;
		this.add(jtFName, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 3;
		this.add(jtMail, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 4;
		this.add(jpfPass, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 5;
		this.add(jbCreate, c);
		
		jbCreate.addActionListener(new ButtonListener());
		
		/*
		 * button = new JButton("Create"); c.fill =
		 * GridBagConstraints.HORIZONTAL; c.ipady = 0; // reset to default
		 * c.weighty = 1.0; // request any extra vertical space c.anchor =
		 * GridBagConstraints.PAGE_END; // bottom of space c.insets = new
		 * Insets(10, 0, 0, 0); // top padding c.gridx = 1; // aligned with
		 * button 2 c.gridwidth = 2; // 2 columns wide c.gridy = 2; // third row
		 * pane.add(button, c);
		 */
	}

	public class ButtonListener implements ActionListener{ 
        public void actionPerformed(ActionEvent e) {
        	strName = jtName.getText();
        	strFName = jtFName.getText();
        	strMail = jtMail.getText();
        	strpass = new HashPassword(jpfPass.getText()).getHashed(); // todo: hash with HashPassword to md5
        	
        	//System.out.println("NAME: "+strName+" "+ strFName);
        	//System.out.println("EMAIL "+strMail);
        	//System.out.println("PASS: "+strpass);
        }  
    }
	
	public UserDTO getUser() {
		return new UserDTO(strName, strFName, strMail, 0, strpass, null);
	}
	
}