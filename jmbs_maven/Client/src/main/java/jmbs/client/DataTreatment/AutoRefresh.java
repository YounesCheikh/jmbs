/**
 * 
 */
package jmbs.client.DataTreatment;

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
	private static Timer timeLine ;
	private static Timer prjctTimeline;
	private static Timer participations;
	private static Timer following;
	private static Timer followers;
	public AutoRefresh() {

	}

	public void timeLineRefresh(int delay) {
		timeLine = new Timer(delay * 1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindow.checkNewMessages(TimeLinePanel.idLastMessage);
			}
		});
		timeLine.start();
	}

	public void prjctsTimeLineRefresh(int delay) {
		prjctTimeline = new Timer(delay * 1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PrjctsTimeLinePanel.checkNewMessages();
			}
		});
		prjctTimeline.start();
	}

	public void participationsRefresh(int delay) {
		participations = new Timer(delay * 1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ParticipationsPrjcstPanel.updateList();
			}
		});
		participations.start();
	}

	public void followingRefresh(int delay) {
		following= new Timer(delay * 1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UsersMngmntPanel.updateFollowingList();
			}
		});
		following.start();
	}

	public void followersRefresh(int delay) {
		followers = new Timer(delay * 1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UsersMngmntPanel.updateFollowersList();
			}
		});
		followers.start();
	}
	
	public static void stopRefreshTimeline(){
		timeLine.stop();
	}
	
	public static void stopRefreshPrjctTL(){
		prjctTimeline.stop();
	}
	
	public static void stopRefreshParticipations() {
		participations.stop();
	}
	
	public static void stopRefreshFollowing(){
		following.stop();
	}
	
	public static void stopRefreshFollowers(){
		followers.stop();
	}
	
	public static void stopAll() {
		stopRefreshFollowers();
		stopRefreshFollowing();
		stopRefreshParticipations();
		stopRefreshPrjctTL();
		stopRefreshTimeline();
	}

}
