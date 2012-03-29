package jmbs.client;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class SysConf {
	
	
	private static boolean macSys = false;
	
	public SysConf() {
		
	}
	
	private static void macSetup(String appName) {
		String os = System.getProperty("os.name").toLowerCase();
		macSys = os.startsWith("mac os x");
		if (!macSys)
			return;

		System.setProperty("apple.laf.useScreenMenuBar", "true");
		System.setProperty("com.apple.mrj.application.apple.menu.about.name", appName);
	}
	
	public void setUIMngr() {
		
		macSetup("JMBS");
	    try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	public boolean isMac() {
		return macSys;
	}
 
}
