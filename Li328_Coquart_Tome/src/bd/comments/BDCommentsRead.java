package bd.comments;

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
 * Gere la lecture des commentaire cote BD
 * 
 * @author Kevin & Pietro
 * 
 */
public class BDCommentsRead {
	private static String bd = "comments";

	/**
	 * Affiche tous les commentaires
	 * 
	 * @return Tout les commentaires existant
	 * @throws JSONException
	 */
	public static JSONObject afficherComments() throws JSONException {
		DBCollection coll = DataBase.getMongoCollection(bd);
		DBCursor c = coll.find();

		JSONObject js = new JSONObject();
		int i = 0;
		while (c.hasNext()) {
			DBObject o = c.next();
			js.put("message " + i, new JSONObject(o.toString()));
			i++;
		}
		return js;
	}

	/**
	 * Affiche les commentaires d'id
	 * 
	 * @param id
	 * @return Tout les commentaires d'id
	 * @throws JSONException
	 */
	public static JSONObject afficherCommentsDe(int id) throws JSONException {
		DBObject obj = new BasicDBObject();
		obj.put("author_id", id);

		DBCollection coll = DataBase.getMongoCollection(bd);
		DBCursor c = coll.find(obj);

		JSONObject js = new JSONObject();
		int i = 0;
		while (c.hasNext()) {
			DBObject o = c.next();
			js.put("message " + i, new JSONObject(o.toString()));
			i++;
		}
		return js;
	}

	/**
	 * Affiche les commentaires de login
	 * 
	 * @param login
	 * @return Tous les commentaires de login
	 * @throws JSONException
	 */
	public static JSONObject afficherCommentsDe(String login)
			throws JSONException {
		DBObject obj = new BasicDBObject();
		obj.put("author_login", login);

		DBCollection coll = DataBase.getMongoCollection(bd);
		DBCursor c = coll.find(obj);

		JSONObject js = new JSONObject();
		int i = 0;
		while (c.hasNext()) {
			DBObject o = c.next();
			js.put("message " + i, new JSONObject(o.toString()));
			i++;
		}
		return js;
	}

	/**
	 * Lit le commentaire id_message
	 * 
	 * @param id_message
	 * @return un JSON du commentaire
	 * @throws JSONException
	 */
	public static JSONObject lireComments(String id_message)
			throws JSONException {
		DBCollection coll = DataBase.getMongoCollection(bd);
		return new JSONObject(BDTools.findOne(coll, id_message).toString());
	}

	/**
	 * Lit les commentaire du tweet
	 * 
	 * @param id_tweet
	 *            l'id du tweet
	 * @return un JSON contenant les commentairs du tweet
	 */
	@SuppressWarnings("deprecation")
	public static JSONObject lisCommentsDuTWEET(String id_tweet) {
		try {
			DBCollection coll_tweet = DataBase.getMongoCollection("tweet");
			DBObject obj = BDTools.findOne(coll_tweet, id_tweet);

			// On verifie que le tweet à des commentaires
			if (!obj.containsKey("comments"))
				return ServicesTools.JSONerreur(
						"Pas de commentaires pour ce tweet", 5);

			String id_Commments = obj.get("comments").toString();
			String[] boucle = id_Commments.split(",");

			JSONObject js = new JSONObject();

			for (int i = 0; i < boucle.length; i++) {
				js.put("commentaire n°" + i, lireComments(boucle[i]));
			}

			return js;
		} catch (JSONException e) {
			return ServicesTools.JSONBDerreur(e.getMessage());
		}
	}
}
