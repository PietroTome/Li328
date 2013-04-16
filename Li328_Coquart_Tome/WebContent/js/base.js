/**
 * Creation de l'environnement
 */
function main(id, login, clef) {
	environnement = {};
	environnement.users = [];

	environnement.switchContext = function() {
		if (this.context == "connected") {
			this.context = "disconnected";
		} else if (this.context == "disconnected") {
			this.context = "connected";
		}
		gererDivConnexion();
	};

	if ((id != undefined) && (login != undefined) && (clef != undefined)) {
		environnement.clef = clef;
		environnement.actif = new User(id, login);
	}
	gererDivConnexion();
	search();
	$("#logout").click(disconnect);
}

/**
 * Creer un objet user
 * 
 * @param id
 * @param login
 * @param contact
 * @returns un objet user
 */
function User(id, login, contact) {
	this.id = id;
	this.login = login;
	this.contact = contact;
	environnement.user[id] = this;
}

/**
 * Fonction pour modifier le statut d'un user
 */
User.prototype.modifStatut = function() {
	this.contact = !this.contact;
};

/**
 * Creer un message
 * 
 * @param id
 * @param auteur
 * @param texte
 * @param date
 * @param score
 * @returns un objet message
 */
function Message(id, auteur, texte, date, score) {
	this.id = id;
	this.auteur = auteur;
	this.texte = texte;
	this.date = new Date(date);
	this.score = score;
}

/**
 * Renvoie le html d'un message
 * 
 * @param message
 * @returns {String}
 */
Message.prototype.getHTML = function(message) {
	var s = '<div class="tweet"><p>';
	s += this.texte.value;
	s += '</p><span>';
	s += this.date.value;
	s += '</span><span>';
	s += this.login.value;
	s += '</span><input type="button" value="+" /><input type="hidden" value="-" /></div>';
	return s;
};

/**
 * Creer un objet recherche
 * 
 * @param resultat
 *            resultat de la requete
 * @param recherche
 *            mots clefs de la recherche
 * @param contacts_only
 *            boolean a vrai si on l'on cherche que dans les amis
 * @param user
 *            auteur de la recherche
 * @param date
 * @returns un objet recherche
 */
function RechercheMessage(resultat, recherche, contacts_only, user, date) {
	this.resultat = resultat;
	this.recherche = recherche;
	this.contacts_only = contacts_only;
	this.user = user;
	this.date = date;
}

RechercheMessage.prototype.getHTML = function(message) {
	var s = '<div class="tweet"><p>Recherche : ';
	s += this.recherche.value;
	s += '</p><p>Resultat : ';
	s += this.resultat.value;
	s += '</p><span>';
	s += this.date.value;
	s += '</span><span>';
	s += this.user.value;
	s += '</span><span>';
	s += this.contacts_only.value;
	s += '</span><input type="button" value="+" /><input type="hidden" value="-" /></div>';
	return s;
};

/**
 * Envoie un message basique
 */
function envoieMessage() {
	var u1 = new User(1, "toto", false);
	var c1 = new Message(45, u1, "bla", new Date(), 4);
	var c2 = new Message(45, u1, "salut", new Date(), 2);
	var r = RechercheMessage([ c1, c2 ], "", false, u1, new Date());
	return JSON.string.fg(r);
}

/**
 * Recree les objets a  partir de paire de clef et valeur
 * 
 * @param clef
 * @param value
 * @returns un objet
 */
RechercheMessage.revival = function(clef, value) {
	if (clef.length == 0)
		if (value.erreur == undefined)
			return new RechercheMessage(value.result);
		else
			return value;
	else if ((isNumber(clef)) && (value.auteur instanceof User))
		return new Message(value.id, value.auteur, value.texte, value.date,
				value.score);
	else if (clef == 'auteur')
		return new User(value.id, value.login, value.contact);
	else if (clef == 'date')
		return new Date(value);
	else
		return value;
};

/**
 * Traite la reponse des recherches de message
 * 
 * @param json_text
 */
RechercheMessage.traiteReponse = function(json_text) {
	var obj = JSONparse(json_text, RechercheMessage.revival);
	if (obj.erreur == undefined)
		$("#liste_tweet").html(obj.getHTML());
};