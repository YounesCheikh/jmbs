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
import jmbs.client.SysConf;
import jmbs.common.Message;
import jmbs.common.User;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.DefaultComboBoxModel;

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
	private JPanel projectsPanel;
	private JPanel prjctTopPanel;
	private JPanel prjctMainPanel;
	private JTextField searchTextField;

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
					currentUser.getId(), 0, ClientRequests.maxReceivedMsgs);
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
		//frmJmbsClient.setLocationRelativeTo(null);
		// frmJmbsClient.setVisible(true);
		new SysConf().centerThisFrame(frmJmbsClient);
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
		tlscrollPane.setAutoscrolls(true);
		tlscrollPane.getVerticalScrollBar().setUnitIncrement(30);
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
		
		projectsPanel = new JPanel();
		tabbedPane.addTab("Projects", null, projectsPanel, null);
		
		prjctTopPanel = new JPanel();
		
		JLabel lblSeachForProject = new JLabel("Seach For Project:");
		
		searchTextField = new JTextField();
		searchTextField.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Projet Java", "Systeme d'Exploitation", "WebServer", "SQL Maintenance"}));
		
		JLabel lblShowMessagesFrom = new JLabel("Show Messages From:");
		
		JButton btnOk = new JButton("OK");
		
		JSeparator separator = new JSeparator();
		
		JButton btnShow = new JButton("Show");
		
		JSeparator separator_1 = new JSeparator();
		GroupLayout gl_prjctTopPanel = new GroupLayout(prjctTopPanel);
		gl_prjctTopPanel.setHorizontalGroup(
			gl_prjctTopPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_prjctTopPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_prjctTopPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(separator_1, GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_prjctTopPanel.createSequentialGroup()
							.addComponent(lblSeachForProject)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(searchTextField)
							.addGap(30)
							.addComponent(btnOk))
						.addGroup(Alignment.LEADING, gl_prjctTopPanel.createSequentialGroup()
							.addComponent(lblShowMessagesFrom)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnShow))
						.addComponent(separator, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_prjctTopPanel.setVerticalGroup(
			gl_prjctTopPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_prjctTopPanel.createSequentialGroup()
					.addGap(1)
					.addGroup(gl_prjctTopPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnOk)
						.addGroup(gl_prjctTopPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblSeachForProject)
							.addComponent(searchTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_prjctTopPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnShow)
						.addComponent(lblShowMessagesFrom))
					.addGap(1)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		prjctTopPanel.setLayout(gl_prjctTopPanel);
		
		prjctMainPanel = new JPanel();
		projectsPanel.setLayout(new BorderLayout(0, 0));
		projectsPanel.add(prjctTopPanel, BorderLayout.NORTH);
		projectsPanel.add(prjctMainPanel);
		prjctMainPanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		prjctMainPanel.add(scrollPane, BorderLayout.CENTER);
		
		SearchProjectPanel panel = new SearchProjectPanel();
		scrollPane.setViewportView(panel);

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
