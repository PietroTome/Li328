<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="style.css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/index.js"></script>
<script type="text/javascript" src="js/suppression.js"></script>

<script type="text/javascript">
	function go() {
<%String clef = request.getParameter("clef");
			if (clef != null) {
				out.println("main_sup("+clef + ");");
			} else {
				out.println("main_sup();");
			}%>
	};
	$(go);
</script>

<title>Supprimer son compte</title>
</head>
<body>
	<div id="page">

		<div id="header">
			<div id=logo>
				<a href="index.jsp"><img src="image/baniere.png" /></a>
			</div>
		</div>

		<div id="stats">
			<h1>
				Zone stats<br> <br> <br> <br> <br> <br>
				<br> <br>
			</h1>
		</div>

		<div id="main">
			<h1>Suppresion de son compte</h1>
			<form action="" method="get" OnSubmit="javascript:sup_compte(this)">
				<div class="ligne">
					<span>Login :</span> <input type="text" name="login" />
				</div>
				<div class="ligne">
					<span>Mot de Passe :</span><input type="password" name="mdp" />
				</div>
				<div class="button">
					<input type="submit" value="Valider" /> <input type="button"
						value="Annuler" onclick="window.location.href='index.jsp'" />
				</div>
			</form>

			<input class="button" id="enregistrer" type="button"
				value="Enregistrer"
				onclick="window.location.href='indexEnregistrement.jsp'" />
		</div>

		<div id="footer">
			<h1>Pied de page</h1>
		</div>
	</div>
</body>
</html>