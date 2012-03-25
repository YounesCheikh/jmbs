package jmbs.client.Graphics;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JToggleButton;

import jmbs.client.ClientRequests;
import jmbs.client.CurrentUser;
import jmbs.common.User;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.awt.Component;
import javax.swing.SwingConstants;

public class FlwUsrPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5508008556101716116L;

	/**
	 * Create the panel.
	 */
	public FlwUsrPanel(final User u) {
		setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));

		ImagePanel panel = new ImagePanel("./src/jmbs/client/img/avatar.jpg",
				60, 60);
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);

		JLabel lblYounesCheikh = new JLabel(u.getFullName());
		lblYounesCheikh.setHorizontalAlignment(SwingConstants.LEFT);
		lblYounesCheikh.setFont(new Font("Lucida Grande", Font.BOLD, 14));

		JToggleButton tglbtnFollow = new JToggleButton("Follow");
		tglbtnFollow.setHorizontalAlignment(SwingConstants.RIGHT);
		tglbtnFollow.setAlignmentX(Component.RIGHT_ALIGNMENT);
		// if the current user follows this user u, set this button as selected
		if (new CurrentUser().get().getFollows().contains(u)) {
			tglbtnFollow.setSelected(true);
		}
		tglbtnFollow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					User current = new CurrentUser().get();

					if (!current.getFollows().contains(u)) {
						new ClientRequests().getConnection().follow(
								current.getId(), u.getId());
						current.getFollows().add(u);
					}
					else {
						new ClientRequests().getConnection().unFollow(
								current.getId(), u.getId());
						current.getFollows().remove(u);
					}

				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					// e1.printStackTrace();
					System.out.println("Error: Can't follow this user\n"
							+ e1.getMessage());
				}

			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblYounesCheikh, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tglbtnFollow, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addGap(0))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblYounesCheikh, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
							.addComponent(tglbtnFollow))
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}
