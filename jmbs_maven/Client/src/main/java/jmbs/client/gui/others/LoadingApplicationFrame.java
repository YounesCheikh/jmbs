package jmbs.client.gui.others;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.Timer;

import jmbs.client.dataTreatment.FramesConf;
import jmbs.client.gui.MainWindow;
import jmbs.client.gui.images.ImagePanel;

import org.eclipse.wb.swing.FocusTraversalOnArray;

public class LoadingApplicationFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4229731248768498008L;
	private Timer load;

	/**
	 * Create the panel.
	 */
	public LoadingApplicationFrame() {
		setAlwaysOnTop(true);
		setEnabled(false);
		setTitle("JMBS Client - Loading… -");
		setResizable(false);
		ImagePanel im = new ImagePanel("/img/loading.png");
		// setContentPane(im);
		getContentPane().setLayout(new BorderLayout(0, 0));

		getContentPane().add(im, BorderLayout.CENTER);
		im.setLayout(null);

		final JProgressBar progressBar = new JProgressBar();
		getContentPane().add(progressBar, BorderLayout.SOUTH);
		progressBar.setBackground(Color.DARK_GRAY);
		progressBar.setValue(0);
		im.setFocusTraversalPolicy(new FocusTraversalOnArray(
				new Component[] {progressBar }));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(460, 310);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { im,
				progressBar, getContentPane() }));
		setUndecorated(true);
		FramesConf.centerThisFrame(this);
		setVisible(true);

		load = new Timer(50, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (progressBar.getValue() < 100) {
					progressBar.setValue(progressBar.getValue() + 1);
				} else {
					finishLoading();
				}
			}
		});
		load.start();

	}

	private void finishLoading() {
		load.stop();
		dispose();
		MainWindow.getFrame().setVisible(true);
	}

	//public static void main(String[] args) {
	//	new LoadingApplicationFrame();
	//}

}
