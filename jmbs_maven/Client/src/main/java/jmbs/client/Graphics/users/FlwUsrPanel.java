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

package jmbs.client.Graphics.users;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import jmbs.client.ClientRequests;
import jmbs.client.CurrentUser;
import jmbs.client.Graphics.images.ImagePanel;
import jmbs.common.User;

public class FlwUsrPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5508008556101716116L;

	/**
	 * Create the panel.
	 */
	public FlwUsrPanel(final User u) {
		setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));

		ImagePanel panel = new ImagePanel(ClientRequests.convert(u.getPic()), 70, 70);
		panel.setPreferredSize(new Dimension(70, 70));
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);

		JLabel lblUserName = new JLabel(u.getFullName());
		lblUserName.setHorizontalAlignment(SwingConstants.LEFT);
		lblUserName.setFont(new Font("Lucida Grande", Font.BOLD, 14));

		setLayout(new BorderLayout(0, 0));
		add(panel, BorderLayout.WEST);
		add(lblUserName, BorderLayout.CENTER);

		final JLabel btnFollow = new JLabel("");
		btnFollow.setHorizontalAlignment(SwingConstants.CENTER);
		//btnFollow.setFont(new Font("Lucida Grande", Font.BOLD, 13));

		btnFollow.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (!CurrentUser.get().equals(u)) {
					if (CurrentUser.getFollows().contains(u)) {
						btnFollow.setIcon(new ImageIcon(getClass().getResource(
								"/img/u_unfollow.png")));
						btnFollow.setToolTipText("unfollow");
					} else {
						btnFollow.setIcon(new ImageIcon(getClass().getResource(
								"/img/u_follow.png")));
						btnFollow.setToolTipText("follow");
					}
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (!CurrentUser.get().equals(u)) {
					if (!CurrentUser.getFollows().contains(u)) {
						btnFollow.setIcon(new ImageIcon(getClass().getResource(
								"/img/u_follow_off.png")));
						btnFollow.setToolTipText("following");
					} else {
						btnFollow.setIcon(new ImageIcon(getClass().getResource(
								"/img/u_following.png")));
						btnFollow.setToolTipText("following");
					}
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (!CurrentUser.get().equals(u)) {
					if (!CurrentUser.getFollows().contains(u)) {
						ClientRequests.follows(CurrentUser.getId(), u.getId());
						CurrentUser.getFollows().add(u);
						btnFollow.setIcon(new ImageIcon(getClass().getResource(
								"/img/u_following.png")));
						btnFollow.setToolTipText("following");
					} else {
						ClientRequests.unFollow(CurrentUser.getId(), u.getId());
						CurrentUser.getFollows().remove(u);
						btnFollow.setIcon(new ImageIcon(getClass().getResource(
								"/img/u_follow_off.png")));
						btnFollow.setToolTipText("follow");
					}
					UsersMngmntPanel.updateFollowingList();
				}
			}
		});

		if (CurrentUser.getFollows().contains(u)) {
			btnFollow.setIcon(new ImageIcon(getClass().getResource(
					"/img/u_following.png")));
			//btnFollow.setText("following");
		} else {
			if (CurrentUser.get().equals(u)) {
				btnFollow.setEnabled(false);
				//btnFollow.setText("");
			} else {
				btnFollow.setIcon(new ImageIcon(getClass().getResource(
						"/img/u_follow_off" + ".png")));
				btnFollow.setToolTipText("follow");
			}
		}
		add(btnFollow, BorderLayout.EAST);
		// add(tglbtnFollow, BorderLayout.EAST);

	}
}
