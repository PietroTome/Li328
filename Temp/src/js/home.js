function home() {
	env = {};
	env.user = [];
}

function User(id, login, contact) {
	this.id = id;
	this.login = login;
	this.contact = contact;
	env.user[id] = this;
}

User.prototype.modifStatut = function() {
	this.contact = !this.contact;
}

function Comments(id, auteur, texte, date, score) {
	this.id = id;
	this.auteur = auteur;
	this.texte = texte;
	this.date = new Date(date);
	this.score = score;
}

Comments.prototype.getHtml = function(comment) {
	var s = '<div class="tweet"><p>';
	s += this.texte.value;
	s += '</p><span>';
	s += this.date.value;
	s += '</span><span>';
	s += this.login.value;
	s += '</span><input type="button" value="+" /><input type="hidden" value="-" /></div>';
	return s;
}
