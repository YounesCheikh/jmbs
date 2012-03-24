package jmbs.client.Graphics;

import java.awt.BorderLayout;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class RegisterFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7428710889628300587L;
	private JPanel contentPane;
	private static Point point = new Point();
	/**
	 * Create the frame.
	 */
	public RegisterFrame() {
		
		setLocationRelativeTo(null);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				point.x = e.getX();
				point.y = e.getY();
			}
		});

		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				Point p = getLocation();
				setLocation(p.x + e.getX() - point.x, p.y + e.getY() - point.y);
			}
		});
		setTitle("Register with JMBS");
		setUndecorated(true);
		
		
		//setDefaultCloseOperation(JFrame.);
		setBounds(100, 100, 450, 484);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		RegisterPanel panel = new RegisterPanel(this);
		contentPane.add(panel, BorderLayout.CENTER);
	}

}
