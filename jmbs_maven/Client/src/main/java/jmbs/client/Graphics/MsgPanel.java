/**
 * JMBS: Java Micro Blogging System
 *
 * Copyright (C) 2012  
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY.
 * See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * @author Younes CHEIKH http://cyounes.com
 * @author Benjamin Babic http://bbabic.com
 * 
 */

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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


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
	public MsgPanel(final Message m) {

		setBorder(UIManager.getBorder("TitledBorder.aquaVariant"));
		
		imgPanel = new ImagePanel("/img/avatar.jpg",69,69);
		
		
		txtPanel = new JPanel();
		
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
		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ShowUserProfileFrm(m.getOwner());
			}
		});
		btnUser.setHorizontalAlignment(SwingConstants.LEFT);
		btnUser.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		btnUser.setBorderPainted(false);
		
		
		lblPrinttime = new JLabel(m.getTimestamp().toString());
		lblPrinttime.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		
		GroupLayout gl_txtPanel = new GroupLayout(txtPanel);
		gl_txtPanel.setHorizontalGroup(
			gl_txtPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_txtPanel.createSequentialGroup()
					.addComponent(btnUser)
					.addContainerGap())
				.addGroup(gl_txtPanel.createSequentialGroup()
					.addContainerGap(124, Short.MAX_VALUE)
					.addComponent(lblPrinttime)
					.addGap(19))
				.addGroup(gl_txtPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(msgEditorPane, GroupLayout.PREFERRED_SIZE, 230, Short.MAX_VALUE)
					.addGap(8))
		);
		gl_txtPanel.setVerticalGroup(
			gl_txtPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_txtPanel.createSequentialGroup()
					.addComponent(btnUser)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(msgEditorPane, GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
					.addGap(6)
					.addComponent(lblPrinttime))
		);
		txtPanel.setLayout(gl_txtPanel);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(imgPanel, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtPanel, GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(16)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(txtPanel, GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(imgPanel, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
							.addGap(9))))
		);
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
