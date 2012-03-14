package jmbs.client;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.sql.Date;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextPane;

import jmbs.common.Message;
import jmbs.common.User;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.ImageIcon;

public class MainClient {

	private JFrame frame;
	private JTextPane updateTextPane;
	//private GridBagConstraints c = new GridBagConstraints();
	GridBagConstraints gbc;
	private static int linesNB = 0;
	private JPanel timeLinePanel;
	private AboutFrame about = new AboutFrame();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainClient window = new MainClient();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainClient() {
		initialize();
		/*// Test of MsgPanel 
		JFrame w = new JFrame();
		w.getContentPane().setLayout(new BorderLayout());
		w.getContentPane().add(new MsgPanel(new Message()),BorderLayout.CENTER);
		w.setSize(600, 400);
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		w.setTitle("Test Msg Panel");
		w.setVisible(true);
		*/
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 640, 460);
		frame.setMinimumSize(new Dimension(300, 500));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout(0, 0));

		JPanel mainCenterPanel = new JPanel();
		mainCenterPanel.setLayout(new BorderLayout(0, 0));

		JScrollPane viewScrollPane = new JScrollPane();
		viewScrollPane.setViewportBorder(UIManager
				.getBorder("InsetBorder.aquaVariant"));
		viewScrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		mainCenterPanel.add(viewScrollPane);
		
		timeLinePanel = new JPanel();
		GridBagLayout gbl_timeLinePanel = new GridBagLayout();
		gbl_timeLinePanel.columnWidths = new int[]{0, 0};
		gbl_timeLinePanel.rowHeights = new int[]{0, 0};
		gbl_timeLinePanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_timeLinePanel.rowWeights = new double[]{1.0, Double.MAX_VALUE};
		timeLinePanel.setLayout(gbl_timeLinePanel);
		
		//c.insets = new Insets(10, 10, 10, 10);
		//c.fill = GridBagConstraints.HORIZONTAL;
		
		
		
		//timeLinePanel.add(new MsgPanel(new Message()));
		//timeLinePanel.add(new MsgPanel(new Message()));
		//timeLinePanel.add(new MsgPanel(new Message()));
		//timeLinePanel.add(new MsgPanel(new Message()));
		//timeLinePanel.add(new MsgPanel(new Message()));
		
		viewScrollPane.setViewportView(timeLinePanel);
		
		//MsgPanel mp = new MsgPanel(new Message());
		gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.ipady = 0;
		//MsgPanel msgPanel = new MsgPanel(new Message());
		//timeLinePanel.add(msgPanel, gbc);
		putMessage(new MsgPanel(new Message(new User("Younes", "CHEIKH", "test@test.com"), "", "can any one recommend me a good grammar checker for Latex? #latex", new Date(0))));
		putMessage(new MsgPanel(new Message(new User("Benjamin", "BABIC", "test@test.com"), "", "We agree with @YourAnonNews that #Antisec was created under #FBI supervision!", new Date(0))));
		putMessage(new MsgPanel(new Message(new User("Benjamin", "BABIC", "test@test.com"), "", "FBI actually leak Stratfor e-mails just to bust Julian Assange? goo.gl/fb/ZGxiW #Security #THN #news", new Date(0))));
		putMessage(new MsgPanel(new Message(new User("Younes", "CHEIKH", "test@test.com"), "", "grep Privileged.*true modules/exploits/ -rl | wc -l  # metasploit has 274 remote roots!  =)", new Date(0))));
		putMessage(new MsgPanel(new Message(new User("Younes", "CHEIKH", "test@test.com"), "", "Greg Smith Isn't A Whistleblower, He's Just A Goldman Sachs Executive Having A Midlife Crisis onforb.es/w854GZ", new Date(0))));
		putMessage(new MsgPanel(new Message(new User("Benjamin", "BABIC", "test@test.com"), "", "well I suppose this is almost exactly what I was talking about in regards to MS12-020 bit.ly/wqv64C", new Date(0))));
		putMessage(new MsgPanel(new Message(new User("Younes", "CHEIKH", "test@test.com"), "", "It seems hypocritical to demand that the govt stay out of your life, and at the same time demand that it intrudes into the lives of others.", new Date(0))));
		putMessage(new MsgPanel(new Message(new User("Younes", "CHEIKH", "test@test.com"), "", "I wonder how much the Goldman Sachs culture changed vs. how much Greg Smith realized as he rose through the ranks: nyti.ms/wunLqU", new Date(0))));
		centerPanel.add(mainCenterPanel);

		final JPanel bottomCenterPanel = new JPanel();
		bottomCenterPanel.setBorder(null);
		bottomCenterPanel.setMinimumSize(new Dimension(300, 300));

		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sendUpdate();
			}
		});

		JPanel updatePanel = new JPanel();
		updatePanel.setBorder(null);
		updatePanel.setLayout(new BorderLayout(0, 0));

		JScrollPane updateScrollPane = new JScrollPane();
		updateScrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		updateScrollPane.setViewportBorder(UIManager
				.getBorder("InsetBorder.aquaVariant"));
		updatePanel.add(updateScrollPane);

		updateTextPane = new JTextPane();
		updateTextPane.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if (arg0.getKeyChar() == '\n') {
					updateTextPane.setText(new String(updateTextPane.getText()).replace("\n", ""));
					sendUpdate();
				}
			}
		});
		updateScrollPane.setViewportView(updateTextPane);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.TRAILING)
														.addComponent(
																bottomCenterPanel,
																Alignment.LEADING,
																GroupLayout.DEFAULT_SIZE,
																628,
																Short.MAX_VALUE)
														.addComponent(
																centerPanel,
																Alignment.LEADING,
																GroupLayout.DEFAULT_SIZE,
																628,
																Short.MAX_VALUE))
										.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(centerPanel, GroupLayout.DEFAULT_SIZE,
								285, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(bottomCenterPanel,
								GroupLayout.PREFERRED_SIZE, 113,
								GroupLayout.PREFERRED_SIZE).addContainerGap()));

		AvatarPanel picturePanel = new AvatarPanel();
		picturePanel.setBackground(new Color(0, 51, 255));
		GroupLayout gl_bottomCenterPanel = new GroupLayout(bottomCenterPanel);
		gl_bottomCenterPanel
				.setHorizontalGroup(gl_bottomCenterPanel
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								gl_bottomCenterPanel
										.createSequentialGroup()
										.addGap(1)
										.addComponent(updatePanel,
												GroupLayout.DEFAULT_SIZE, 528,
												Short.MAX_VALUE)
										.addGap(18)
										.addGroup(
												gl_bottomCenterPanel
														.createParallelGroup(
																Alignment.TRAILING,
																false)
														.addComponent(
																picturePanel,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																btnSend,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addContainerGap()));
		gl_bottomCenterPanel
				.setVerticalGroup(gl_bottomCenterPanel
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								gl_bottomCenterPanel
										.createSequentialGroup()
										.addGroup(
												gl_bottomCenterPanel
														.createParallelGroup(
																Alignment.TRAILING,
																false)
														.addComponent(
																updatePanel,
																GroupLayout.PREFERRED_SIZE,
																107,
																GroupLayout.PREFERRED_SIZE)
														.addGroup(
																gl_bottomCenterPanel
																		.createSequentialGroup()
																		.addContainerGap(
																				GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(
																				picturePanel,
																				GroupLayout.PREFERRED_SIZE,
																				66,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				btnSend)))
										.addContainerGap()));
		bottomCenterPanel.setLayout(gl_bottomCenterPanel);
		frame.getContentPane().setLayout(groupLayout);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(UIManager.getColor("ComboBox.background"));
		frame.setJMenuBar(menuBar);

		JMenu mnJmbs = new JMenu("JMBS");
		mnJmbs.setBackground(UIManager.getColor("ComboBox.background"));
		menuBar.add(mnJmbs);
		
		JMenuItem mntmRefresh = new JMenuItem("Refresh");
		mntmRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timeLinePanel.updateUI();
				timeLinePanel.validate();
			}
		});
		
		mntmRefresh.setIcon(new ImageIcon("refresh_ico.png"));
		mnJmbs.add(mntmRefresh);
		
		JSeparator separator_1 = new JSeparator();
		mnJmbs.add(separator_1);

		JMenu mnAccount = new JMenu("Account");
		mnJmbs.add(mnAccount);

		JMenuItem mntmConnect = new JMenuItem("Connect");
		mntmConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConnectionFrame cf = new ConnectionFrame();
				cf.setVisible(true);
			}
		});
		mnAccount.add(mntmConnect);

		JMenuItem mntmDisconnect = new JMenuItem("Disconnect");
		mntmDisconnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NextVersion nv = new NextVersion();
				nv.setVisible(true);
			}
		});
		mnAccount.add(mntmDisconnect);

		JMenuItem mntmPreferences = new JMenuItem("Preferences");
		mnJmbs.add(mntmPreferences);

		JSeparator separator = new JSeparator();
		mnJmbs.add(separator);

		JMenuItem mntmQuit = new JMenuItem("Quit");
		mntmQuit.setIcon(new ImageIcon("quit_ico.png"));
		mntmQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnJmbs.add(mntmQuit);

		JMenu mnProjects = new JMenu("Projects");
		mnProjects.setBackground(UIManager.getColor("ComboBox.background"));
		menuBar.add(mnProjects);

		JMenuItem mntmAddNewProject = new JMenuItem("Add new project");
		mnProjects.add(mntmAddNewProject);

		JMenuItem mntmBrowseAllProjects = new JMenuItem("Browse all projects");
		mnProjects.add(mntmBrowseAllProjects);

		JMenu menu = new JMenu("?");
		menu.setBackground(UIManager.getColor("ComboBox.background"));
		menuBar.add(menu);

		JMenuItem mntmHelp = new JMenuItem("Help");
		menu.add(mntmHelp);

		JMenuItem mntmCheckForUpdate = new JMenuItem("Check for update");
		menu.add(mntmCheckForUpdate);

		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				about.setVisible(true);
			}
		});
		menu.add(mntmAbout);
		timeLinePanel.updateUI();
	}

	private void sendUpdate() {
		if (updateTextPane.getText().length() > 0) {
			/*
			Calendar cal = new GregorianCalendar();
			// Get the components of the time
			int hour24 = cal.get(Calendar.HOUR_OF_DAY); // 0..23
			int min = cal.get(Calendar.MINUTE); // 0..59
			int sec = cal.get(Calendar.SECOND);// 0..59
			String heure = new String();
			heure += new Integer(hour24).toString() + ":"
					+ new Integer(min).toString() + ":"
					+ new Integer(sec).toString();
			*/
			String status = updateTextPane.getText().replaceAll("\n", "");
			putMessage(new MsgPanel(new Message(new User("Younes", "CHEIKH", "test@test.com"), "", status, new Date(0))));
			updateTextPane.setText("");
		}
	}
	private void putMessage(Component obj) {
		gbc.gridx =0;
		gbc.gridy = linesNB;
		timeLinePanel.add(obj, gbc);
		//timeLinePanel.validate();
		linesNB++;
		timeLinePanel.updateUI();
	}
}
