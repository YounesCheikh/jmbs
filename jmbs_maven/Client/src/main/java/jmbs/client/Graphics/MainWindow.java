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
import javax.swing.JScrollPane;
import java.rmi.RemoteException;
import java.util.ArrayList;
import jmbs.client.ClientRequests;
import jmbs.client.CurrentUser;
import jmbs.client.SysConf;
import jmbs.client.Graphics.projects.MyProjectsPanel;
import jmbs.client.Graphics.projects.ParticipationsPrjcstPanel;
import jmbs.client.Graphics.projects.PrjctsTimeLinePanel;
import jmbs.client.Graphics.projects.SearchProjectPanel;
import jmbs.common.Message;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow {

	private static JFrame frmJmbsClient = null;
	private static TimeLinePanel timelinepanel;
	private static ProfilePanel ppanel;
	private static NewMessageFrame nmFrame;
	private static AboutFrame about;
	private static UsersFrame uFrame;
	private static ArrayList<Message> msgListTL;
	private MainMenuBar menuBar;
	private JTabbedPane tabbedPane;
	private JPanel projectsPanel;

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
		msgListTL = new ArrayList<Message>();
		frmJmbsClient = new JFrame();
		frmJmbsClient.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				frmJmbsClient.dispose();
				if (ClientRequests.server != null)
					try {
						ClientRequests.server.close(CurrentUser.getId());
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						// e1.printStackTrace();
						System.out.println(e1.getMessage());
					}
				System.exit(0);
			}
		});
		ppanel = new ProfilePanel(CurrentUser.get());
		about = new AboutFrame();
		uFrame = new UsersFrame();

		frmJmbsClient.setTitle("JMBS Client : " + CurrentUser.getFullName());
		// frmJmbsClient.setBounds(100, 100, 365, 600);
		frmJmbsClient.setSize(480, 640);
		frmJmbsClient.setMinimumSize(new Dimension(480, 600));
		frmJmbsClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frmJmbsClient.setLocationRelativeTo(null);
		// frmJmbsClient.setVisible(true);
		new SysConf().centerThisFrame(frmJmbsClient);
		//menuBar = new MainMenuBar(this);
		//frmJmbsClient.setJMenuBar(menuBar);
		tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane.setBorder(null);
		tabbedPane.setToolTipText("JMBS");
		frmJmbsClient.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
				timelinepanel = new TimeLinePanel();
				nmFrame = new NewMessageFrame(timelinepanel);
				JScrollPane tlscrollPane = new JScrollPane();
				tabbedPane.addTab("TimeLine", null, tlscrollPane, null);
				tlscrollPane.setAutoscrolls(true);
				tlscrollPane.getVerticalScrollBar().setUnitIncrement(30);
				tlscrollPane
						.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				tlscrollPane.setViewportView(timelinepanel);

		projectsPanel = new JPanel();
		tabbedPane.addTab("Projects", null, projectsPanel, null);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		
		PrjctsTimeLinePanel prjctTLPanel = new PrjctsTimeLinePanel();
		tabbedPane_1.addTab("TimeLine", null, prjctTLPanel, null);
		
		SearchProjectPanel searchPrjctPanel = new SearchProjectPanel();
		tabbedPane_1.addTab("Search For Project", null, searchPrjctPanel, null);
		
		ParticipationsPrjcstPanel participationPrjctPanel = new ParticipationsPrjcstPanel();
		tabbedPane_1.addTab("Participation", null, participationPrjctPanel, null);
		
		MyProjectsPanel myPrjctPanel = new MyProjectsPanel();
		tabbedPane_1.addTab("My Projects", null, myPrjctPanel, null);
		GroupLayout gl_projectsPanel = new GroupLayout(projectsPanel);
		gl_projectsPanel.setHorizontalGroup(
			gl_projectsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_projectsPanel.createSequentialGroup()
					.addComponent(tabbedPane_1, GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
					.addGap(0))
		);
		gl_projectsPanel.setVerticalGroup(
			gl_projectsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_projectsPanel.createSequentialGroup()
					.addComponent(tabbedPane_1, GroupLayout.DEFAULT_SIZE, 997, Short.MAX_VALUE)
					.addGap(7))
		);
		projectsPanel.setLayout(gl_projectsPanel);

		JPanel profpanel = new JPanel();
		tabbedPane.addTab("Profile", null, profpanel, null);
		profpanel.setLayout(new BorderLayout(0, 0));

		JScrollPane profilescrollPane = new JScrollPane();
		profilescrollPane.setViewportBorder(UIManager
				.getBorder("List.evenRowBackgroundPainter"));
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
		uFrame = new UsersFrame(); // TODO:  Provisoir !
	}
	
	public void setMenuBar() {
		menuBar = new MainMenuBar(this);
		frmJmbsClient.setJMenuBar(menuBar);
	}
	
	public 	JTabbedPane getTabbedPane() {
		return tabbedPane;
	}
	
	public JPanel getProjectsPanel() {
		return projectsPanel;
	}

	public void checkNewMessages(int idLastMsg) {
		try {
			msgListTL = new ClientRequests().getConnection().getLatestTL(
					CurrentUser.getId(), idLastMsg,
					ClientRequests.maxReceivedMsgs);
			timelinepanel.putList(msgListTL);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();
			System.out.println("Can't get last timeLine from server ");
		}
	}
}
