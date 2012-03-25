package jmbs.client.Graphics;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;

import jmbs.client.ClientRequests;
import jmbs.client.CurrentUser;
import jmbs.common.User;
import java.awt.Font;

public class UsersFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4074857690054435001L;
	private JPanel contentPane;
	private JTextField nameTextField;
	private UsrLstPanel resultSearchPanel;
	private UsrLstPanel flwngUsrsLstPanel;
	private UsrLstPanel flwrLstPanel;
	private User currentUser = new CurrentUser().get();
	ArrayList<User> flwrList;
 	/**
	 * Create the frame.
	 */
	public UsersFrame() {
		setResizable(false);
		setAlwaysOnTop(true);
		setLocationRelativeTo(new MainWindow().getFrame());
		setLocationRelativeTo(nameTextField);
		setTitle("Users management ");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//setBounds(100, 100, 460, 420);
		setSize(460, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel searchUsersPanel = new JPanel();
		tabbedPane.addTab("Search", null, searchUsersPanel, null);
		searchUsersPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		searchUsersPanel.add(panel, BorderLayout.NORTH);
		
		JLabel lblSearchForUser = new JLabel("Search for user: ");
		
		nameTextField = new JTextField();
		nameTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resultSearchPanel.removeAll();
				ArrayList<User> usersList =null ; 
				try {
					usersList = new ClientRequests().getConnection().searchFor(nameTextField.getText());
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
					System.out.println("Error while downloading user list\n" + e1.getMessage());
				}
				if(usersList != null) {
					resultSearchPanel.putList(usersList);
				}
				resultSearchPanel.repaint();
				resultSearchPanel.validate();
				resultSearchPanel.updateUI();
			}
		});
		nameTextField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resultSearchPanel.removeAll();
				ArrayList<User> usersList =null ; 
				try {
					usersList = new ClientRequests().getConnection().searchFor(nameTextField.getText());
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
					System.out.println("Error while downloading user list\n" + e1.getMessage());
				}
				if(usersList != null) {
					resultSearchPanel.putList(usersList);
				}
				resultSearchPanel.repaint();
				resultSearchPanel.validate();
				resultSearchPanel.updateUI();
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblSearchForUser)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(nameTextField, GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSearch))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSearchForUser)
						.addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSearch)))
		);
		panel.setLayout(gl_panel);
		
		JPanel searchContainerPanel = new JPanel();
		searchUsersPanel.add(searchContainerPanel, BorderLayout.CENTER);
		searchContainerPanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane resultScrollPane = new JScrollPane();
		resultScrollPane.updateUI();
		searchContainerPanel.add(resultScrollPane);
		
		resultSearchPanel = new UsrLstPanel();
		resultScrollPane.setViewportView(resultSearchPanel);
		
		JPanel followingPanel = new JPanel();
		tabbedPane.addTab("Following", null, followingPanel, null);
		followingPanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane flwngScrollPane = new JScrollPane();
		flwngScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		followingPanel.add(flwngScrollPane, BorderLayout.CENTER);
		
		flwngUsrsLstPanel = new UsrLstPanel();
		flwngUsrsLstPanel.putList(currentUser.getFollows());
		flwngScrollPane.setViewportView(flwngUsrsLstPanel);
		
		JPanel topFlwngPanel = new JPanel();
		followingPanel.add(topFlwngPanel, BorderLayout.NORTH);
		topFlwngPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblPeopleYouFollow = new JLabel("People you follow:");
		lblPeopleYouFollow.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		topFlwngPanel.add(lblPeopleYouFollow);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flwngUsrsLstPanel.removeAll();
				flwngUsrsLstPanel.putList(currentUser.getFollows());
				flwngUsrsLstPanel.repaint();
				flwngUsrsLstPanel.validate();
				flwngUsrsLstPanel.updateUI();
			}
		});
		topFlwngPanel.add(btnRefresh, BorderLayout.EAST);
		
		JPanel followersPanel = new JPanel();
		tabbedPane.addTab("Followers", null, followersPanel, null);
		followersPanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane flwrScrollPane = new JScrollPane();
		followersPanel.add(flwrScrollPane);
		
		flwrLstPanel = new UsrLstPanel();
		flwrList = new ArrayList<User>();
		try {
			flwrList = new ClientRequests().getConnection().getFollowers(currentUser);
		} catch (RemoteException e2) {
			// TODO Auto-generated catch block
			//e2.printStackTrace();
			System.out.println("there is no follower!");
		}
		
		flwrLstPanel.putList(flwrList);
		
		flwrScrollPane.setViewportView(flwrLstPanel);
		
		JPanel panel_1 = new JPanel();
		followersPanel.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblYourFollowers = new JLabel("Your Followers:");
		panel_1.add(lblYourFollowers);
		
		JButton btnRefresh_1 = new JButton("Refresh");
		btnRefresh_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flwrLstPanel.removeAll();
				try {
					flwrList = new ClientRequests().getConnection().getFollowers(currentUser);
					flwrLstPanel.putList(flwrList);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
					System.out.println("there is no follower!");
				}
				flwrLstPanel.repaint();
				flwrLstPanel.validate();
				flwrLstPanel.updateUI();
			}
		});
		panel_1.add(btnRefresh_1, BorderLayout.EAST);
	}

}
