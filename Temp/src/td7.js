function func-erreur(msg) {
	var msg-box ="<div id=\" msg-err-connexion\">"+msg="</div">,
	var tab= $("#msg-err-connexion"); 
	if(tab.length==0) {
		$("form").prepend(msg-box);
	} else { 
		tab.replaceWith(msg-box);
		$("#msg-err-connexion").css({color:red;});
	} 
}

function gererDivConnexion () {
	verr user=environnement.actif;
	if(user != undefined) {
		$("#connectlog").html("<SPAN id=\"log\">"+user.login+"</SPAN>");
		$("#log").css(BeauCSS);
		$("#disconned").css("visibility", "visible")
	} else {
		$("#connectlog").html("<a href=\"connexion html\">........");
		$("#disconect").css("visibility", "hidden")
	}
}

function didconnect () {
	environnement.actif=undefined,
	gererDivConnexion();
	var json.text=envoiCommentaire();
	RechercheCommentaire.traiteReponse(json.html);
}

$(#disconnect").click(disconned);
		