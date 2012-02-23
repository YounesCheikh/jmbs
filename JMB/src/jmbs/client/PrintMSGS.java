package jmbs.client;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import jmbs.client.ConnectionPanel.ButtonListener;

public class PrintMSGS extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7120390944031252968L;
	private JTextField msg;
	private GridBagConstraints c;
	
	public PrintMSGS() {
		
		JButton refreshButton = new JButton("Refresh");
		this.setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		
		//msg.setBackground(Color.BLUE);
		
		c.insets = new Insets(10, 10, 10, 10);
		c.fill = GridBagConstraints.HORIZONTAL;
		
		msg = new JTextField("this is a simple message!");
		msg.setEnabled(false);
		c.weightx = 1;
		c.gridwidth = 2;
		c.ipady = 0;
		putElement(0, 0, msg);
		msg = new JTextField("this is a simple message!");
		msg.setEnabled(false);
		c.weightx = 1;
		c.gridwidth = 2;
		c.ipady = 0;
		putElement(0, 1, msg);
		putElement(0, 2, refreshButton);
		//this.remove(msg);
		
		refreshButton.addActionListener(new ButtonListener());
	}
	
	private void putElement(int x, int y, Component obj) {
		c.gridx = x;
		c.gridy = y;
		this.add(obj, c);
	}
	
	private void removeElement(Component obj) {
		this.remove(obj);
	}
	
	public class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			removeElement(msg);
			System.out.println("bonjour!");
		}
		
	}

}
