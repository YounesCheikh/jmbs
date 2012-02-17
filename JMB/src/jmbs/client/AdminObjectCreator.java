package jmbs.client;

import java.util.ArrayList;

public class AdminObjectCreator {
	
	AdminObjectCreator() {
		
	}
	
	public UserDTO create () {
		String n = null;
		String f = null;
		String m = null;
		int a = 0;
		String p = null;
		int id = 0;
		ArrayList<Integer> pr = null;
		UserDTO ul = new UserDTO(n, f, m, a, p, id, pr);
		return ul;
	}
	
}
