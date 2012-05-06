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

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageTreatment {

	public ImageTreatment() {

	}

	public static BufferedImage convert(byte[] ib) {
		BufferedImage im = null;
		try {
			im = ImageIO.read(new ByteArrayInputStream(ib));
		} catch (IOException e) {
		}
		return im;
	}

	public static byte[] pathToByte(String path) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			BufferedImage bi = ImageIO.read(new File(path));
			ImageIO.write(bi, "jpg", baos);
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			baos.close();
			return imageInByte;
		} catch (IOException ex) {
			return null;
		}
	}

	public static BufferedImage byteToImage(byte[] imageInByte) {
		try {
			BufferedImage image = ImageIO.read(new ByteArrayInputStream(
					imageInByte));
			return image;
		} catch (IOException ex) {
			return null;
		}
	}

	public static boolean exportPicture(int userid, BufferedImage img,
			String format) {
		boolean ret = false;
		File f = new File("./upics/" + userid + ".jpg");

		try {
			ImageIO.write(img, format, f);
			ret = true;
		} catch (IOException e) {
			ret = false;
			System.out.println("Image cannot be saved.");
		}

		return ret;
	}

}
