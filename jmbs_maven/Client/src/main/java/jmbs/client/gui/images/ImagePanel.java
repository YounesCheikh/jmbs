/*
 * Copyright (c) 2012. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright 
 * notice, this list of conditions and the following disclaimer in 
 * the documentation and/or other materials provided with the distribution.
 *
 * 3. The names of the authors may not be used to endorse or promote products
 * derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL JCRAFT,
 * INC. OR ANY CONTRIBUTORS TO THIS SOFTWARE BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
 * OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package jmbs.client.gui.images;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import jmbs.client.dataTreatment.ImageTreatment;

/**
 * This class used to display image easily in another jPanel.
 * 
 * @author <a target="_blank" href="cyounes.com/contact">Younes CHEIKH</a>
 * @author Babic Benjamin
 * @version 1.2
 */
public class ImagePanel extends JPanel {

	private static final long serialVersionUID = 1101259239413058291L;

	private Image image = null;

	private ImageTreatment imageTreatment = new ImageTreatment();

	/**
	 * Instantiates a new image panel.
	 * 
	 * @param imgFilePath
	 *            the img file path
	 */
	public ImagePanel(String imgFilePath) {
		try {
			File input = new File(imgFilePath);
			image = ImageIO.read(input);
		} catch (IOException ie) {
			try {
				image = ImageIO.read(getClass().getResource(imgFilePath));
			} catch (IOException ioe) {
				System.out.println("Error:" + ie.getMessage());
			}
		}
	}

	/**
	 * Instantiates a new image panel.
	 * 
	 * @param imgFilePath
	 *            the img file path
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 */
	public ImagePanel(String imgFilePath, int width, int height) {
		try {
			File input = new File(imgFilePath);
			image = ImageIO.read(input);
		} catch (IOException ie) {
			try {
				image = ImageIO.read(getClass().getResource(imgFilePath));
			} catch (IOException ioe) {
				System.out.println("Error:" + ie.getMessage());
			}
		}

	}

	/**
	 * Instantiates a new image panel.
	 * 
	 * @param bImage
	 *            the b image
	 */
	public ImagePanel(BufferedImage bImage) {
		image = bImage;
	}

	/**
	 * Instantiates a new image panel.
	 * 
	 * @param bImage
	 *            the bufferedImage
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 */
	public ImagePanel(BufferedImage bImage, int width, int height) {
		image = bImage;
		if (bImage != null) {
			image = imageTreatment.scale(image, width, height);
		}

	}

	/**
	 * Instantiates a new image panel.
	 * 
	 * @param imico
	 *            the received image
	 */
	public ImagePanel(ImageIcon imico) {
		image = imico.getImage();
	}

	/**
	 * Instantiates a new image panel.
	 * 
	 * @param imico
	 *            the imageIcon
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 */
	public ImagePanel(ImageIcon imico, int width, int height) {
		
		if (imico != null) {
			image = imico.getImage();
			image = imageTreatment.scale(image, width, height);
		}

	}

	/**
	 * Instantiates a new image panel.
	 * 
	 * @param ima
	 *            the image
	 */
	public ImagePanel(Image ima) {
		image = ima;
	}

	/**
	 * Instantiates a new image panel.
	 * 
	 * @param ima
	 *            the image
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 */
	public ImagePanel(Image ima, int width, int height) {
		image = ima;
		if (ima != null) {
			image = imageTreatment.scale(image, width, height);
		}
	}

	/**
	 * Sets the image.
	 * 
	 * @param im
	 *            the new image
	 */
	public void setImage(Image im) {
		this.image = im;
		repaint();
	}

	/**
	 * Sets the image.
	 * 
	 * @param im
	 *            the new image
	 */
	public void setImage(ImageIcon im) {
		this.image = im.getImage();
		repaint();
	}

	/**
	 * Sets the image.
	 * 
	 * @param im
	 *            the new image
	 */
	public void setImage(BufferedImage im) {
		this.image = im;
		repaint();
	}

	/**
	 * Sets the image.
	 * 
	 * @param im
	 *            the new image
	 */
	public void setImage(String im) {
		try {
			this.image = ImageIO.read(new File(im));
			this.repaint();
		} catch (IOException e) {
			try {
				image = ImageIO.read(getClass().getResource(im));
				this.repaint();
			} catch (IOException ioe) {
				System.out.println("Error:" + ioe.getMessage());
			}
		}

	}

	/**
	 * Sets the image.
	 * 
	 * @param im
	 *            the im
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 */
	public void setImage(Image im, int width, int height) {
		this.image = im;
		if (im != null) {
			image = imageTreatment.scale(image, width, height);
			repaint();
		}

	}

	/**
	 * Set the image from ImageIcon and display it.
	 * 
	 * @param im
	 *            the image
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 */
	public void setImage(ImageIcon im, int width, int height) {
		if (im != null) {
			this.image = im.getImage();
			image = imageTreatment.scale(image, width, height);
			repaint();
		}
	}

	/**
	 * Set the image from BufferedImage and display it.
	 * 
	 * @param im
	 *            the image
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 */
	public void setImage(BufferedImage im, int width, int height) {
		this.image = im;
		if (im != null) {
			image = imageTreatment.scale(image, width, height);
			repaint();
		}
	}

	/**
	 * Set the image from path and display it.
	 * 
	 * @param im
	 *            the image
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 */
	public void setImage(String im, int width, int height) {
		try {
			this.image = ImageIO.read(new File(im));
			if (im != null) {
				image = imageTreatment.scale(image, width, height);
				repaint();
			}
		} catch (IOException e) {
			try {
				image = ImageIO.read(getClass().getResource(im));
				if (im != null) {
					image = imageTreatment.scale(image, width, height);
					repaint();
				}
			} catch (IOException ioe) {
				System.out.println("Error:" + ioe.getMessage());
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g) {
		super.paintComponent(g);
		if (image != null)
			g.drawImage(image, 0, 0, null);
	}

	// private void scale(int width, int height) {
	//
	// int min = image.getWidth(null) < image.getHeight(null) ? image
	// .getWidth(null) : image.getHeight(null);
	// Image im = createImage(new FilteredImageSource(image.getSource(),
	// new CropImageFilter(0, 0, min, min)));
	// image = im;
	// if (image.getWidth(null) > width || image.getHeight(null) > height) {
	// thumbnail = new ImageIcon(image.getScaledInstance(width, height,
	// Image.SCALE_SMOOTH));
	// }
	// }
}