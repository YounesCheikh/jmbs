package jmbs.client.Graphics;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import jmbs.client.SysConf;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SayToUser extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9145407301854671794L;
	private final JPanel contentPanel = new JPanel();
	private JButton cancelButton;

	/**
	 * Create the dialog.
	 */
	public SayToUser(String msg, boolean error) {
		if(error) {
		setTitle("error");
		}
		else {
			setTitle("Message from server:");
		}
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		//setBounds(100, 100, 360, 160);
		setSize(360,160);
		new SysConf().centerThisDialog(this);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblNewLabel = new JLabel(msg);
		
		if (error) {
			lblNewLabel.setIcon(new ImageIcon(SayToUser.class.getResource("/com/sun/java/swing/plaf/gtk/resources/gtk-dialog-error-6.png")));
		} else {
			lblNewLabel.setIcon(new ImageIcon(SayToUser.class.getResource("/com/sun/java/swing/plaf/gtk/resources/gtk-yes-4.png")));
		}

		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPanel.createSequentialGroup().addGap(31).addComponent(lblNewLabel).addContainerGap(85, Short.MAX_VALUE)));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup().addContainerGap(36, Short.MAX_VALUE).addComponent(lblNewLabel).addGap(21)));
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				cancelButton = new JButton("Okay!");
				cancelButton.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						dispose();
					}
				});
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
			}
			GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
			gl_buttonPane.setHorizontalGroup(gl_buttonPane.createParallelGroup(Alignment.LEADING).addGroup(gl_buttonPane.createSequentialGroup().addGap(137).addComponent(cancelButton).addContainerGap(137, Short.MAX_VALUE)));
			gl_buttonPane.setVerticalGroup(gl_buttonPane.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, gl_buttonPane.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(cancelButton)));
			buttonPane.setLayout(gl_buttonPane);
		}
		setVisible(true);
	}

}
