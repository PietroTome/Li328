package bd.post;

import org.json.JSONException;
import org.json.JSONObject;

import bd.BDTools;
import bd.DataBase;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

/**
 * Contient les op�rations pour la BD pour lire un/des message
 * 
 * @author Kevin et Pietro
 * 
 */
public class BDPostRead {
	private static String bd = "tweet";

	/**
	 * Affiche tous les statuts
	 * 
	 * @return Tout les statuts existant
	 * @throws JSONException
	 */
	public static JSONObject afficherStatut() throws JSONException {
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
	 * Affiche les statuts d'id
	 * 
	 * @param id
	 * @return Tout les statuts d'id
	 * @throws JSONException
	 */
	public static JSONObject afficherStatutDe(int id) throws JSONException {
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
	 * Affiche les statuts de login
	 * 
	 * @param login
	 * @return Tous les statuts de login
	 * @throws JSONException
	 */
	public static JSONObject afficherStatutDe(String login)
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
	 * Lit le statut id_message
	 * 
	 * @param id_message
	 * @return un JSON dut message
	 * @throws JSONException
	 */
	public static JSONObject lireStatut(String id_message) throws JSONException {
		DBCollection coll = DataBase.getMongoCollection(bd);
		return new JSONObject(BDTools.findOne(coll, id_message).toString());
	}
}
