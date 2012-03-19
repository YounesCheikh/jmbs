package jmbs.client.Graphics;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MainWindow {

	private JFrame frmJmbsClient;
	private TimeLinePanel timelinepanel;
	private ProfilePanel ppanel ;
	private NewMessageFrame nmFrame;
	private AboutFrame about;

	/**
	 * Create the application.
	 * TODO: create a new class for the menubar
	 */
	public MainWindow() {
		timelinepanel = new TimeLinePanel();
		ppanel = new ProfilePanel();
		nmFrame = new NewMessageFrame(timelinepanel);
		about = new AboutFrame();
		initialize();
	}

	public JFrame getFrame() {
		return frmJmbsClient;
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmJmbsClient = new JFrame();
		frmJmbsClient.setTitle("JMBS Client");
		frmJmbsClient.setBounds(100, 100, 365, 600);
		frmJmbsClient.setMinimumSize(new Dimension(365, 560));
		frmJmbsClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frmJmbsClient.setVisible(true);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.LIGHT_GRAY);
		frmJmbsClient.setJMenuBar(menuBar);;
		//frmJmbsClient.getContentPane().add(menuBar, BorderLayout.NORTH);
		
		JMenu mnJmbs = new JMenu("JMBS");
		mnJmbs.setBackground(Color.LIGHT_GRAY);
		menuBar.add(mnJmbs);
		
		JMenuItem mntmNewMessage = new JMenuItem("New Message");
		mntmNewMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Open the new Frame to write a new message
				nmFrame.setVisible(true);
			}
		});
		mnJmbs.add(mntmNewMessage);
		
		JSeparator separator_4 = new JSeparator();
		mnJmbs.add(separator_4);
		
		JMenuItem mntmAboutJmbs = new JMenuItem("About JMBS");
		mntmAboutJmbs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				about.setVisible(true);
			}
		});
		mnJmbs.add(mntmAboutJmbs);
		
		JSeparator separator = new JSeparator();
		mnJmbs.add(separator);
		
		JMenuItem mntmPreferences = new JMenuItem("Preferences");
		mnJmbs.add(mntmPreferences);
		
		JMenuItem mntmEmptyCache = new JMenuItem("Empty cache");
		mntmEmptyCache.setEnabled(false);
		mnJmbs.add(mntmEmptyCache);
		
		JSeparator separator_2 = new JSeparator();
		mnJmbs.add(separator_2);
		
		JMenuItem mntmHide = new JMenuItem("Hide");
		mntmHide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmJmbsClient.setState(JFrame.ICONIFIED);
			}
		});
		mnJmbs.add(mntmHide);
		
		JMenuItem mntmDisconnect = new JMenuItem("Disconnect");
		mntmDisconnect.setEnabled(false);
		mnJmbs.add(mntmDisconnect);
		
		JSeparator separator_1 = new JSeparator();
		mnJmbs.add(separator_1);
		
		JMenuItem mntmQuit = new JMenuItem("Quit");
		mntmQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnJmbs.add(mntmQuit);
		
		JMenu mnProjects = new JMenu("Projects");
		mnProjects.setEnabled(false);
		mnProjects.setBackground(Color.LIGHT_GRAY);
		menuBar.add(mnProjects);
		
		JMenuItem mntmCreateNewProject = new JMenuItem("Create new");
		mnProjects.add(mntmCreateNewProject);
		
		JMenuItem mntmSearchProject = new JMenuItem("Search project");
		mnProjects.add(mntmSearchProject);
		
		JMenuItem mntmBrowseAll = new JMenuItem("Browse all");
		mnProjects.add(mntmBrowseAll);
		
		JMenuItem mntmMyProjects = new JMenuItem("My Projects");
		mnProjects.add(mntmMyProjects);
		
		JMenu mnUsers = new JMenu("Users");
		mnUsers.setBackground(Color.LIGHT_GRAY);
		menuBar.add(mnUsers);
		
		JMenuItem mntmSearch = new JMenuItem("Search");
		mntmSearch.setEnabled(false);
		mnUsers.add(mntmSearch);
		
		JMenuItem mntmFollowing = new JMenuItem("Following");
		mnUsers.add(mntmFollowing);
		
		JMenuItem mntmFollowers = new JMenuItem("Followers");
		mnUsers.add(mntmFollowers);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.BELOW_BOTTOM, null, null));
		tabbedPane.setToolTipText("JMBS");
		frmJmbsClient.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel tlpanel = new JPanel();
		tlpanel.setLayout(new BorderLayout(0, 0));
		tlpanel.setToolTipText("TimeLine");
		tabbedPane.addTab("TimeLine", null, tlpanel, null);
		
		JScrollPane tlscrollPane = new JScrollPane();
		tlscrollPane.setViewportBorder(UIManager
				.getBorder("InsetBorder.aquaVariant"));
		tlscrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		tlscrollPane.setViewportView(timelinepanel);
		tlpanel.add(tlscrollPane);
		
		JPanel profpanel = new JPanel();
		tabbedPane.addTab("Profile", null, profpanel, null);
		profpanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane profilescrollPane = new JScrollPane();
		profilescrollPane.setViewportBorder(UIManager
				.getBorder("InsetBorder.aquaVariant"));
		profilescrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		profilescrollPane.setViewportView(ppanel);
		
		profpanel.add(profilescrollPane);
	}
	
	/**
	 * used to update the timelinepanel from the other windows as NewMessageFrame
	 * @return the default timeline Panel
	 */
	public TimeLinePanel getTLPanel() {
		return this.timelinepanel;
	}
}
