package jmbs.client.Graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;

import jmbs.client.ClientRequests;
import jmbs.client.CurrentUser;
import jmbs.client.ServerConnection;
import jmbs.client.SysConf;
import jmbs.client.Graphics.projects.PrjectTabbedPane;
import jmbs.common.Message;
import javax.swing.JSeparator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow {

	private static JFrame frmJmbsClient = null;
	private static TimeLinePanel timelinepanel;
	private static ProfilePanel ppanel;
	private static NewMessageFrame nmFrame;
	private static AboutFrame about;
	private static UsersFrame uFrame;
	private static ArrayList<Message> msgListTL;
	private MainMenuBar menuBar;
	private JPanel projectsPanel;
	private PrjectTabbedPane prjctTabbedPanel;
	private Preferences prfrm;
	private JPanel mainPanel;
	private JButton btnTimeline;
	private JButton btnProjects;
	private JButton btnProfile;
	private JScrollPane tlscrollPane;
	private JPanel profpanel;

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
		uFrame = new UsersFrame();
		prfrm = new Preferences();
		final ButtonGroup sideBarBtns = new ButtonGroup();
		frmJmbsClient.setTitle("JMBS Client : " + CurrentUser.getFullName());
		frmJmbsClient.setSize(520, 640);
		frmJmbsClient.setMinimumSize(new Dimension(480, 600));
		frmJmbsClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		new SysConf().centerThisFrame(frmJmbsClient);
		timelinepanel = new TimeLinePanel();
		nmFrame = new NewMessageFrame(timelinepanel);
		tlscrollPane = new JScrollPane();
		tlscrollPane.setAutoscrolls(true);
		tlscrollPane.getVerticalScrollBar().setUnitIncrement(30);
		tlscrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		tlscrollPane.setViewportView(timelinepanel);

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

		JPanel sidebarPanel = new JPanel();

		mainPanel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(
				frmJmbsClient.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(sidebarPanel, GroupLayout.PREFERRED_SIZE,
								75, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, 427,
								Short.MAX_VALUE).addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.TRAILING).addGroup(
				Alignment.LEADING,
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								groupLayout
										.createParallelGroup(Alignment.LEADING)
										.addComponent(sidebarPanel,
												GroupLayout.DEFAULT_SIZE, 606,
												Short.MAX_VALUE)
										.addComponent(mainPanel,
												GroupLayout.DEFAULT_SIZE, 606,
												Short.MAX_VALUE))
						.addContainerGap()));
		mainPanel.setLayout(new BorderLayout(0, 0));

		btnTimeline = new JButton("");
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
		btnProjects = new JButton("");
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
		btnProfile = new JButton("");
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
		
		JButton btnAdd = new JButton("");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getNmFrame().setVisible(true);
			}
		});
		btnAdd.setBorderPainted(false);
		btnAdd.setIcon(new ImageIcon(getClass().getResource("/img/add.png")));

		final JButton btnUsers = new JButton("");
		btnUsers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnUsers.setIcon(new ImageIcon(getClass().getResource("/img/users.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnUsers.setIcon(new ImageIcon(getClass().getResource("/img/users_off.png")));
			}
		});
		btnUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				uFrame.setVisible(true);
			}
		});
		btnUsers.setIcon(new ImageIcon(getClass().getResource("/img/users_off.png")));
		btnUsers.setToolTipText("Users");
		btnUsers.setBorderPainted(false);

		JSeparator separator = new JSeparator();

		final JButton btnPreferences = new JButton("");
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
		GroupLayout gl_sidebarPanel = new GroupLayout(sidebarPanel);
		gl_sidebarPanel
				.setHorizontalGroup(gl_sidebarPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_sidebarPanel
										.createSequentialGroup()
										.addGroup(
												gl_sidebarPanel
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																btnTimeline,
																GroupLayout.DEFAULT_SIZE,
																69,
																Short.MAX_VALUE)
														.addComponent(
																btnAdd,
																GroupLayout.DEFAULT_SIZE,
																69,
																Short.MAX_VALUE)
														.addComponent(
																btnProjects,
																GroupLayout.DEFAULT_SIZE,
																69,
																Short.MAX_VALUE)
														.addComponent(
																btnProfile,
																GroupLayout.DEFAULT_SIZE,
																69,
																Short.MAX_VALUE)
														.addComponent(
																separator,
																Alignment.TRAILING,
																GroupLayout.DEFAULT_SIZE,
																69,
																Short.MAX_VALUE)
														.addGroup(
																Alignment.TRAILING,
																gl_sidebarPanel
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				btnUsers,
																				GroupLayout.DEFAULT_SIZE,
																				69,
																				Short.MAX_VALUE))
														.addGroup(
																gl_sidebarPanel
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				btnPreferences)))
										.addContainerGap()));
		gl_sidebarPanel.setVerticalGroup(gl_sidebarPanel.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_sidebarPanel
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(btnTimeline, GroupLayout.PREFERRED_SIZE,
								64, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnProjects, GroupLayout.PREFERRED_SIZE,
								52, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnProfile)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(separator, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnUsers, GroupLayout.PREFERRED_SIZE, 68,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnPreferences)
						.addPreferredGap(ComponentPlacement.RELATED, 241,
								Short.MAX_VALUE).addComponent(btnAdd)));
		sidebarPanel.setLayout(gl_sidebarPanel);
		frmJmbsClient.getContentPane().setLayout(groupLayout);

		sideBarBtns.add(btnTimeline);
		sideBarBtns.add(btnProjects);
		sideBarBtns.add(btnProfile);
		mainPanel.removeAll();
		mainPanel.add(tlscrollPane, BorderLayout.CENTER);
		mainPanel.updateUI();
		btnTimeline.setSelected(true);
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
		frmJmbsClient.setVisible(true);
	}

	public NewMessageFrame getNmFrame() {
		return nmFrame;
	}

	public AboutFrame getAbout() {
		return about;
	}

	public UsersFrame getuFrame() {
		return uFrame;
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
		uFrame = new UsersFrame(); // TODO: Provisoir !
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
			mainPanel.add(tlscrollPane, BorderLayout.CENTER);
		else if (sideBarItem == 2)
			mainPanel.add(prjctTabbedPanel, BorderLayout.CENTER);
		else if (sideBarItem == 3)
			mainPanel.add(profpanel, BorderLayout.CENTER);
		btnTimeline.setSelected(sideBarItem == 1);
		btnProjects.setSelected(sideBarItem == 2);
		btnProfile.setSelected(sideBarItem == 3);
		mainPanel.updateUI();
	}
}
