/**
 * 
 */
package jmbs.client;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

//import java.rmi.Naming;
//import java.rmi.RMISecurityManager;
//import jmbs.remote.RemoteUser;

/**
 * @author yc
 *
 */

public class Client {

	public static void main(String[] args) {
		Window w= new Window();
		
		ConnectionPanel connectPanel = new ConnectionPanel();
		//PrintMSGS pmsg = new PrintMSGS();
		// On ajoute le conteneur
		//content.setPreferredSize(300,300);
		JPanel pan = new JPanel();
		pan.add(connectPanel);
		w.setContentPane(pan);
		//this.setContentPane(pmsg);
		w.setVisible(true);
		//pan.setLocation(0, 0);
		//wpan.setBackground(Color.cyan);
		//connectPanel.setPreferredSize(new Dimension(300,400));
		//connectPanel.setLocation(0,0);
	}
}
