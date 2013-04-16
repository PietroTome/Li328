package servlet.comments;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.comments.CommentsRead;
import servlet.ParametreException;

/**
 * Servlet pour afficher les commentaires globaux ou bien ceux d'un utilisateur
 * donné
 * 
 * @author Kevin & Pietro
 * 
 */
public class AfficherComments extends HttpServlet {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse rep) {
		try {
			String clef = req.getParameter("clef"), idS = req
					.getParameter("id"), login = req.getParameter("login");
			if (clef == null)
				throw new ParametreException("Un parametre n'est pas affecte");

			rep.setContentType("text/plain");
			PrintWriter out = rep.getWriter();

			if (idS != null) {
				int id = Integer.parseInt(idS);
				out.println(CommentsRead.afficherCommentsDe(clef, id));
			} else if (login != null)
				out.println(CommentsRead.afficherCommentsDe(clef, login));
			else
				out.println(CommentsRead.afficherComments(clef));

		} catch (Exception e) {
			e.printStackTrace();
			rep.setContentType("text/plain");
			PrintWriter out;
			try {
				out = rep.getWriter();
				out.println(e.getMessage());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
