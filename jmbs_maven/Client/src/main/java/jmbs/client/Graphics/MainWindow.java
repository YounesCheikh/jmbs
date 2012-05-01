package jmbs.client.Graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import jmbs.client.ClientRequests;
import jmbs.client.CurrentUser;
import jmbs.client.ServerConnection;
import jmbs.client.SysConf;
import jmbs.client.Graphics.messages.MsgPanel;
import jmbs.client.Graphics.messages.NewMessagePanel;
import jmbs.client.Graphics.messages.TimeLinePanel;
import jmbs.client.Graphics.others.AboutFrame;
import jmbs.client.Graphics.others.Preferences;
import jmbs.client.Graphics.projects.PrjectTabbedPane;
import jmbs.client.Graphics.users.UsersMngmntPanel;
import jmbs.common.Message;

public class MainWindow {

	private static JFrame frmJmbsClient = null;
	private static TimeLinePanel timelinepanel;
	private static ProfilePanel ppanel;
	private static AboutFrame about;
	private static ArrayList<Message> msgListTL;
	private UsersMngmntPanel usersMngmntPanel;
	private MainMenuBar menuBar;
	private JPanel projectsPanel;
	private PrjectTabbedPane prjctTabbedPanel;
	private Preferences prfrm;
	private JPanel mainPanel;
	private JButton btnTimeline;
	private JButton btnProjects;
	private JButton btnProfile;
	private JButton btnUsers;
	private JScrollPane tlscrollPane;
	private JPanel profpanel;
	private JButton btnLogout;
	private JToolBar toolBar;
	private JSeparator separator_1;
	private JButton btnRefresh;
	private NewMessagePanel newMsgPanel;
	private JPanel tlpan;

	public Preferences getPreferencesFrame() {
		return this.prfrm;
	}

	public MainWindow() {
		if (frmJmbsClient == null) {
			initialize();
		}
	}

	public JFrame getFrame() {
		return frmJmbsClient;
	}

