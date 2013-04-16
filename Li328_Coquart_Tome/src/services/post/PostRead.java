package services.post;

import org.json.JSONObject;

import services.ServicesTools;
import bd.BDTools;
import bd.post.BDPostRead;
import bd.user.BDUserTools;

/**
 * Services de lecture sur les posts
 * 
 * @author Kevin et Pietro
 * 
 */
public class PostRead {

	/**
	 * 
	 * @param clef
	 * @return un JSON contenant tout les messages de la BD
	 */
	public static JSONObject afficherStatut(String clef) {
		try {
			if (!BDUserTools.checkClef(clef))
				return ServicesTools.JSONerreur("Clef incorrecte", 2);

			return BDPostRead.afficherStatut();
		} catch (Exception e) {
			return ServicesTools.JSONBDerreur(e.getMessage());
		}
	}

	/**
	 * 
	 * @param clef
	 * @param id
	 * @return un JSON contenant tout les messages d'id
	 */
	public static JSONObject afficherStatutDe(String clef, int id) {
		try {
			if (!BDUserTools.checkClef(clef))
				return ServicesTools.JSONerreur("Clef incorrecte", 2);

			return BDPostRead.afficherStatutDe(id);
		} catch (Exception e) {
			return ServicesTools.JSONBDerreur(e.getMessage());
		}
	}

	/**
	 * 
	 * @param clef
	 * @param login
	 * @return un JSON contenant tout les messages de login
	 */
	public static JSONObject afficherStatutDe(String clef, String login) {
		try {
			if (!BDUserTools.checkClef(clef))
				return ServicesTools.JSONerreur("Clef incorrecte", 2);

			return BDPostRead.afficherStatutDe(login);
		} catch (Exception e) {
			return ServicesTools.JSONBDerreur(e.getMessage());
		}
	}

	/**
	 * Lis le statut id_message
	 * 
	 * @param clef
	 * @param id_obj
	 * @return
	 */
	public static JSONObject lireStatut(String clef, String id_obj) {
		try {
			if (!BDUserTools.checkClef(clef))
				return ServicesTools.JSONerreur("Clef incorrecte", 2);
			if (!BDTools.ObjetExiste("tweet", id_obj))
				return ServicesTools.JSONerreur("message introuvable", 3);

			return BDPostRead.lireStatut(id_obj);
		} catch (Exception e) {
			return ServicesTools.JSONBDerreur(e.getMessage());
		}
	}

}
