package bd.admin;

import bd.DataBase;

import com.mongodb.DBCollection;

/**
 * Outils administrateur niveau BD
 * 
 * @author Kevin & Pietro
 * 
 */
public class BDAdmin {
	private static String bd = "tweet,image,comments,mp,like";

	/**
	 * Supprime toutes les MongoDB
	 */
	public static void viderMongoDB() {
		String[] tmp = bd.split(",");
		for (int i = 0; i < tmp.length; i++) {
			DBCollection coll = DataBase.getMongoCollection(tmp[i]);
			coll.drop();
		}
	}
}
