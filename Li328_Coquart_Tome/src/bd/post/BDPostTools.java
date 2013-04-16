package bd.post;

import bd.BDTools;
import bd.DataBase;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

/**
 * Boite a outils pour BD.Post
 * 
 * @author Kevin & Pietro
 * 
 */
public class BDPostTools {
	private static String bd = "tweet";

	/**
	 * Verifie si le statut appartient a id
	 * 
	 * @param id
	 * @param id_message
	 * @return vrai si id est l'auteur
	 */
	public static boolean SonStatut(int id, String id_message) {
		DBObject obj = BDTools.ObjectID(id_message);
		obj.put("author_id", id);

		DBCollection coll = DataBase.getMongoCollection(bd);
		DBCursor c = coll.find(obj);

		if (c.hasNext())
			return true;
		return false;
	}
}
