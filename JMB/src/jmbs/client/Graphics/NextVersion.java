package jmbs.client.Graphics;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;

public class NextVersion extends JDialog {

	/**
	 * this dialog is temporary , i use it to say to the user that the
	 * feature which you request is not available for the moment
	 */
	private static final long serialVersionUID = 7095502745092240625L;
	private final JPanel contentPanel = new JPanel();
	private JPanel buttonPane;
	private JLabel lblWeAreVery;

	/**
	 * Create the dialog.
	 */
	public NextVersion() {
		setTitle("In the next version");
		setBounds(100, 100, 450, 189);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		{
			lblWeAreVery = new JLabel("We are very very very very very very very very very Sorry!");
		}
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		getContentPane().setLayout(new MigLayout("", "[450px]", "[121px][39px]"));
		JLabel lblThisButtonWill = new JLabel("This button will be available in the next version!! O_o");
		JLabel lblCloseThisAnd = new JLabel("Close this, and don't try to compile this program again!");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(44)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCloseThisAnd)
						.addComponent(lblThisButtonWill)
						.addComponent(lblWeAreVery))
					.addContainerGap(111, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(14)
					.addComponent(lblWeAreVery)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblThisButtonWill)
					.addGap(18)
					.addComponent(lblCloseThisAnd)
					.addContainerGap(19, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		getContentPane().add(contentPanel, "cell 0 0,growx,aligny top");
		getContentPane().add(buttonPane, "cell 0 1,growx,aligny top");
	}

}
