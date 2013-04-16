function connexion (formulaire)  {
	var login = formulaire.login.value;
	var pass = formulaire.mdp.value;
	var ok = verif_co(login, pass);
	if (ok) {
		connecte(login, pass);
	}
}

function verif_co(login, pass) {
	if (login.length == 0) {
		alert("Login obligatoire.");
		return false;
	} else if (pass.length == 0) {
		alert("Mot de passe obligatoire.");
		return false;
	} else {
		return true;
	}
}

function connecte(login, pass) {
		alert("Connexion : " + login + ";" + pass);
		window.location.href='index.html';
}