package jmbs.client;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import jmbs.client.Graphics.ConnectionFrame;
import jmbs.client.Graphics.MainWindow;

public class MainClient {
	private static MainWindow window;
	/**
	 * The Connection Window
	 */
	private static ConnectionFrame cf;

	private static SysConf setMacConf = new SysConf();

	public static void main(String[] args) throws Exception {

		setMacConf.setUIMngr();
		if (!setMacConf.isMac()) {
			try {
				boolean themeMacFound = false;
				boolean themeNimbusFound = false;
				for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
					System.out.println(info.getName());
					if ("Mac OS X".equals(info.getName())) {
						themeMacFound = true;
						break;
					} else if ("Nimbus".equals(info.getName())) {
						themeNimbusFound = true;
					}
				}
				if (themeMacFound)
					UIManager.setLookAndFeel("Mac OS X");
				else if (themeNimbusFound)
					UIManager.setLookAndFeel("Nimbus");
			} catch (Exception e) {
				// look and feel.
			}
		}

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				cf = new ConnectionFrame(window);
				// AWTUtilities.setWindowOpacity(cf, 0.99f);
				cf.setVisible(true);

			}
		});
	}
}
