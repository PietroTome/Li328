package bd.like;

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
 * Gere l'envoie de commentaire � la bd
 * 
 * @author Kevin & Pietro
 * 
 */
public class BDLikeSend {
	private static String bd = "like";

	/**
	 * Ajoute un like � un message donn�
	 * 
	 * @param clef
	 * @param id_tweet
	 * @throws BDException
	 */
	@SuppressWarnings("deprecation")
	public static void ajouterLike(String clef, String id_tweet)
			throws BDException {
		// Ajout du commentaire dans la base comments
		GregorianCalendar calendar = new GregorianCalendar();
		Date date = calendar.getTime();

		DBCollection coll = DataBase.getMongoCollection(bd);
		DBObject obj = new BasicDBObject();

		obj.put("id_tweet", id_tweet);
		obj.put("author_id", BDUserTools.getId(clef));
		obj.put("author_login", BDUserTools.getLogin(clef));
		obj.put("date", date);
		obj.put("like", "+1");

		coll.insert(obj);

		// ***
		// ***
		// Ajout de l'id du commentaire au message pere
		String id_like = BDLikeTools.getObjID(BDUserTools.getId(clef), date);

		DBCollection coll_tweet = DataBase.getMongoCollection("tweet");
		DBObject o_tweet = BDTools.findOne(coll_tweet, id_tweet);
		if (o_tweet.containsKey("like")
				&& !o_tweet.get("like").toString().equals(""))
			o_tweet.put("like", o_tweet.get("like").toString() + "," + id_like);
		else
			o_tweet.put("like", id_like);

		coll_tweet
				.findAndModify(BDTools.findOne(coll_tweet, id_tweet), o_tweet);
	}

	/**
	 * Ajoute un unlike � un message donn�
	 * 
	 * @param clef
	 * @param id_tweet
	 * @throws BDException
	 */
	@SuppressWarnings("deprecation")
	public static void ajouterUnlike(String clef, String id_tweet)
			throws BDException {
		// Ajout du commentaire dans la base comments
		GregorianCalendar calendar = new GregorianCalendar();
		Date date = calendar.getTime();

		DBCollection coll = DataBase.getMongoCollection(bd);
		DBObject obj = new BasicDBObject();

		obj.put("id_tweet", id_tweet);
		obj.put("author_id", BDUserTools.getId(clef));
		obj.put("author_login", BDUserTools.getLogin(clef));
		obj.put("date", date);
		obj.put("like", "-1");

		coll.insert(obj);

		// ***
		// ***
		// Ajout de l'id du commentaire au message pere
		String id_like = BDLikeTools.getObjID(BDUserTools.getId(clef), date);

		DBCollection coll_tweet = DataBase.getMongoCollection("tweet");
		DBObject o_tweet = BDTools.findOne(coll_tweet, id_tweet);
		if (o_tweet.containsKey("like"))
			o_tweet.put("like", o_tweet.get("like").toString() + "," + id_like);
		else
			o_tweet.put("like", id_like);

		coll_tweet
				.findAndModify(BDTools.findOne(coll_tweet, id_tweet), o_tweet);
	}
}
