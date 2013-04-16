package bd.like;

import org.json.JSONException;
import org.json.JSONObject;

import services.ServicesTools;
import bd.BDTools;
import bd.DataBase;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

/**
 * Gere la lecture des likes cote BD
 * 
 * @author Kevin & Pietro
 * 
 */
public class BDLikeRead {
	private static String bd = "like";

	/**
	 * Affiche tous les likes
	 * 
	 * @return Tout les likes existant
	 * @throws JSONException
	 */
	public static JSONObject afficherLike() throws JSONException {
		DBCollection coll = DataBase.getMongoCollection(bd);
		DBCursor c = coll.find();

		JSONObject js = new JSONObject();
		int i = 0, j = 0;
		while (c.hasNext()) {
			DBObject o = c.next();
			if (o.get("like").toString().equals("+1"))
				js.put("like " + ++i, new JSONObject(o.toString()));
			else
				js.put("unlike " + ++j, new JSONObject(o.toString()));
		}
		return js;
	}

	/**
	 * Affiche les likes d'id
	 * 
	 * @param id
	 * @return Tout les likes d'id
	 * @throws JSONException
	 */
	public static JSONObject afficherLikeDe(int id) throws JSONException {
		DBObject obj = new BasicDBObject();
		obj.put("author_id", id);

		DBCollection coll = DataBase.getMongoCollection(bd);
		DBCursor c = coll.find(obj);

		JSONObject js = new JSONObject();
		int i = 0, j = 0;
		while (c.hasNext()) {
			DBObject o = c.next();
			if (o.get("like").toString().equals("+1"))
				js.put("like " + ++i, new JSONObject(o.toString()));
			else
				js.put("unlike " + ++j, new JSONObject(o.toString()));
		}
		return js;
	}

	/**
	 * Affiche les likes de login
	 * 
	 * @param login
	 * @return Tous les likes de login
	 * @throws JSONException
	 */
	public static JSONObject afficherLikeDe(String login) throws JSONException {
		DBObject obj = new BasicDBObject();
		obj.put("author_login", login);

		DBCollection coll = DataBase.getMongoCollection(bd);
		DBCursor c = coll.find(obj);

		JSONObject js = new JSONObject();
		int i = 0, j = 0;
		while (c.hasNext()) {
			DBObject o = c.next();
			if (o.get("like").toString().equals("+1"))
				js.put("like " + ++i, new JSONObject(o.toString()));
			else
				js.put("unlike " + ++j, new JSONObject(o.toString()));
		}
		return js;
	}

	/**
	 * Lit le like id_like
	 * 
	 * @param id_like
	 * @return un JSON du like
	 * @throws JSONException
	 */
	public static JSONObject lireLike(String id_like) throws JSONException {
		DBCollection coll = DataBase.getMongoCollection(bd);
		return new JSONObject(BDTools.findOne(coll, id_like).toString());
	}

	/**
	 * Lit les likes du tweet
	 * 
	 * @param id_tweet
	 *            l'id du tweet
	 * @return un JSON contenant les likes du tweet
	 */
	@SuppressWarnings("deprecation")
	public static JSONObject lisLikeDuTWEET(String id_tweet) {
		try {
			DBCollection coll_tweet = DataBase.getMongoCollection("tweet");
			DBObject obj = BDTools.findOne(coll_tweet, id_tweet);

			// On verifie que le tweet à des likes
			if (!obj.containsKey("like"))
				return ServicesTools.JSONerreur(
						"Pas de like pour ce tweet", 5);

			String id_Likes = obj.get("like").toString();
			String[] boucle = id_Likes.split(",");

			JSONObject js = new JSONObject();

			for (int i = 0; i < boucle.length; i++) {
				js.put("like/unlike " + i, lireLike(boucle[i]));
			}

			return js;
		} catch (JSONException e) {
			return ServicesTools.JSONBDerreur(e.getMessage());
		}
	}
}
