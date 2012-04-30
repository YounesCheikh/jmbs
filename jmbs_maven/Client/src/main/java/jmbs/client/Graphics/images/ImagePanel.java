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
 * @author Younes CHEIKH http://cyounes.com
 * @author Benjamin Babic http://bbabic.com
 * 
 */
package jmbs.client.Graphics.images;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1101259239413058291L;
	Image image = null;
	ImageIcon thumbnail = null;

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

		if (width > 0) {
			if (image.getWidth(null) > width) {
				thumbnail = new ImageIcon(image.getScaledInstance(width, -1,
						Image.SCALE_DEFAULT));
			}
		}
		if (height > 0) {
			if (image.getHeight(null) > height) {
				thumbnail = new ImageIcon(thumbnail.getImage()
						.getScaledInstance(-1, height, Image.SCALE_DEFAULT));
			}
		}
		image = thumbnail.getImage();
	}

	public ImagePanel(BufferedImage bImage) {
		image = bImage;
	}

	public ImagePanel(BufferedImage bImage, int width, int height) {
		image = bImage;
		if (bImage != null) {
			if (width > 0) {
				if (image.getWidth(null) > width) {
					thumbnail = new ImageIcon(image.getScaledInstance(width,
							-1, Image.SCALE_DEFAULT));
				}
			}
			if (height > 0) {
				if (image.getHeight(null) > height) {
					thumbnail = new ImageIcon(thumbnail.getImage()
							.getScaledInstance(-1, height, Image.SCALE_DEFAULT));
				}
			}
			image = thumbnail.getImage();
		}

	}

	public ImagePanel(ImageIcon imico) {
		image = imico.getImage();
	}

	public ImagePanel(ImageIcon imico, int width, int height) {
		image = imico.getImage();
		if (imico != null) {
			if (width > 0) {
				if (image.getWidth(null) > width) {
					thumbnail = new ImageIcon(image.getScaledInstance(width,
							-1, Image.SCALE_DEFAULT));
				}
			}
			if (height > 0) {
				if (image.getHeight(null) > height) {
					thumbnail = new ImageIcon(thumbnail.getImage()
							.getScaledInstance(-1, height, Image.SCALE_DEFAULT));
				}
			}
			image = thumbnail.getImage();
		}

	}

	public ImagePanel(Image ima) {
		image = ima;
	}

	public ImagePanel(Image ima, int width, int height) {
		image = ima;
		if (ima != null) {
			if (width > 0) {
				if (image.getWidth(null) > width) {
					thumbnail = new ImageIcon(image.getScaledInstance(width,
							-1, Image.SCALE_DEFAULT));
				}
			}
			if (height > 0) {
				if (image.getHeight(null) > height) {
					thumbnail = new ImageIcon(thumbnail.getImage()
							.getScaledInstance(-1, height, Image.SCALE_DEFAULT));
				}
			}
			image = thumbnail.getImage();
		}
	}

	public void setImage(Image im) {
		this.image = im;
		repaint();
	}

	public void setImage(ImageIcon im) {
		this.image = im.getImage();
		repaint();
	}

	public void setImage(BufferedImage im) {
		this.image = im;
		repaint();
	}

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

	public void setImage(Image im, int width, int height) {
		this.image = im;
		if (im != null) {
			if (width > 0) {
				if (image.getWidth(null) > width) {
					thumbnail = new ImageIcon(image.getScaledInstance(width,
							-1, Image.SCALE_DEFAULT));
				}
			}
			if (height > 0) {
				if (image.getHeight(null) > height) {
					thumbnail = new ImageIcon(thumbnail.getImage()
							.getScaledInstance(-1, height, Image.SCALE_DEFAULT));
				}
			}
			image = thumbnail.getImage();
			repaint();
		}

	}

	public void setImage(ImageIcon im, int width, int height) {
		this.image = im.getImage();
		if (im != null) {
			if (width > 0) {
				if (image.getWidth(null) > width) {
					thumbnail = new ImageIcon(image.getScaledInstance(width,
							-1, Image.SCALE_DEFAULT));
				}
			}
			if (height > 0) {
				if (image.getHeight(null) > height) {
					thumbnail = new ImageIcon(thumbnail.getImage()
							.getScaledInstance(-1, height, Image.SCALE_DEFAULT));
				}
			}
			image = thumbnail.getImage();
			repaint();
		}
	}

	public void setImage(BufferedImage im, int width, int height) {
		this.image = im;
		if (im != null) {
			if (width > 0) {
				if (image.getWidth(null) > width) {
					thumbnail = new ImageIcon(image.getScaledInstance(width,
							-1, Image.SCALE_DEFAULT));
				}
			}
			if (height > 0) {
				if (image.getHeight(null) > height) {
					thumbnail = new ImageIcon(thumbnail.getImage()
							.getScaledInstance(-1, height, Image.SCALE_DEFAULT));
				}
			}
			image = thumbnail.getImage();
			repaint();
		}
	}

	public void setImage(String im, int width, int height) {
		try {
			this.image = ImageIO.read(new File(im));
			if (im != null) {
				if (width > 0) {
					if (image.getWidth(null) > width) {
						thumbnail = new ImageIcon(image.getScaledInstance(
								width, -1, Image.SCALE_DEFAULT));
					}
				}
				if (height > 0) {
					if (image.getHeight(null) > height) {
						thumbnail = new ImageIcon(thumbnail.getImage()
								.getScaledInstance(-1, height,
										Image.SCALE_DEFAULT));
					}
				}
				image = thumbnail.getImage();
				repaint();
			}
		} catch (IOException e) {
			try {
				image = ImageIO.read(getClass().getResource(im));
				if (im != null) {
					if (width > 0) {
						if (image.getWidth(null) > width) {
							thumbnail = new ImageIcon(image.getScaledInstance(
									width, -1, Image.SCALE_DEFAULT));
						}
					}
					if (height > 0) {
						if (image.getHeight(null) > height) {
							thumbnail = new ImageIcon(thumbnail.getImage()
									.getScaledInstance(-1, height,
											Image.SCALE_DEFAULT));
						}
					}
					image = thumbnail.getImage();
					repaint();
				}
			} catch (IOException ioe) {
				System.out.println("Error:" + ioe.getMessage());
			}
		}

	}

	public void paint(Graphics g) {
		super.paintComponent(g);
		if (image != null)
			g.drawImage(image, 0, 0, null);
	}
}