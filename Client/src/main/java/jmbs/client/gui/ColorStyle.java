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

package jmbs.client.gui;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.UIManager;

/**
 * Manage Application Colors
 * @author <a href="http://cyounes.com/">Younes CHEIKH</a>
 * @author Benjamin Babic
 */
public class ColorStyle {
	private ArrayList<Color> darkList = new ArrayList<Color>();
	private ArrayList<Color> lightList = new ArrayList<Color>();
	private ArrayList<Color> currentColors;
	private static ArrayList<Color> defaultColors = null;

        /**
         * 
         * @param name the color theme name
         */
	public ColorStyle(String name) {
		fillColors();
		setColor(name);
	}

        /**
         * set Colors for application
         * @param name the theme name
         */
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

        /**
         * 
         * @return the current background
         */
	public Color getWindowBackground() {
		return currentColors.get(0);
	}

        /**
         * 
         * @return Timeline background
         */
	public Color getTimeLineBackground() {
		return currentColors.get(1);
	}

        /**
         * 
         * @return font color
         */
	public Color getFontColor() {
		return currentColors.get(2);
	}
        
        /**
         * 
         * @return second color
         */
	public Color getSecondFontColor() {
		return currentColors.get(3);
	}
	
        /**
         * reset the default theme
         * @param list list of colors
         */
	public void setDefaultColors(ArrayList<Color> list) {
		if(defaultColors==null) {
			defaultColors = list;
		}
	}
}
