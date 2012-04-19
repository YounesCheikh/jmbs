/**
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
 * @author Younes CHEIKH http://cyounes.com
 * @author Benjamin Babic http://bbabic.com
 * 
 */
package jmbs.client.Graphics;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	String filePath;

	public ImagePanel(String fileImage) {
		this.filePath = fileImage;
	}

	public ImagePanel(String fileImage, int width, int height) {
		this.filePath = fileImage;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 5118025531286392963L;

	public void paintComponent(Graphics g) {
		ImageIcon img = new ImageIcon(
				getClass().getResource(this.filePath));
		img.paintIcon(this, g, 0, 0);
	}
}
