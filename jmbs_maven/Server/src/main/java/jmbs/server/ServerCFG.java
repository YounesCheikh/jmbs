/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jmbs.server;

/**
 *
 * @author jim
 */
public class ServerCFG {
    
    private static volatile ServerCFG instance = null;
    
    public final static ServerCFG getInstance() {
		if (instance == null) 
		{
		 synchronized (ServerCFG.class) {
				if (instance == null)
					instance = new ServerCFG();
			}
		}
		return instance;
	}
    
}
