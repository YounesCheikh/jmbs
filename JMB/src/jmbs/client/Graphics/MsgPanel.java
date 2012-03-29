package jmbs.client.Graphics;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import javax.swing.JEditorPane;
import javax.swing.UIManager;

import jmbs.common.Message;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


public class MsgPanel extends JPanel {

	/**
	 * This class create a panel for any message with the user's avatar, name, time and text message
	 */
	private static final long serialVersionUID = 8331996728301748647L;
	/**
	 * new panel to show user's profile picture.
	 */
	private ImagePanel imgPanel;
	private JPanel txtPanel;
	private JEditorPane msgEditorPane;
	private JButton btnUser;
	private JLabel lblPrinttime;
	/**
	 * Create the panel.
	 */
	public MsgPanel(Message m) {
		

		setBorder(UIManager.getBorder("TitledBorder.aquaVariant"));
		
		imgPanel = new ImagePanel("./src/jmbs/client/img/avatar.jpg",69,69);
		
		
		txtPanel = new JPanel();
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(imgPanel, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtPanel, GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(txtPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
						.addComponent(imgPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
					.addGap(0))
		);
		
		msgEditorPane = new JEditorPane();
		msgEditorPane.setBackground(UIManager.getColor("CheckBox.background"));
		//msgEditorPane.setContentType("text/html");
		
		msgEditorPane.setEditable(false);
		String text = new String();
		//text+=m.getOwner().getFullName()+":\t\t"+m.getDatetime().toString()+"\n";
		text+=m.getMessage();
		msgEditorPane.setText(text);
		//msgEditorPane.setText("<html><body><strong>"+m.getOwner().getFullName()+"</strong><br />"+"<p>"+m.getMessage()+"</p><br />");
		//msgEditorPane.setText(msgEditorPane.getText()+"<p >19/03/2012 23:30:12</p>"+"</body></html>");
		
		
		btnUser = new JButton(m.getOwner().getFullName());
		btnUser.setHorizontalAlignment(SwingConstants.LEFT);
		btnUser.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		btnUser.setBorderPainted(false);
		
		
		lblPrinttime = new JLabel(m.getDatetime().toString());
		lblPrinttime.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		
		GroupLayout gl_txtPanel = new GroupLayout(txtPanel);
		gl_txtPanel.setHorizontalGroup(
			gl_txtPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_txtPanel.createSequentialGroup()
					.addComponent(btnUser)
					.addContainerGap())
				.addGroup(gl_txtPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(msgEditorPane, GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_txtPanel.createSequentialGroup()
					.addContainerGap(123, Short.MAX_VALUE)
					.addComponent(lblPrinttime)
					.addGap(19))
		);
		gl_txtPanel.setVerticalGroup(
			gl_txtPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_txtPanel.createSequentialGroup()
					.addComponent(btnUser)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(msgEditorPane)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblPrinttime))
		);
		txtPanel.setLayout(gl_txtPanel);
		
		setLayout(groupLayout);
	}
	
	public void setColors(String name) {
		ColorStyle cs = new ColorStyle(name);
		setBackground(cs.getWindowBackground()); // Color 0
		txtPanel.setBackground(cs.getWindowBackground()); // Color 0
		msgEditorPane.setForeground(cs.getFontColor()); // Color 2
		msgEditorPane.setBackground(cs.getWindowBackground()); // Color 0
		btnUser.setForeground(cs.getFontColor()); // Color 2
		btnUser.setBackground(cs.getWindowBackground()); // Color 0
		lblPrinttime.setForeground(cs.getSecondFontColor()); // Color 3
	}
}
