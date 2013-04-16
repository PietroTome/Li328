package bd;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

/**
 * Boite � outils pour la BD
 * 
 * @author Kevin & Pietro
 * 
 */
public class BDTools {

	/**
	 * 
	 * @param id_obj
	 *            l'id de l'objet
	 * @return Un object avec un champ id
	 */
	public static DBObject ObjectID(String id_obj) {
		DBObject obj = new BasicDBObject();
		obj.put("_id", new ObjectId(id_obj));
		return obj;
	}

	/**
	 * Trouve l'objet d'id correspondant dans la base donn�
	 * @param coll la base ou chercher
	 * @param id_obj l'id de l'objet cherch�
	 * @return l'objet qui correspond � id_obj dans la base coll
	 */
	public static DBObject findOne(DBCollection coll, String id_obj) {
		DBCursor c = coll.find(ObjectID(id_obj));
		return c.next();
	}

	/**
	 * Verifie si l'objet id_obj existe
	 * 
	 * @param id_message
	 * @return vrai si il existe
	 */
	public static boolean ObjetExiste(String bd, String id_obj) {
		if (id_obj.length() != 24)
			return false;
		DBCollection coll = DataBase.getMongoCollection(bd);
		DBCursor c = coll.find(ObjectID(id_obj));
		if (c.hasNext())
			return true;
		return false;
	}
}
