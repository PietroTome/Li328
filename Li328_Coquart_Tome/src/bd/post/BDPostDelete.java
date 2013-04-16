package bd.post;

import bd.BDException;
import bd.BDTools;
import bd.DataBase;
import bd.comments.BDCommentsDelete;
import bd.like.BDLikeDelete;
import bd.user.BDUserTools;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

/**
 * Supprime les statuts de la base
 * 
 * @author Kevin & Pietro
 * 
 */
public class BDPostDelete {
	private static String bd = "tweet";

	/**
	 * Suppimer les statuts de clef
	 * 
	 * @param clef
	 * @throws BDException
	 */
	public static void supprimerCesStatut(String clef) throws BDException {
		int id = BDUserTools.getId(clef);
		DBObject obj = new BasicDBObject();
		obj.put("author_id", id);

		DBCollection coll = DataBase.getMongoCollection(bd);
		DBCursor cursor = coll.find(obj);

		// Suppresion des commentaires associé
		while (cursor.hasNext()) {
			DBObject o = cursor.next();
			BDCommentsDelete.supprimerCommentsDuTWEET(o.get("_id").toString());
			coll.remove(o);
		}
	}

	/**
	 * Supprimer le statut id_message
	 * 
	 * @param id_message
	 */
	public static void supprimerStatut(String id_message) {
		DBCollection coll = DataBase.getMongoCollection(bd);
		DBObject o = BDTools.findOne(coll, id_message);
		// Supression des commentaires
		BDCommentsDelete.supprimerCommentsDuTWEET(id_message);
		BDLikeDelete.supprimerLikeDuTWEET(id_message);
		coll.remove(o);
	}

}
