/**
 * 
 */
package jmbs.client.DataProcessing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import jmbs.client.Graphics.MainWindow;
import jmbs.client.Graphics.messages.TimeLinePanel;
import jmbs.client.Graphics.projects.ParticipationsPrjcstPanel;
import jmbs.client.Graphics.projects.PrjctsTimeLinePanel;
import jmbs.client.Graphics.users.UsersMngmntPanel;

/**
 * @author ycheikh
 * 
 */
public class AutoRefresh {
	public AutoRefresh() {

	}

	public void timeLineRefresh(int delay) {
		Timer timer = new Timer(delay * 1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindow.checkNewMessages(TimeLinePanel.idLastMessage);
			}
		});
		timer.start();
	}

	public void prjctsTimeLineRefresh(int delay) {
		Timer timer = new Timer(delay * 1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PrjctsTimeLinePanel.checkNewMessages();
			}
		});
		timer.start();
	}

	public void participationsRefresh(int delay) {
		Timer timer = new Timer(delay * 1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ParticipationsPrjcstPanel.updateList();
			}
		});
		timer.start();
	}

	public void followingRefresh(int delay) {
		Timer timer = new Timer(delay * 1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UsersMngmntPanel.updateFollowingList();
			}
		});
		timer.start();
	}

	public void followersRefresh(int delay) {
		Timer timer = new Timer(delay * 1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UsersMngmntPanel.updateFollowersList();
			}
		});
		timer.start();
	}

}
