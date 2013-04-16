<!DOCTYPE html>

<html>
<!-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%> -->
<head>
<title>PieCoq</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="style.css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/index.js"></script>
<script type="text/javascript" src="js/base.js"></script>

<script type="text/javascript">
	function go() {
<%String id = request.getParameter("id");
			String login = request.getParameter("login");
			String clef = request.getParameter("clef");
			if ((id != null) && (login != null) && (clef != null)) {
				out.println("main(" + id + "," + login + "," + clef + ");");
			} else {
				out.println("main();");
			}%>
	};
	$(go);
</script>
</head>

<body>
	<div id="page">

		<div id="header">
			<div id=logo>
				<a href="index.jsp"><img src="image/baniere.png" /></a>
			</div>
			<div id="divconnect">
				<div id="connectlog">
				</div>
				<form id="form_reste" method="get" action="">
					<input class="button" id="login" type="button" value="Login" onclick="window.location.href='indexLogin.jsp'" /> 
					<input class="button" id="logout" type="hidden" value="Logout" /> 
					<input class="button" id="delete_compte" type="hidden" value="Supprimer son compte" onclick="window.location.href='indexDeleteCompte.jsp'"/>
				</form>
			</div>
		</div>

		<div id="recherche">
			<form id="form_recherche" method="get" action="">
				<textarea name="recherche" id="text_recherche">Votre recherche</textarea>
				<input class="button" id="button_recherche" type=submit
					value="Rechercher" /> <input id="check_ami" type="checkbox"
					value="SelectFriends" /><span id="txt_check_ami">Dans mes
					amis</span>
			</form>
		</div>

		<div id="stats">
			<h1>
				Zone stats<br />
			</h1>
		</div>

		<div id="main">
			<div id="add_comments">
				<form method="get" action="poster">
					<h1>Poster un message :</h1>
					<textarea id="txt_message" name="message">Votre message</textarea><br>
					<input class="button" id="poster" type="submit" value="Poster" />
				</form>
			</div>
			

			<h1>Liste des tweets</h1>
			<div id="liste_tweet">
				<span id=tweet>Bienvenue sous PieCoq</span>
				<span id=date>Le 14/04/2013</span>
				<span id=Proprio>Par Pietro</span> 
				<input class="button" id="plus" type="button" value="+" /> 
				<input class="button" id="moins" type="button" value="-" />
			</div>
		</div>

		<div id="footer">
			<h1>©PieCoq Corporetion</h1>
		</div>
	</div>
</body>
</html>