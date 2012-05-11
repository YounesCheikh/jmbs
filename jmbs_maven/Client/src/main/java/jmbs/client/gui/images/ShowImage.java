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

package jmbs.client.gui.images;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

/* ImagePreview.java by FileChooserDemo2.java. */
public class ShowImage extends JComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8845938950021284222L;
	ImageIcon thumbnail = null;
	Image img = null;

	public ShowImage(Image i) {
		img = i;
	}

	public void loadImage() {
		if (img == null) {
			thumbnail = null;
			return;
		}

		// Don't use createImageIcon (which is a wrapper for getResource)
		// because the image we're trying to load is probably not one
		// of this program's own resources.
		ImageIcon tmpIcon = new ImageIcon(img);
		if (tmpIcon != null) {
			if (tmpIcon.getIconWidth() > 90) {
				thumbnail = new ImageIcon(tmpIcon.getImage().getScaledInstance(
						90, -1, Image.SCALE_DEFAULT));
			} else { // no need to miniaturize
				thumbnail = tmpIcon;
			}
		}
	}

	public void propertyChange(boolean update) {
		// Update the preview accordingly.
		if (update) {
			thumbnail = null;
			if (isShowing()) {
				loadImage();
				repaint();
			}
		}
	}

	protected void paintComponent(Graphics g) {
		if (thumbnail == null) {
			loadImage();
		}
		if (thumbnail != null) {
			int x = getWidth() / 2 - thumbnail.getIconWidth() / 2;
			int y = getHeight() / 2 - thumbnail.getIconHeight() / 2;

			if (y < 0) {
				y = 0;
			}

			if (x < 5) {
				x = 5;
			}
			thumbnail.paintIcon(this, g, x, y);
		}
	}
}
