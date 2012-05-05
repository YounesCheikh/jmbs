package jmbs.client.cache;

import java.io.File;
import java.util.ArrayList;

import jmbs.client.DataTreatment.ImageTreatment;
import jmbs.common.Message;

public class CacheMsgRequests {

	private static Connect con;

	public CacheMsgRequests() {

	}

	/******************************************************************/
	/************************* MESSAGES *******************************/
	/******************************************************************/
	public void addMessage(Message m) {
		con = new Connect();
		File f = new File("upics/+" + m.getOwner().getId() + ".jpg");
		if (!f.exists()) {
			ImageTreatment.exportPicture(m.getOwner().getId(),
					ImageTreatment.convert(m.getOwner().getPic()), "jpg");
		}
		MsgDAO mdao = new MsgDAO(con.getConnection());
		mdao.insertMessage(m);
		con.close();
	}

	public ArrayList<Message> getMessages() {
		con = new Connect();
		MsgDAO mdao = new MsgDAO(con.getConnection());

		ArrayList<Message> retList = mdao.getMessages();

		con.close();
		return retList;
	}

	public static void removeAllMsgs() {
		con = new Connect();
		MsgDAO mdao = new MsgDAO(con.getConnection());
		mdao.deleteAll();
		con.close();
	}

}
