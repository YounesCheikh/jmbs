/*
 * JMBS: Java Micro Blogging System
 *
 * Copyright (C) 2012  
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY.
 * See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package jmbs.client.DataTreatment;

import java.io.File;

public class SetEnv {
	
	public static final String CACHE_PIC_PATH = "cache/upics/";

	public SetEnv() {
		// createCacheDirectory();
		createProfilePicsDirectory();
	}

	private void createProfilePicsDirectory() {
		new File(CACHE_PIC_PATH).mkdirs();
	}

	public static void removeFile(File r) {
		if (r.isDirectory()) {
			removeDirectory(r);
		}
		System.out.println(r.delete());
	}

	public static void removeDirectory(File r) {
		File[] fileList = r.listFiles();
		for (int i = 0; i < fileList.length; i++) {
			if (fileList[i].isDirectory()) {
				removeDirectory(fileList[i]);
				System.out.println(fileList[i].delete());
			} else {
				System.out.println(fileList[i].delete());
			}
		}
	}
}
