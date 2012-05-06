/*
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
 */

package jmbs.client.Graphics.others;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import jmbs.client.DataTreatment.FramesConf;

public class AboutFrame extends JFrame {

	/**
	 * a small frame for write something about the program developers! :-)
	 */
	private static final long serialVersionUID = 3233282767200634393L;

	/**
	 * Create the frame.
	 */
	// TODO: Set The default layout BorderLayout
	public AboutFrame() {
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		setResizable(false);
		setTitle("About JMBS");
		// setBounds(100, 100, 339, 246);
		setSize(340, 246);
		// setLocationRelativeTo(new MainWindow().getFrame());
		FramesConf.centerThisFrame(this);
		JLabel lblHiThis = new JLabel("Hi , this program is blablablablablaal");

		JLabel lblYounesBenjamin = new JLabel("Younes & Benjamin");

		JLabel lblCyounescomBbabiccom = new JLabel("cYounes.com & bBabic.com");

		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						groupLayout
								.createSequentialGroup()
								.addGap(111)
								.addComponent(lblYounesBenjamin,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE).addGap(108))
				.addGroup(
						Alignment.TRAILING,
						groupLayout
								.createSequentialGroup()
								.addGap(46)
								.addComponent(lblHiThis,
										GroupLayout.DEFAULT_SIZE, 268,
										Short.MAX_VALUE).addGap(28))
				.addGroup(
						Alignment.TRAILING,
						groupLayout
								.createSequentialGroup()
								.addGap(85)
								.addComponent(lblCyounescomBbabiccom,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE).addGap(81))
				.addGroup(
						Alignment.TRAILING,
						groupLayout.createSequentialGroup()
								.addContainerGap(136, Short.MAX_VALUE)
								.addComponent(btnOk).addGap(128)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addGap(42)
						.addComponent(lblHiThis, GroupLayout.PREFERRED_SIZE,
								51, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblYounesBenjamin).addGap(33)
						.addComponent(lblCyounescomBbabiccom)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(btnOk)
						.addContainerGap(19, Short.MAX_VALUE)));
		getContentPane().setLayout(groupLayout);

	}
}
