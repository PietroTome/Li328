package bd.image;

import bd.DataBase;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

/**
 * Contient les outils pour la BD image
 * 
 * @author Kevin et Pietro
 * 
 */
public class BDImageTools {
	private static String bd = "image";
	
	public static String getObjId(String url) {
		DBObject obj = new BasicDBObject();
		obj.put("url", url);

		DBCollection coll = DataBase.getMongoCollection(bd);
		DBCursor c = coll.find(obj);
		return c.next().get("_id").toString();
	}

}
