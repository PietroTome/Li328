/**
 * Permet de switcher dans la zone connexion entre login et logout et de moduler
 * la zone pour poster un message
 */
function gererDivConnexion() {
	var user = environnement.actif;
	if (user != undefined) {
		$("#connectlog").html("<SPAN id=\"log\">" + user.login + "</SPAN>");
		$("#logout").css("visibility", "visible");
		$("#delete_compte").css("visibility", "visible");
		$("#login").css("visibility", "hidden");
		
		$("#add_comments")
				.html(
						'<form method="get" action=""><h1>Poster un message :</h1><textarea id="txt_message" name="message">Votre message</textarea><br /><input class="button" id="poster" type="submit" value="Poster" /></form>');
	} else {
		$("#connectlog").html("");
		$("#logout").css("visibility", "hidden");
		$("#delete_compte").css("visibility", "hidden");
		$("#login").css("visibility", "visible");

		$("#add_comments").html(
				'<p>Pour poster un message, il faut être connecté</p>');
	}
}

/**
 * deconnection, suppresion de l'environnement, modification de la page,
 * affichage des tweet de base
 */
function disconnect() {
	environnement.actif = undefined;
	gererDivConnexion();
	envoiMessage();
	RechercheMessage.traiteReponse(json.html);
}

/**
 * ajoute l'action disconnect au bouton logout
 */
$("#logout").click(disconnect);

/**
 * La fonction de recherche de tweet
 */
function search() {
	// var friends = $("#check_ami").get(0).checked ? 1 : 0;
	// var query = $("#text_recherche").val();
	// $.ajax({
	// type : "Get",
	// url : "http://li328.lip6.fr:8280/COQUART_TOME/Search",
	// data : "clef =" + environnement.clef + "&query=" + query + "&friends="
	// + friends,
	// dataType : "text",
	// success : RechercheMessage.traiteReponseJson,
	// error : ""
	// });
	$.ajax({
		type : "Get",
		url : "AfficherStatut",
		data : "clef =" + environnement.clef,
		dataType : "text",
		success : RechercheMessage.traiteReponseJson,
		error : ""
	});
}

/**
 * Associe la fonction search au bouton rechercher
 */
$("button_recherche").click(search);

/**
 * Permet d'ajouter ou supprimer le contact
 * 
 * @param id
 *            id du contact
 */
function ajout_sup_contact(id) {
	var user = environnement.users[id];
	var url = "AddFriend";
	if (user.contact) {
		url = "RemoveFriend";
	}
	$.ajax({
		type : "GET",
		url : url,
		data : "clef=" + environnement.clef + "&id_friend=" + id,
		dataType : "json",
		success : function(rep) {
			traiteReponceAjoutSupContact(rep, id);
		},
		error : ""
	});
}

/**
 * Traite la réponse d'ajout/suppresion du contact
 * 
 * @param rep
 *            reponse de la servlet
 * @param id
 *            id du contact
 */
function traiteReponceAjoutSupContact(rep, id) {
	if (rep.erreur != undefined) {
		alert(rep.erreur);
	} else {
		var user = environnement.users[id];
		user.modifStatus();
		var comments = envrionnement.recherche.resultats;
		for ( var i in comments) {
			if (comments[i].auteur.id == id) {
				var id_comment = comment[i].id;
				$("#Commentaire_" + id_comment).replaceWith(
						comment[i].getHtml());
			}
			;
		}
	}
}

/**
 * Pour poster un message
 */
function poster() {
	var message = $("#txt_message").val();
	$.ajax({
		type : "Get",
		url : "PosterStatut",
		data : "clef =" + environnement.clef + "&message=" + message,
		dataType : "text",
		success : search(),
		error : ""
	});
}

/**
 * Associe l'envoie de message au bouton poster
 */
$("#poster").click(poster);
