package bd.like;

import java.util.Date;

import bd.BDTools;
import bd.DataBase;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

/**
 * Boite à outils pour les commentaires
 * 
 * @author Kevin & Pietro
 * 
 */
public class BDLikeTools {
	private static String bd = "like";

	/**
	 * 
	 * @param author_id
	 * @param date
	 * @return l'id du like correspondant
	 */
	// Normalement l'id et la date est censé consitué une clef puisque la date
	// est précise
	public static String getObjID(int author_id, Date date) {
		DBObject obj = new BasicDBObject();
		obj.put("author_id", author_id);
		obj.put("date", date);

		DBCollection coll = DataBase.getMongoCollection(bd);
		DBCursor c = coll.find(obj);
		return c.next().get("_id").toString();
	}

	/**
	 * Verifie si le like appartient a id
	 * 
	 * @param id
	 * @param id_like
	 * @return vrai si id est l'auteur
	 */
	public static boolean SonLike(int id, String id_like) {
		DBObject obj = BDTools.ObjectID(id_like);
		obj.put("author_id", id);

		DBCollection coll = DataBase.getMongoCollection(bd);
		DBCursor c = coll.find(obj);

		if (c.hasNext())
			return true;
		return false;
	}

	/**
	 * Indique si l'utilisateur id a déjà donné son avis sur le tweet id_tweet
	 * 
	 * @param id
	 *            id de l'utilisateur
	 * @param id_tweet
	 *            id du tweet
	 * @return vrai si il a déjà donné son avis, sinon faux
	 */
	public static boolean like_deja(int id, String id_tweet) {
		DBCollection coll = DataBase.getMongoCollection("tweet");
		DBObject o = BDTools.findOne(coll, id_tweet);

		Object o_like = o.get("like");
		if (o_like == null || o_like.toString().equals(""))
			return false;

		String[] boucle = o_like.toString().split(",");

		for (int i = 0; i < boucle.length; i++) {
			DBCollection coll_like = DataBase.getMongoCollection(bd);
			DBObject tmp = BDTools.findOne(coll_like, boucle[i]);

			if (tmp.get("author_id").toString().equals(id))
				return true;
		}
		return false;
	}
}
