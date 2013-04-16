package bd.mp;

import bd.BDTools;
import bd.DataBase;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

/**
 * Gestion mp cote BD
 * 
 * @author Kevin & Pietro
 * 
 */
public class BDMPTools {
	private static String bd = "mp";

	/**
	 * Verifie si le mp appartient a id
	 * 
	 * @param id
	 * @param id_message
	 * @return vrai si id est le destinataire
	 */
	public static boolean SonMP(int id, String id_message) {
		DBObject obj = BDTools.ObjectID(id_message);
		obj.put("id_dest", id);

		DBCollection coll = DataBase.getMongoCollection(bd);
		DBCursor c = coll.find(obj);

		if (c.hasNext())
			return true;
		return false;
	}
}
