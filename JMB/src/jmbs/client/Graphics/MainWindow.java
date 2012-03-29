package jmbs.client.Graphics;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import java.rmi.RemoteException;
import java.util.ArrayList;

import jmbs.client.ClientRequests;
import jmbs.client.CurrentUser;
import jmbs.common.Message;
import jmbs.common.User;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class MainWindow {

	private static JFrame frmJmbsClient = null;

	public NewMessageFrame getNmFrame() {
		return nmFrame;
	}

	public AboutFrame getAbout() {
		return about;
	}

	public UsersFrame getuFrame() {
		return uFrame;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	private static TimeLinePanel timelinepanel;
	private static ProfilePanel ppanel;
	private static NewMessageFrame nmFrame;
	private static AboutFrame about;
	private static UsersFrame uFrame;
	public ArrayList<Message> getMsgListTL() {
		return msgListTL;
	}

	private static ArrayList<Message> msgListTL;
	private User currentUser = new CurrentUser().get();
	private MainMenuBar menuBar;
	private JTabbedPane tabbedPane;
	private JPanel tlpanel;

	public MainMenuBar getMenuBar() {
		return menuBar;
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		if (frmJmbsClient == null) {
			initialize();
		}
	}

	public JFrame getFrame() {
		return frmJmbsClient;
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		
		frmJmbsClient = new JFrame();
		
		timelinepanel = new TimeLinePanel();
		timelinepanel.setBorder(null);
		
		ppanel = new ProfilePanel();
		nmFrame = new NewMessageFrame(timelinepanel);
		about = new AboutFrame();
		uFrame = new UsersFrame();
		try {
			msgListTL = new ClientRequests().getConnection().getLatestTL(
					currentUser.getId(), 0, 50);
			timelinepanel.putList(msgListTL);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();
			System.out.println("Can't get last timeLine from server ");
		}

		frmJmbsClient.setTitle("JMBS Client : "+currentUser.getFullName());
		// frmJmbsClient.setBounds(100, 100, 365, 600);
		frmJmbsClient.setSize(480, 640);
		frmJmbsClient.setMinimumSize(new Dimension(480, 600));
		frmJmbsClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJmbsClient.setLocationRelativeTo(null);
		// frmJmbsClient.setVisible(true);

		menuBar = new MainMenuBar(this);
		frmJmbsClient.setJMenuBar(menuBar);
		tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		
		tabbedPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
				TitledBorder.BELOW_BOTTOM, null, null));
		tabbedPane.setToolTipText("JMBS");
		frmJmbsClient.getContentPane().add(tabbedPane, BorderLayout.CENTER);

		tlpanel = new JPanel();
		tlpanel.setBorder(null);
		
		tlpanel.setToolTipText("TimeLine");
		tabbedPane.addTab("TimeLine", null, tlpanel, null);

		JScrollPane tlscrollPane = new JScrollPane();
		tlscrollPane.setViewportBorder(null);
		tlscrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		tlscrollPane.setViewportView(timelinepanel);
		GroupLayout gl_tlpanel = new GroupLayout(tlpanel);
		gl_tlpanel.setHorizontalGroup(
			gl_tlpanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_tlpanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(tlscrollPane, GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_tlpanel.setVerticalGroup(
			gl_tlpanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_tlpanel.createSequentialGroup()
					.addComponent(tlscrollPane, GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
					.addContainerGap())
		);
		tlpanel.setLayout(gl_tlpanel);

		JPanel profpanel = new JPanel();
		tabbedPane.addTab("Profile", null, profpanel, null);
		profpanel.setLayout(new BorderLayout(0, 0));

		JScrollPane profilescrollPane = new JScrollPane();
		profilescrollPane.setViewportBorder(UIManager.getBorder("List.evenRowBackgroundPainter"));
		profilescrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		profilescrollPane.setViewportView(ppanel);

		profpanel.add(profilescrollPane);
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
		frmJmbsClient.getContentPane().setBackground(cs.getWindowBackground()); // Color 0
		timelinepanel.setBackground(cs.getTimeLineBackground()); // Color 1
		//tabbedPane.setBackground(cs.getWindowBackground()); // Color 0
		//tlpanel.setBackground(cs.getWindowBackground()); // Color 0
		for(Component c: timelinepanel.getComponents()) {
			((MsgPanel) c).setColors(name);
		}
		frmJmbsClient.setVisible(true);
	}
}
