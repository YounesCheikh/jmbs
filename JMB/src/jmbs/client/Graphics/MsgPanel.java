package jmbs.client.Graphics;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import javax.swing.JEditorPane;
import javax.swing.UIManager;

import jmbs.common.Message;


public class MsgPanel extends JPanel {

	/**
	 * This class create a panel for any message with the user's avatar, name, time and text message
	 */
	private static final long serialVersionUID = 8331996728301748647L;
	/**
	 * new panel to show user's profile picture.
	 */
	private ImagePanel imgPanel;
	/**
	 * Create the panel.
	 */
	public MsgPanel(Message m) {

		setBorder(UIManager.getBorder("TitledBorder.aquaVariant"));
		
		imgPanel = new ImagePanel("./src/jmbs/client/img/avatar.jpg",69,69);
		
		
		JPanel txtPanel = new JPanel();
		txtPanel.setBackground(UIManager.getColor("Spinner.background"));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(imgPanel, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtPanel, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(txtPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(imgPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE))
					.addGap(0))
		);
		
		JEditorPane msgEditorPane = new JEditorPane();
		msgEditorPane.setEditable(false);
		msgEditorPane.setContentType("text/html");
		String text = new String();
		text+="<html><body><strong>"+m.getOwner().getFullName()+"</strong><br />"+"<p>"+m.getMessage()+"</p>";
		text+="<p style=\"color:#555555; font-size:8px; text-align:right;\" >"+m.getDatetime().toString()+"</p>"+"</body></html>";
		msgEditorPane.setText(text);
		//msgEditorPane.setText("<html><body><strong>"+m.getOwner().getFullName()+"</strong><br />"+"<p>"+m.getMessage()+"</p><br />");
		//msgEditorPane.setText(msgEditorPane.getText()+"<p >19/03/2012 23:30:12</p>"+"</body></html>");
		msgEditorPane.setBackground(UIManager.getColor("Button.background"));
		GroupLayout gl_txtPanel = new GroupLayout(txtPanel);
		gl_txtPanel.setHorizontalGroup(
			gl_txtPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_txtPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(msgEditorPane, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_txtPanel.setVerticalGroup(
			gl_txtPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_txtPanel.createSequentialGroup()
					.addGap(5)
					.addComponent(msgEditorPane, GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
					.addContainerGap())
		);
		txtPanel.setLayout(gl_txtPanel);
		
		setLayout(groupLayout);
		

	}
}