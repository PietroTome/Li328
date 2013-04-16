/**
 * Creation de l'environnement
 */
function main_sup(id, login, clef) {
	environnement = {};

	if (clef != undefined) {
		environnement.clef = clef;
	}
}

/**
 * Gere les messages d'erreur
 * 
 * @param msg
 */
function func_erreur(msg) {
	var msg_box = "<div id=\"msg-err-connexion\">" + msg + "</div>";
	var tab = $("#msg-err-connexion");
	if (tab.length == 0) {
		$("form").prepend(msg_box);
		$("#msg-err-connexion").css({
			"color" : "red"
		});
	} else {
		tab.replaceWith(msg_box);
		$("#msg-err-connexion").css({
			"color" : "red"
		});
	}
}

/**
 * Fonction de connexion
 * 
 * @param formulaire
 */
function sup_compte(formulaire) {
	var login = formulaire.login.value;
	var pass = formulaire.mdp.value;
	var ok = verif(login, pass);
	if (ok)
		suppression(environnement.clef, login, pass);
}

/**
 * Verifie si les parametres sont correcte
 * 
 * @param login
 * @param pass
 * @returns {Boolean}
 */
function verif(login, pass) {
	if (login.length == 0) {
		func_erreur("Login obligatoire");
		return false;
	} else if (pass.length == 0) {
		func_erreur("Mot de passe obligatoire.");
		return false;
	} else {
		return true;
	}
}

/**
 * Gere la suppression
 * 
 * @param clef
 * @param login
 * @param password
 */
function suppression(clef, login, password) {
	$.ajax({
		type : "GET",
		url : "DeleteUser",
		data : "clef=" + clef + "&login=" + login + "&mdp=" + password,
		dataType : "json",
		success : traiteReponseSup,
		error : function(XHR, testStatus, errorThrown) {
			alert(XHR + "" + testStatus + "" + errorThrown);
		}
	});
}

/**
 * S'occupe de la reponse de la servlet
 * 
 * @param rep
 */
function traiteReponseSup(rep) {
	if (rep.erreur != undefined) {
		func_erreur(rep.erreur);
	} else
		windows.location.href = "index.jsp";
}
