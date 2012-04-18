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

package jmbs.server;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;

import javax.imageio.ImageIO;

import jmbs.common.User;

public class PictureDAO extends DAO {
	private static final long serialVersionUID = -3486687583564090822L;
	private final static String DEFAULT_SEPARATOR = "/";
	private final static String DEFAULT_PATH = ".";
	private final static String DEFAULT_IMAGE_FORMAT = "jpg";
	
	public PictureDAO(Connection c) {
		super(c);
	}
	
	/**
	 * Retruned image can be null ! 
	 * @param path
	 * @return
	 */
	public BufferedImage getPicture(String path){
		BufferedImage img = null;
		
		try {
			img = ImageIO.read(new File(path));
		} catch (IOException e0) {
			System.err.println("Error on filepath no files named: " + path);
		} 
		
		return img;
	}

	public String getRepertoryPath(int userid){
		return (DEFAULT_PATH + DEFAULT_SEPARATOR + String.valueOf(userid) + DEFAULT_SEPARATOR);
	}
	
	public String getAvatarPath(int userid, String name){
		return (getRepertoryPath(userid) + name);
	}
	
	public String getName(String path){
		return path.substring(path.lastIndexOf(DEFAULT_SEPARATOR)+1);
	}
	
	
	public BufferedImage getAvatar(int iduser, String pic){
		return (getPicture(getAvatarPath(iduser,pic)));
	}
	
	public BufferedImage getAvatar(User u){
		return (getAvatar(u.getId(),u.getPic()));
	}
	

	public boolean setAvatar(int userid, BufferedImage img, String nom, boolean overwrite){
		String path = getRepertoryPath(userid) + nom;
		createUserRepertory(userid);
		boolean ret = false;
		
		File f = new File(path);
		
		if (!f.exists() || overwrite){
			
		
			try {
				ImageIO.write(img, DEFAULT_IMAGE_FORMAT, f);
				ret = true;
			} catch (IOException e) {
				System.out.println("Image cannot be saved.");
			}
		}
		
		return ret;
	}
	

	/**
	 * Creates the image on the server. if the user repertoire doesn't exist it will create it.
	 * 
	 * @param u User who uploads the image
	 * @param img Image to upload
	 * @param nom Image name
	 * @param overwrite true to overwrite existing file false to return false if file already exists.
	 * @return true if the image was saved - false if not.
	 */
	public boolean setAvatar(User u, BufferedImage img, String nom, boolean overwrite){
		return setAvatar(u.getId(),img,nom,overwrite);
	}


	

	public boolean createUserRepertory(User u){
		return createUserRepertory(u.getId());
	}
	

	public boolean createUserRepertory(int userid){
		File f = new File(getRepertoryPath(userid));
		boolean ret = false;
		
		if (!f.exists()){
			ret = f.mkdir();
		}
		
		return ret;
	}
	
	public boolean deleteUserRepertory(User u){
		return deleteUserRepertory(u.getId());
	}
	
	public boolean deleteUserRepertory(int userid){
		boolean ret = false;
		File f = new File(getRepertoryPath(userid));
		
		if (f.isDirectory()){
			File[] subfiles = f.listFiles();
			String subfileName = new String ();
		
			for (File i:subfiles){
				subfileName = i.getName();
				i.delete();
				System.out.println(subfileName + " deleted.");
			}
			if (ret=f.delete()) System.out.println(f.getName() + " deleted.");
		}else {
			System.err.println("This user has no avatar repertory.");
		}
		
		return ret;
	}

}
