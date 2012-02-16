package jmbs.client;

import java.util.ArrayList;

public class AdminObjectCreator {
	
	AdminObjectCreator() {
		
	}
	
	public UserLight create () {
		String n = null;
		String f = null;
		String m = null;
		int a = 0;
		String p = null;
		int id = 0;
		ArrayList<Integer> pr = null;
		UserLight ul = new UserLight(n, f, m, a, p, id, pr);
		return ul;
	}
	
}
