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

package jmbs.client.Graphics.messages;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;

import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import jmbs.client.ClientRequests;
import jmbs.client.Graphics.ColorStyle;
import jmbs.client.Graphics.images.ImagePanel;
import jmbs.client.Graphics.users.ShowUserProfileFrm;
import jmbs.common.Message;

public class MsgPanel extends JPanel {

	/**
	 * This class create a panel for any message with the user's avatar, name,
	 * time and text message
	 */
	private static final long serialVersionUID = 8331996728301748647L;
	/**
	 * new panel to show user's profile picture.
	 */
	private ImagePanel imgPanel;
	private JPanel txtPanel;
	private JEditorPane msgEditorPane;
	private JLabel btnUser;
	private JLabel lblPrinttime;
	private JPanel panel;

	/**
	 * Create the panel.
	 */
	public MsgPanel(final Message m) {

		setBorder(UIManager.getBorder("InsetBorder.aquaVariant"));

		this.setPreferredSize(new Dimension(10, 10));
		imgPanel = new ImagePanel(
				ClientRequests.convert(m.getOwner().getPic()), 69, 69);
		imgPanel.setPreferredSize(new Dimension(70, 70));
		imgPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		txtPanel = new JPanel();
		txtPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		msgEditorPane = new JEditorPane();
		msgEditorPane.setBackground(UIManager.getColor("CheckBox.background"));
		// msgEditorPane.setContentType("text/html");

		msgEditorPane.setEditable(false);
		String text = new String();
		// text+=m.getOwner().getFullName()+":\t\t"+m.getDatetime().toString()+"\n";
		text += m.getMessage();
		msgEditorPane.setText(text);
		// msgEditorPane.setText("<html><body><strong>"+m.getOwner().getFullName()+"</strong><br />"+"<p>"+m.getMessage()+"</p><br />");
		// msgEditorPane.setText(msgEditorPane.getText()+"<p >19/03/2012 23:30:12</p>"+"</body></html>");

		btnUser = new JLabel(m.getOwner().getFullName());
		btnUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new ShowUserProfileFrm(m.getOwner());
			}
		});
		btnUser.setHorizontalAlignment(SwingConstants.LEFT);
		btnUser.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		setLayout(new BorderLayout(0, 0));
		add(imgPanel, BorderLayout.WEST);
		add(txtPanel);
		txtPanel.setLayout(new BorderLayout(0, 0));
		txtPanel.add(btnUser, BorderLayout.NORTH);
		txtPanel.add(msgEditorPane);

		panel = new JPanel();
		panel.setBorder(new EmptyBorder(10, 0, 0, 0));
		txtPanel.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));

		lblPrinttime = new JLabel(
				new SimpleDateFormat("dd/MM/yyyy HH:mm").format(m
						.getTimestamp()));
		panel.add(lblPrinttime, BorderLayout.EAST);
		lblPrinttime.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
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
