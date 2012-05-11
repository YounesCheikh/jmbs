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
package jmbs.client.dataTreatment;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Contains different methods to manage images
 *
 * @author <a href="http://cyounes.com/">Younes CHEIKH</a>
 * @author Benjamin Babic
 */
public class ImageTreatment extends Component {

    private static final long serialVersionUID = -2043754781570901276L;

    /**
     *
     */
    public ImageTreatment() {
    }

    /**
     * Convert byte list to BufferedImage
     *
     * @param ib the list
     * @return image converted
     */
    public static BufferedImage convert(byte[] ib) {
        BufferedImage im = null;
        try {
            im = ImageIO.read(new ByteArrayInputStream(ib));
        } catch (IOException e) {
        }
        return im;
    }

    /**
     * load image from file and convert it to list byte[]
     *
     * @param path the path to the image file
     * @return a list
     */
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

    /**
     * export image into file
     *
     * @param userid Current user
     * @param img the bufferedImage
     * @param format the format
     * @return true if export succeed
     */
    public static boolean exportPicture(int userid, BufferedImage img,
            String format) {
        boolean ret = false;
        File f = new File("./cache/upics/" + userid + ".jpg");

        try {
            ImageIO.write(img, format, f);
            ret = true;
        } catch (IOException e) {
            ret = false;
            System.out.println("Image cannot be saved.");
        }

        return ret;
    }

    /**
     * Resize image with specified size
     *
     * @param image image to resize
     * @param thumbnail thumbnail
     * @param width new width
     * @param height new height
     * @return resized image
     * @deprecated
     */
    public ImageIcon scale(Image image, ImageIcon thumbnail, int width,
            int height) {
        int min = image.getWidth(null) < image.getHeight(null) ? image.getWidth(
                null) : image.getHeight(null);
        Image im = createImage(new FilteredImageSource(image.getSource(),
                new CropImageFilter(0, 0, min, min)));
        image = im;
        if (image.getWidth(null) > width || image.getHeight(null) > height) {
            thumbnail = new ImageIcon(image.getScaledInstance(width, height,
                    Image.SCALE_SMOOTH));

        }
        return thumbnail;
    }

    /**
     * Resize image with specified size
     *
     * @param image image to resize
     * @param width new width
     * @param height new height
     * @return resized image
     */
    public BufferedImage scale(Image image, int width, int height) {
        int min = image.getWidth(null) < image.getHeight(null) ? image.getWidth(
                null) : image.getHeight(null);
        // CROP
        Image im = createImage(new FilteredImageSource(image.getSource(),
                new CropImageFilter(0, 0, min, min)));

        image = im;

        if (im.getWidth(null) > width || im.getHeight(null) > height) {
            image = im.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        }

        BufferedImage bi = new BufferedImage(image.getWidth(null),
                image.getHeight(null), BufferedImage.SCALE_SMOOTH);

        // Draw image into bufferedImage.
        Graphics2D g2 = bi.createGraphics();
        g2.drawImage(image, 0, 0, this);
        g2.dispose();
        return bi;
    }

    /**
     * convert image into a list of byte
     *
     * @param im image to convert
     * @return a list <i>byte</i>
     */
    private static byte[] convert(BufferedImage im) {
        byte[] imageInByte = null;
        if (im != null) {
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(im, "jpg", baos);

                baos.flush();
                imageInByte = baos.toByteArray();
                baos.close();
            } catch (IOException e) {
                // Logger.getLogger(PictureDAO.class.getName()).log(Level.SEVERE,
                // null, e);
            }
        }
        return imageInByte;
    }

    /**
     * Prepare image before sending to the server <br /> <ul> <li>load image
     * from file </li> <li>resize image</li> <li>convert into byte</li> </ul>
     *
     * @param path path to image file
     * @return a list
     */
    public byte[] preparedImage(String path) {

        byte[] imInByte = ImageTreatment.pathToByte(path);
        BufferedImage bi = ImageTreatment.convert(imInByte);
        bi = scale(bi, 70, 70);
        return (convert(bi));
    }
}
