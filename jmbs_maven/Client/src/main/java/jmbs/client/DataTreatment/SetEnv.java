package jmbs.client.DataTreatment;

import java.io.File;

public class SetEnv {
	
	public SetEnv() {
		createCacheDirectory();
		createProfilePicsDirectory();
	}
	
	private void createCacheDirectory() {
		new File("cache").mkdir();
	}
	
	private void createProfilePicsDirectory() {
		new File("upics").mkdir();
	}
}
