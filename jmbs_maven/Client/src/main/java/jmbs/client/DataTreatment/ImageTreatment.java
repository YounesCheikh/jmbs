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
