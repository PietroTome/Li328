package bd.mp;

import org.json.JSONException;
import org.json.JSONObject;

import bd.BDException;
import bd.BDTools;
import bd.DataBase;
import bd.user.BDUserTools;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

/**
 * Gere cote BD la lecture et l'affichage de MP
 * 
 * @author Kevin & Pietro
 * 
 */
public class BDMPRead {
	private static String bd = "mp";

	/**
	 * Renvoie le mp desire
	 * 
	 * @param clef
	 * @param id_mp
	 * @return Un JSON contenant le mp d'_id id_mp
	 * @throws JSONException
	 */
	public static JSONObject lireMP(String clef, String id_message)
			throws JSONException {
		DBCollection coll = DataBase.getMongoCollection(bd);
		DBObject obj = BDTools.findOne(coll, id_message);
		obj.put("statut", "Lu");
		coll.findAndModify(BDTools.findOne(coll, id_message), obj);
		return new JSONObject(obj.toString());
	}

	/**
	 * Affiche les mp recus
	 * 
	 * @param clef
	 * @param non_lu
	 *            Si vrai on affiche que les messages non lu
	 * @return Tous ces mp (ou tous ces mp non lu)
	 * @throws JSONException
	 * @throws BDException
	 */
	public static JSONObject afficherMP(String clef, boolean non_lu)
			throws JSONException, BDException {
		DBObject obj = new BasicDBObject();
		obj.put("author_id", BDUserTools.getId(clef));
		if (non_lu)
			obj.put("statut", "Non lu");

		DBCollection coll = DataBase.getMongoCollection(bd);
		DBCursor c = coll.find(obj);

		JSONObject js = new JSONObject();
		int i = 0;
		while (c.hasNext()) {
			js.put("message " + i, new JSONObject(c.next().toString()));
			i++;
		}
		return js;
	}
}