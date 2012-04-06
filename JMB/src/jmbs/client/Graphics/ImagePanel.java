
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
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
 
public class ImagePanel extends JPanel {
	String filePath;
	private int w = 0;
	private int h = 0; /*w: Width ; h : Height */
	private Image imm ;
	public ImagePanel(String fileImage) {
		this.filePath = fileImage;
	}
	
	public ImagePanel(Image im) {
		this.imm = im;
	}
	
	public ImagePanel(String fileImage, int width, int height) {
		this.filePath = fileImage;
		this.w = width;
		this.h = height;
	}
 
        /**
	 * 
	 */
	private static final long serialVersionUID = 5118025531286392963L;

		public void paintComponent(Graphics g){
                try {
                	Image img;
                		if (imm==null) {
                			img = ImageIO.read(new File(this.filePath));
                		}
                		else {
                			img = imm;
                		}
                        if (this.w == 0 || this.h==0)
                        	g.drawImage(img, 0, 0,img.getWidth(null),img.getHeight(null), this);
                        else 
                        	g.drawImage(img, 0, 0,w,h, this);
                        //Pour une image de fond
                        //g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
                } catch (IOException e) {
                        // TODO Auto-generated catch block
                        //e.printStackTrace();
                	System.err.println("file of image not found\n"+this.filePath);
                }
                
        }
		
		
}

