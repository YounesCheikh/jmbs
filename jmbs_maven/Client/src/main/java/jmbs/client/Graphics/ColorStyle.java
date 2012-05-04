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
 * 
 */

package jmbs.client.Graphics;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.UIManager;

public class ColorStyle {
	private ArrayList<Color> darkList = new ArrayList<Color>();
	private ArrayList<Color> lightList = new ArrayList<Color>();
	private ArrayList<Color> currentColors;
	private static ArrayList<Color> defaultColors = null;

	public ColorStyle(String name) {
		fillColors();
		setColor(name);
	}

	private void setColor(String name) {
		if (name.equals("dark")) {
			currentColors = darkList;
		} else if (name.equals("light")) {
			currentColors = lightList;
		} else {
			currentColors = defaultColors;
		}

	}

	private void fillColors() {
		darkList.add(Color.DARK_GRAY);
		darkList.add(Color.BLACK);
		darkList.add(Color.WHITE);
		darkList.add(Color.LIGHT_GRAY);

		lightList.add(Color.WHITE);
		lightList.add(UIManager.getColor("CheckBox.background"));
		lightList.add(Color.DARK_GRAY);
		lightList.add(Color.GRAY);
	}

	public Color getWindowBackground() {
		return currentColors.get(0);
	}

	public Color getTimeLineBackground() {
		return currentColors.get(1);
	}

	public Color getFontColor() {
		return currentColors.get(2);
	}

	public Color getSecondFontColor() {
		return currentColors.get(3);
	}
	
	public void setDefaultColors(ArrayList<Color> list) {
		if(defaultColors==null) {
			defaultColors = list;
		}
	}
}
