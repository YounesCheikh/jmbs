package jmbs.client.Graphics;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegistrationSuccessed extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7275874515837648992L;
	private final JPanel contentPanel = new JPanel();
	private JButton okButton;

	/**
	 * Create the dialog.
	 */
	public RegistrationSuccessed(String userName) {
		setLocationRelativeTo(null);
		setBounds(100, 100, 450, 203);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		ImagePanel panel = new ImagePanel("./src/jmbs/client/img/success.png");
		JLabel lblCongrat = new JLabel("Congratulations!");
		lblCongrat.setFont(new Font("Comic Sans MS", Font.PLAIN, 23));
		lblCongrat.setForeground(new Color(0, 102, 0));
		JLabel lblThanks = new JLabel("Thanks "+userName+"! your registration has been ");
		JLabel lblSecondeline = new JLabel("successful. Now you can login using your email");
		JLabel lblThirdline = new JLabel("and password.");
		{
			okButton = new JButton("OK");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
				}
			});
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCongrat)
								.addComponent(lblSecondeline)
								.addComponent(lblThirdline)
								.addComponent(lblThanks)))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(180)
							.addComponent(okButton)))
					.addContainerGap(9, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblCongrat)
							.addGap(18)
							.addComponent(lblThanks, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblSecondeline)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblThirdline)
							.addGap(18)
							.addComponent(okButton)
							.addContainerGap(7, Short.MAX_VALUE))))
		);
		contentPanel.setLayout(gl_contentPanel);
	}

}
