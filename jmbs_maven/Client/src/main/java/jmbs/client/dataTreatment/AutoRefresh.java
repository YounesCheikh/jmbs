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

package jmbs.client.dataTreatment;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import jmbs.client.gui.MainWindow;
import jmbs.client.gui.messages.TimeLinePanel;
import jmbs.client.gui.projects.ParticipationsPrjcstPanel;
import jmbs.client.gui.projects.PrjctsTimeLinePanel;
import jmbs.client.gui.users.UsersMngmntPanel;

/**
 * Refresh the different panels in the Client Application <br />
 * Refreshing TimeLine and (Following , Followers) lists <br />
 * @author ycheikh
 */
public class AutoRefresh {
	private static Timer timeLine ;
	private static Timer prjctTimeline;
	private static Timer participations;
	private static Timer following;
	private static Timer followers;
	private MainWindow w;
	
        /**
         * @param w MainWindow that contains the TimeLine Panel
         */
	public AutoRefresh(MainWindow w) {
		this.w = w;
	}

        /**
         * Refresh the TimeLine panel and requests new messages from server
         * @param delay delay in seconds
         */
	public void timeLineRefresh(int delay) {
		timeLine = new Timer(delay * 1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				w.checkNewMessages(TimeLinePanel.idLastMessage);
			}
		});
		timeLine.start();
	}

        /**
         * refresh Projects lists after the specified delay 
         * @param delay delay in seconds
         */
	public void prjctsTimeLineRefresh(int delay) {
		prjctTimeline = new Timer(delay * 1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PrjctsTimeLinePanel.checkNewMessages();
			}
		});
		prjctTimeline.start();
	}

        /**
         * Refresh user's participations in projects after a specified delay
         * @param delay delay to refresh in seconds
         */
	public void participationsRefresh(int delay) {
		participations = new Timer(delay * 1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ParticipationsPrjcstPanel.updateList();
			}
		});
		participations.start();
	}

        /**
         * refresh the following user's list after a a specified delay
         * @param delay delay in seconds
         */
	public void followingRefresh(int delay) {
		following= new Timer(delay * 1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UsersMngmntPanel.updateFollowingList();
			}
		});
		following.start();
	}

        /**
         * refresh the user's followers list after a specified delay
         * @param delay delay in seconds
         */
	public void followersRefresh(int delay) {
		followers = new Timer(delay * 1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UsersMngmntPanel.updateFollowersList();
			}
		});
		followers.start();
	}
	
        /**
         * stop the timer that refreshing the TimeLine
         */
	public static void stopRefreshTimeline(){
		timeLine.stop();
	}
	
        /**
         * stop the time that refreshing the projects timeline 
         */
	public static void stopRefreshPrjctTL(){
		prjctTimeline.stop();
	}
	
        /**
         * stop the timer that refreshing the participations in project
         */
	public static void stopRefreshParticipations() {
		participations.stop();
	}
	
        /**
         * Stop the Timer that refreshing the user's following list
         */
	public static void stopRefreshFollowing(){
		following.stop();
	}
	
        /**
         * Stop the Timer that refreshing the user's followers list
         */
	public static void stopRefreshFollowers(){
		followers.stop();
	}
	
        /**
         * Stop All timers
         */
	public static void stopAll() {
		stopRefreshFollowers();
		stopRefreshFollowing();
		stopRefreshParticipations();
		stopRefreshPrjctTL();
		stopRefreshTimeline();
	}

}
