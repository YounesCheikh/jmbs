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

package jmbs.client.gui.others;

import javax.swing.JOptionPane;

public class SayToUser {

	public static void error(String title, String message) {
		JOptionPane.showMessageDialog(null, message, title,
				JOptionPane.ERROR_MESSAGE);
	}

	public static void warning(String title, String message) {
		JOptionPane.showMessageDialog(null, message, title,
				JOptionPane.WARNING_MESSAGE);
	}

	public static void success(String title, String message) {
		JOptionPane.showMessageDialog(null, message, title,
				JOptionPane.PLAIN_MESSAGE);
	}
}
