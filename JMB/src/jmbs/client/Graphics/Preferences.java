package jmbs.client.Graphics;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JSlider;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import com.sun.awt.AWTUtilities;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Preferences extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4493110241913884584L;
	private JPanel contentPane;
	private JLabel labelprecent;
	private JSlider slider;
	private JCheckBox chckbxTransparency;
	private JLabel lblThemes;
	private JRadioButton rdbtnDark;
	private JRadioButton rdbtnGray;
	private JRadioButton rdbtnLight;
	private JFrame frame = new MainWindow().getFrame();

	/**
	 * Create the frame.
	 */
	public Preferences() {
		ButtonGroup groupDefaultOptions = new ButtonGroup();
		ButtonGroup groupThemes = new ButtonGroup();
		setTitle("JMBS: Preferences");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 320, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel generalPanel = new JPanel();
		tabbedPane.addTab("General", new ImageIcon("/Users/ycheikh/Dev/JMB/src/jmbs/client/img/General-Alt.png"), generalPanel, null);
		
		JPanel appearancePanel = new JPanel();
		tabbedPane.addTab("appearance", new ImageIcon("/Users/ycheikh/Dev/JMB/src/jmbs/client/img/appearance.png"), appearancePanel, "appearance");
		
		JRadioButton rdbtnUseDefaultOptions = new JRadioButton("Use default options");
		rdbtnUseDefaultOptions.setBounds(16, 16, 155, 23);
		rdbtnUseDefaultOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chckbxTransparency.setEnabled(false);
				chckbxTransparency.setSelected(false);
				labelprecent.setEnabled(false);
				slider.setEnabled(false);
				lblThemes.setEnabled(false);
				rdbtnDark.setEnabled(false);
				rdbtnGray.setEnabled(false);
				rdbtnGray.setSelected(true);
				rdbtnLight.setEnabled(false);
				new MainWindow().setColors("");
				AWTUtilities.setWindowOpacity(frame, 1.0f);
			}
		});
		rdbtnUseDefaultOptions.setSelected(true);
		
		JRadioButton rdbtnDontUseThe = new JRadioButton("Don't use the default options");
		rdbtnDontUseThe.setBounds(16, 54, 216, 23);
		rdbtnDontUseThe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				chckbxTransparency.setEnabled(true);
				lblThemes.setEnabled(true);
				rdbtnDark.setEnabled(true);
				rdbtnGray.setEnabled(true);
				rdbtnLight.setEnabled(true);
			}
		});
		
		groupDefaultOptions.add(rdbtnUseDefaultOptions);
		groupDefaultOptions.add(rdbtnDontUseThe);
		
		chckbxTransparency = new JCheckBox("Opacity:");
		chckbxTransparency.setBounds(76, 81, 84, 23);
		chckbxTransparency.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxTransparency.isSelected()) {
					labelprecent.setEnabled(true);
					slider.setEnabled(true);
					AWTUtilities.setWindowOpacity(frame, slider.getValue()/100f);
				}
				else {
					labelprecent.setEnabled(false);
					slider.setEnabled(false);
					AWTUtilities.setWindowOpacity(frame, 1.0f);
				}
			}
		});
		chckbxTransparency.setEnabled(false);
		
		slider = new JSlider();
		slider.setBounds(50, 108, 190, 38);
		slider.setEnabled(false);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				labelprecent.setText(slider.getValue()+"%");
				AWTUtilities.setWindowOpacity(frame, slider.getValue()/100f);
			}
		});
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		
		labelprecent = new JLabel(slider.getValue()+"%");
		labelprecent.setBounds(164, 85, 25, 16);
		labelprecent.setEnabled(false);
		
		lblThemes = new JLabel("Themes:");
		lblThemes.setBounds(45, 150, 57, 16);
		lblThemes.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		
		rdbtnDark = new JRadioButton("Dark");
		
		rdbtnDark.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MainWindow().setColors("dark");
			}
		});
		
		rdbtnDark.setBounds(40, 170, 62, 23);
		rdbtnDark.setEnabled(false);
		
		rdbtnGray = new JRadioButton("Gray");
		rdbtnGray.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MainWindow().setColors("");
			}
		});
		rdbtnGray.setBounds(110, 170, 60, 23);
		rdbtnGray.setEnabled(false);
		rdbtnGray.setSelected(true);
		
		rdbtnLight = new JRadioButton("Light");
		
		rdbtnLight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MainWindow().setColors("light");
			}
		});
		
		rdbtnLight.setBounds(182, 170, 64, 23);
		rdbtnLight.setEnabled(false);
		
		groupThemes.add(rdbtnDark);
		groupThemes.add(rdbtnGray);
		groupThemes.add(rdbtnLight);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(16, 43, 258, 7);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(16, 197, 86, 29);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JButton btnOk = new JButton("Ok");
		btnOk.setBounds(185, 197, 75, 29);
		
		JButton btnApply = new JButton("Apply");
		btnApply.setBounds(110, 197, 80, 29);
		appearancePanel.setLayout(null);
		appearancePanel.add(rdbtnUseDefaultOptions);
		appearancePanel.add(rdbtnDontUseThe);
		appearancePanel.add(chckbxTransparency);
		appearancePanel.add(slider);
		appearancePanel.add(labelprecent);
		appearancePanel.add(lblThemes);
		appearancePanel.add(rdbtnDark);
		appearancePanel.add(rdbtnGray);
		appearancePanel.add(rdbtnLight);
		appearancePanel.add(separator);
		appearancePanel.add(btnCancel);
		appearancePanel.add(btnOk);
		appearancePanel.add(btnApply);
	}
}
