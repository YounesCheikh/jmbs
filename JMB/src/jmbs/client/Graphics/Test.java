package jmbs.client.Graphics;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JFrame;

import jmbs.common.User;
import jmbs.server.Connect;
import jmbs.server.PictureDAO;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frm = new JFrame();
		
		Connection con = new Connect().getConnection();
		
		PictureDAO pdao = new PictureDAO(con);
		
		Image img = pdao.getPicture(User.DEFAULT_PICTURE);

		frm.setContentPane(new ImagePanel(img));
		frm.setSize(100,100);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setVisible(true);
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
