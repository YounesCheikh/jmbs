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

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import jmbs.client.SysConf;

public class SayToUser {

	private final static JPanel contentPanel = new JPanel();
	private JButton cancelButton;
	private static JDialog dialog;
	private static JLabel lblTitleLabel;
	private static JEditorPane textArea;
	private static ImagePanel panel;

	/**
	 * Create the dialog.
	 */
	public SayToUser() {
		dialog = new JDialog();
		dialog.setAlwaysOnTop(true);
		dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dialog.setResizable(false);
		dialog.setSize(460, 220);
		new SysConf().centerThisDialog(dialog);
		dialog.getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 460, 163);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		dialog.getContentPane().add(contentPanel);

		panel = new ImagePanel("/img/warning_ico.png");
		panel.setBounds(26, 17, 43, 39);
		panel.setBackground(Color.WHITE);

		lblTitleLabel = new JLabel("Title of message");
		lblTitleLabel.setBounds(81, 28, 368, 20);
		lblTitleLabel.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		contentPanel.setLayout(null);

		textArea = new JEditorPane();
		textArea.setContentType("text/html");
		textArea.setBackground(UIManager.getColor("Button.background"));
		// textArea.setBounds(11, 68, 438, 90);
		textArea.setEditable(false);
		// contentPanel.add(textArea);
		contentPanel.add(panel);
		contentPanel.add(lblTitleLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 65, 448, 92);
		contentPanel.add(scrollPane);

		scrollPane.setViewportView(textArea);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 163, 460, 35);
			dialog.getContentPane().add(buttonPane);
			{
				cancelButton = new JButton("Okay!");
				cancelButton.setBounds(182, 6, 80, 29);
				cancelButton.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						dialog.dispose();
					}
				});
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dialog.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
			}
			buttonPane.setLayout(null);
			buttonPane.add(cancelButton);
		}

	}

	public static void error(String title, String message) {
		dialog.setTitle("error: " + title);
		contentPanel.remove(panel);
		panel = new ImagePanel("/img/error_ico.png");
		panel.setBounds(26, 17, 43, 39);
		contentPanel.add(panel);
		contentPanel.updateUI();
		lblTitleLabel.setText(title);
		textArea.setText(message);
		dialog.setVisible(true);
	}

	public static void warning(String title, String message) {
		dialog.setTitle("Warning: " + title);
		contentPanel.remove(panel);
		panel = new ImagePanel("/img/warning_ico.png");
		panel.setBounds(26, 17, 43, 39);
		contentPanel.add(panel);
		contentPanel.updateUI();
		lblTitleLabel.setText(title);
		textArea.setText(message);
		dialog.setVisible(true);
	}

	public static void success(String title, String message) {
		dialog.setTitle("Success: " + title);
		contentPanel.remove(panel);
		panel = new ImagePanel("/img/success_ico.png");
		panel.setBounds(26, 17, 43, 39);
		contentPanel.add(panel);
		contentPanel.updateUI();
		lblTitleLabel.setText(title);
		textArea.setText(message);
		dialog.setVisible(true);
	}
}
