package bd.post;

import java.util.Date;
import java.util.GregorianCalendar;

import bd.BDException;
import bd.BDTools;
import bd.DataBase;
import bd.user.BDUserTools;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

/**
 * Contient les operations pour la BD pour poster un message
 * 
 * @author Kevin et Pietro
 * 
 */
public class BDPostSend {
	private static String bd = "tweet";

	/**
	 * Post un message dans la BD
	 * 
	 * @param clef
	 * @param message
	 * @throws BDException
	 */
	public static void posterStatut(String clef, String message)
			throws BDException {
		GregorianCalendar calendar = new GregorianCalendar();
		Date date = calendar.getTime();

		DBCollection coll = DataBase.getMongoCollection(bd);
		DBObject obj = new BasicDBObject();

		obj.put("author_id", BDUserTools.getId(clef));
		obj.put("author_login", BDUserTools.getLogin(clef));
		obj.put("date", date);
		obj.put("text", message);

		coll.insert(obj);
	}

	/**
	 * Post un message dans la BD
	 * 
	 * @param clef
	 * @param message
	 * @param image_id
	 * @throws BDException
	 */
	public static void posterStatut(String clef, String message, String image_id)
			throws BDException {
		GregorianCalendar calendar = new GregorianCalendar();
		Date date = calendar.getTime();

		DBCollection coll = DataBase.getMongoCollection(bd);
		DBObject obj = new BasicDBObject();

		obj.put("author_id", BDUserTools.getId(clef));
		obj.put("author_login", BDUserTools.getLogin(clef));
		obj.put("date", date);
		obj.put("text", message);
		obj.put("photo", image_id);

		coll.insert(obj);
	}

	public static void ajouterPhoto(String id_message, String id_photo) {
		DBCollection coll = DataBase.getMongoCollection(bd);
		DBObject o_message = BDTools.findOne(coll, id_message);
		o_message.put("photo", id_photo);

		coll.findAndModify(BDTools.findOne(coll, id_message), o_message);
	}
}