	public static void initFrame() {
		frmJmbsClient = null;
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		msgListTL = new ArrayList<Message>();
		frmJmbsClient = new JFrame();
		frmJmbsClient.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				frmJmbsClient.dispose();
				ClientRequests.close();
				System.exit(0);
			}
		});
		ppanel = new ProfilePanel(CurrentUser.get());
		about = new AboutFrame();
		prfrm = new Preferences();
		usersMngmntPanel = new UsersMngmntPanel();
		final ButtonGroup sideBarBtns = new ButtonGroup();
		frmJmbsClient.setTitle("JMBS Client : " + CurrentUser.getFullName());
		frmJmbsClient.setSize(520, 640);
		frmJmbsClient.setMinimumSize(new Dimension(480, 600));
		frmJmbsClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SysConf.centerThisFrame(frmJmbsClient);

		projectsPanel = new JPanel();
		prjctTabbedPanel = new PrjectTabbedPane(projectsPanel);

		profpanel = new JPanel();
		profpanel.setLayout(new BorderLayout(0, 0));

		JScrollPane profilescrollPane = new JScrollPane();
		profilescrollPane.setViewportBorder(UIManager
				.getBorder("List.evenRowBackgroundPainter"));
		profilescrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		profilescrollPane.setViewportView(ppanel);

		profpanel.add(profilescrollPane);

		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(0, 0));
		mainPanel.removeAll();
		mainPanel.updateUI();
		frmJmbsClient.getContentPane().setLayout(new BorderLayout(0, 0));
		frmJmbsClient.getContentPane().add(mainPanel);

		tlpan = new JPanel();
		mainPanel.add(tlpan, BorderLayout.CENTER);
		tlpan.setLayout(new BorderLayout(0, 0));
		timelinepanel = new TimeLinePanel();
		tlscrollPane = new JScrollPane();
		tlpan.add(tlscrollPane);
		tlscrollPane.setAutoscrolls(true);
		tlscrollPane.getVerticalScrollBar().setUnitIncrement(30);
		tlscrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		tlscrollPane.setViewportView(timelinepanel);

		newMsgPanel = new NewMessagePanel(timelinepanel);

		tlpan.add(newMsgPanel, BorderLayout.SOUTH);

		toolBar = new JToolBar();
		frmJmbsClient.getContentPane().add(toolBar, BorderLayout.WEST);
		toolBar.setBackground(Color.DARK_GRAY);
		toolBar.setRollover(true);
		toolBar.setOrientation(SwingConstants.VERTICAL);

		btnTimeline = new JButton("");
		btnTimeline.setToolTipText("TimeLine");
		toolBar.add(btnTimeline);
		btnTimeline.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnTimeline.setIcon(new ImageIcon(getClass().getResource(
						"/img/timeline.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnTimeline.setIcon(new ImageIcon(getClass().getResource(
						"/img/timeline_off.png")));
			}
		});
		btnTimeline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateMainPanel(1);
			}
		});
		btnTimeline.setBorderPainted(false);
		btnTimeline.setIcon(new ImageIcon(getClass().getResource(
				"/img/timeline_off.png")));
		btnTimeline.setSelectedIcon(new ImageIcon(getClass().getResource(
				"/img/timeline.png")));

		sideBarBtns.add(btnTimeline);
		btnTimeline.setSelected(true);
		btnProjects = new JButton("");
		toolBar.add(btnProjects);
		btnProjects.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnProjects.setIcon(new ImageIcon(getClass().getResource(
						"/img/projects.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnProjects.setIcon(new ImageIcon(getClass().getResource(
						"/img/projects_off.png")));
			}
		});

		btnProjects.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateMainPanel(2);
			}
		});
		btnProjects.setToolTipText("Projects");
		btnProjects.setBorderPainted(false);
		btnProjects.setIcon(new ImageIcon(getClass().getResource(
				"/img/projects_off.png")));
		btnProjects.setSelectedIcon(new ImageIcon(getClass().getResource(
				"/img/projects.png")));
		sideBarBtns.add(btnProjects);
		btnProfile = new JButton("");
		toolBar.add(btnProfile);
		btnProfile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnProfile.setIcon(new ImageIcon(getClass().getResource(
						"/img/profile_edit.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnProfile.setIcon(new ImageIcon(getClass().getResource(
						"/img/profile_edit_off.png")));
			}
		});

		btnProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateMainPanel(3);
			}
		});

		btnProfile.setToolTipText("Profile");
		btnProfile.setBorderPainted(false);
		btnProfile.setIcon(new ImageIcon(getClass().getResource(
				"/img/profile_edit_off.png")));
		btnProfile.setSelectedIcon(new ImageIcon(getClass().getResource(
				"/img/profile_edit.png")));
		sideBarBtns.add(btnProfile);

		btnUsers = new JButton("");
		toolBar.add(btnUsers);
		btnUsers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnUsers.setIcon(new ImageIcon(getClass().getResource(
						"/img/users.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnUsers.setIcon(new ImageIcon(getClass().getResource(
						"/img/users_off.png")));
			}
		});
		btnUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// uFrame.setVisible(true);
				updateMainPanel(4);
			}
		});
		btnUsers.setIcon(new ImageIcon(getClass().getResource(
				"/img/users_off.png")));
		btnUsers.setSelectedIcon(new ImageIcon(getClass().getResource(
				"/img/users.png")));
		btnUsers.setToolTipText("Users");
		btnUsers.setBorderPainted(false);

		JSeparator separator = new JSeparator();
		toolBar.add(separator);

		final JButton btnPreferences = new JButton("");
		toolBar.add(btnPreferences);
		btnPreferences.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnPreferences.setIcon(new ImageIcon(getClass().getResource(
						"/img/pref.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnPreferences.setIcon(new ImageIcon(getClass().getResource(
						"/img/pref_off.png")));
			}
		});
		btnPreferences.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prfrm.setVisible(true);
			}
		});
		btnPreferences.setToolTipText("Preferences");
		btnPreferences.setIcon(new ImageIcon(getClass().getResource(
				"/img/pref_off.png")));
		btnPreferences.setBorderPainted(false);

		separator_1 = new JSeparator();
		toolBar.add(separator_1);

		btnRefresh = new JButton("");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkNewMessages(timelinepanel.getLastIdMsg());
			}
		});

		btnRefresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnRefresh.setIcon(new ImageIcon(getClass().getResource(
						"/img/refreshmsgs.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnRefresh.setIcon(new ImageIcon(getClass().getResource(
						"/img/refreshmsgs_off.png")));
			}
		});
		btnRefresh.setToolTipText("Refresh");
		btnRefresh.setBorderPainted(false);
		btnRefresh.setIcon(new ImageIcon(getClass().getResource(
				"/img/refreshmsgs_off.png")));
		toolBar.add(btnRefresh);

		final JButton btnAdd = new JButton("");
		btnAdd.setToolTipText("Add New Message");
		toolBar.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateNewMsgPane();
			}
		});
		newMsgPanel.setVisible(false);
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAdd.setIcon(new ImageIcon(getClass().getResource(
						"/img/add.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAdd.setIcon(new ImageIcon(getClass().getResource(
						"/img/add_off.png")));
			}
		});
		btnAdd.setBorderPainted(false);
		btnAdd.setIcon(new ImageIcon(getClass().getResource("/img/add_off.png")));

		btnLogout = new JButton("");
		btnLogout.setToolTipText("Logout");
		toolBar.add(btnLogout);
		btnLogout.setIcon(new ImageIcon(getClass().getResource(
				"/img/logout_off.png")));
		btnLogout.setBorderPainted(false);
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenuBar.disconnect();
			}
		});
		btnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnLogout.setIcon(new ImageIcon(getClass().getResource(
						"/img/logout.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnLogout.setIcon(new ImageIcon(getClass().getResource(
						"/img/logout_off.png")));
			}
		});
		
	}

	/**
	 * used to update the timelinepanel from the other windows as
	 * NewMessageFrame
	 * 
	 * @return the default timeline Panel
	 */
	public TimeLinePanel getTLPanel() {
		return MainWindow.timelinepanel;
	}

	public void setColors(String name) {
		ColorStyle cs = new ColorStyle(name);
		ArrayList<Color> defaultColors = new ArrayList<Color>();
		defaultColors.add(frmJmbsClient.getBackground());
		defaultColors.add(frmJmbsClient.getContentPane().getBackground());
		defaultColors.add(frmJmbsClient.getForeground());
		defaultColors.add(frmJmbsClient.getContentPane().getForeground());
		cs.setDefaultColors(defaultColors);
		frmJmbsClient.setVisible(false);
		frmJmbsClient.setBackground(cs.getWindowBackground()); // color 0
		frmJmbsClient.getContentPane().setBackground(cs.getWindowBackground()); // Color
																				// 0
		timelinepanel.setBackground(cs.getTimeLineBackground()); // Color 1
		// tabbedPane.setBackground(cs.getWindowBackground()); // Color 0
		// tlpanel.setBackground(cs.getWindowBackground()); // Color 0
		for (Component c : timelinepanel.getComponents()) {
			((MsgPanel) c).setColors(name);
		}
		tlpan.add(newMsgPanel, BorderLayout.SOUTH);
		frmJmbsClient.setVisible(true);
	}

	public NewMessagePanel getNmPanel() {
		return newMsgPanel;
	}

	public AboutFrame getAbout() {
		return about;
	}

	public ArrayList<Message> getMsgListTL() {
		return msgListTL;
	}

	public void initMsgListTL() {
		msgListTL.clear();
	}

	public MainMenuBar getMenuBar() {
		return menuBar;
	}

	public void resetProfilePanel() {
		ppanel.resetAll(CurrentUser.get());
	}

	public void setMenuBar() {
		menuBar = new MainMenuBar(this);
		frmJmbsClient.setJMenuBar(menuBar);
	}

	public JPanel getProjectsPanel() {
		return projectsPanel;
	}

	public void checkNewMessages(int idLastMsg) {
		msgListTL = ClientRequests.getLatestTL(CurrentUser.getId(), idLastMsg,
				ServerConnection.maxReceivedMsgs);
		timelinepanel.putList(msgListTL);
	}

	public void updateMainPanel(int sideBarItem) {
		mainPanel.removeAll();
		if (sideBarItem == 1)
			mainPanel.add(tlpan, BorderLayout.CENTER);
		else if (sideBarItem == 2)
			mainPanel.add(prjctTabbedPanel, BorderLayout.CENTER);
		else if (sideBarItem == 3)
			mainPanel.add(profpanel, BorderLayout.CENTER);
		else if (sideBarItem == 4)
			mainPanel.add(usersMngmntPanel, BorderLayout.CENTER);
		btnTimeline.setSelected(sideBarItem == 1);
		btnProjects.setSelected(sideBarItem == 2);
		btnProfile.setSelected(sideBarItem == 3);
		btnUsers.setSelected(sideBarItem == 4);
		mainPanel.updateUI();
	}

	private void showNewMsgPan() {
		newMsgPanel.setVisible(true);
	}

	private void hideNewMsgPan() {
		newMsgPanel.setVisible(false);
	}

	public void updateNewMsgPane() {
		if (newMsgPanel.isVisible()) {
			hideNewMsgPan();
		} else {
			showNewMsgPan();
		}
	}

}
