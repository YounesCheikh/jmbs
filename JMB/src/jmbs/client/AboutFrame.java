package jmbs.client;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AboutFrame extends JFrame {

	/**
	 * a small frame for write something about the program developers! :-)
	 */
	private static final long serialVersionUID = 3233282767200634393L;


	/**
	 * Create the frame.
	 */
	public AboutFrame() {
		setBounds(100, 100, 450, 246);
		
		JLabel lblHiThis = new JLabel("Hi , this program is blablablablablaal");
		
		JLabel lblYounesBenjamin = new JLabel("Younes & Benjamin");
		
		JLabel lblCyounescomBbabiccom = new JLabel("cYounes.com & bBabic.com");
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(74)
							.addComponent(lblHiThis, GroupLayout.PREFERRED_SIZE, 268, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(131)
							.addComponent(lblYounesBenjamin))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(111)
							.addComponent(lblCyounescomBbabiccom))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(165)
							.addComponent(btnOk)))
					.addContainerGap(108, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(42)
					.addComponent(lblHiThis, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblYounesBenjamin)
					.addGap(33)
					.addComponent(lblCyounescomBbabiccom)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnOk)
					.addContainerGap(73, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

	}
}
