package jmbs.client;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;


import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JEditorPane;
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
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainClient {

	private JFrame frame;
	private JEditorPane updateEditorPane;
	private JEditorPane viewEditorPane;
	private ConnectionPanel cp = new ConnectionPanel();
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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 640, 460);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		final JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel mainCenterPanel = new JPanel();
		mainCenterPanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane viewScrollPane = new JScrollPane();
		viewScrollPane.setViewportBorder(UIManager.getBorder("InsetBorder.aquaVariant"));
		viewScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		mainCenterPanel.add(viewScrollPane);
		
		viewEditorPane = new JEditorPane();
		viewEditorPane.setEditable(false);
		viewEditorPane.setContentType("text/plain");
		viewScrollPane.setViewportView(viewEditorPane);
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
		updateScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		updateScrollPane.setViewportBorder(UIManager.getBorder("InsetBorder.aquaVariant"));
		updatePanel.add(updateScrollPane);
		
		updateEditorPane = new JEditorPane();
		updateEditorPane.setText("Write new update here: ");
		updateEditorPane.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar()=='\n') {
					sendUpdate();
				}
			}
		});
		updateScrollPane.setViewportView(updateEditorPane);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(bottomCenterPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
						.addComponent(centerPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(centerPanel, GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(bottomCenterPanel, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		JPanel picturePanel = new JPanel();
		picturePanel.setBackground(new Color(0, 51, 255));
		GroupLayout gl_bottomCenterPanel = new GroupLayout(bottomCenterPanel);
		gl_bottomCenterPanel.setHorizontalGroup(
			gl_bottomCenterPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_bottomCenterPanel.createSequentialGroup()
					.addGap(1)
					.addComponent(updatePanel, GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(gl_bottomCenterPanel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(picturePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnSend, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_bottomCenterPanel.setVerticalGroup(
			gl_bottomCenterPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_bottomCenterPanel.createSequentialGroup()
					.addGroup(gl_bottomCenterPanel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(updatePanel, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_bottomCenterPanel.createSequentialGroup()
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(picturePanel, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSend)))
					.addContainerGap())
		);
		bottomCenterPanel.setLayout(gl_bottomCenterPanel);
		frame.getContentPane().setLayout(groupLayout);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnJmbs = new JMenu("JMBS");
		menuBar.add(mnJmbs);
		
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
		mntmQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnJmbs.add(mntmQuit);
		
		JMenu mnProjects = new JMenu("Projects");
		menuBar.add(mnProjects);
		
		JMenuItem mntmAddNewProject = new JMenuItem("Add new project");
		mnProjects.add(mntmAddNewProject);
		
		JMenuItem mntmBrowseAllProjects = new JMenuItem("Browse all projects");
		mnProjects.add(mntmBrowseAllProjects);
		
		JMenu menu = new JMenu("?");
		menuBar.add(menu);
		
		JMenuItem mntmHelp = new JMenuItem("Help");
		menu.add(mntmHelp);
		
		JMenuItem mntmCheckForUpdate = new JMenuItem("Check for update");
		menu.add(mntmCheckForUpdate);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		menu.add(mntmAbout);
	}
	
	private void sendUpdate() {
		Calendar cal = new GregorianCalendar();

		// Get the components of the time
		int hour24 = cal.get(Calendar.HOUR_OF_DAY);     // 0..23
		int min = cal.get(Calendar.MINUTE);             // 0..59
		int sec = cal.get(Calendar.SECOND);// 0..59
		String heure = new String();
		heure+=new Integer(hour24).toString() +":"+ new Integer(min).toString()+":"+new Integer(sec).toString();
		String status = updateEditorPane.getText().replaceAll("\n", "");
		viewEditorPane.setText(viewEditorPane.getText()+"\n"+"["+heure+"] "+"Younes: "+status);
		updateEditorPane.setText("Write new update here: ");
	}
}
