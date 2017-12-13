<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page
	import="java.util.*, package1.Item, java.io.ByteArrayOutputStream, java.io.ObjectOutputStream, java.util.Base64"%>


<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
<title>Dota 2</title>
<link href="../../css/common.css" rel="stylesheet" />
<link href="../../css/page-3.css" rel="stylesheet" />
<link href="https://fonts.googleapis.com/css?family=Open+Sans"
	rel="stylesheet" type="text/css" />
<meta charset="UTF-8" />
</head>
<body>

	<header>
		<nav>
			<ul>
				<li id="home"><a href="../hub/index.jsp">&#160;</a></li>
				<li><a href="">Developers</a></li>
				<li><a href="">Country</a></li>
				<li id="current"><a href="">Genres<span id="icon"></span></a></li>
				<li><a href="">Engine</a></li>
				<li><a href="">Year</a></li>
				<li><a href="">Publishers</a></li>
				<li><a href="">Franchises</a></li>
				<li><a href="">Reviews</a></li>
				<li><a href="">Credits</a></li>
				<li id="vaultboy"></li>
			</ul>

		</nav>
	</header>
	<hr />
	<div class="block">
		<aside id="aside-block-1">
			<hr />
			<ul class="secondnav">
				<li class="login">
					<form method="post">
						<!--action=""-->
						<h2>Log In</h2>
						<input type="text" placeholder="username" /> <input
							type="password" placeholder="password" /> <a>Register</a><br />
						<a>Password recovery</a><br /> <input type="submit" />
					</form>
				</li>
				<li class="search">
					<form method="get">
						<h2>Search:</h2>
						<input type="text" placeholder="type here" /> <input
							type="submit" />
					</form>
				</li>
			</ul>
			<hr />
			<div class="creed">
				<h2>THE CREED</h2>
				<ul>
					<li>This is my website.</li>
					<li>There are many like it, but this one is mine.</li>
					<li>My website is my best friend.</li>
					<li>It is my life.</li>
					<li>I must master it as I must master my life.</li>
					<li>Without me, my website is useless.</li>
					<li>Without my website, I am useless.</li>
					<li>Before God, I swear this creed.</li>
				</ul>
				<hr />
			</div>
		</aside>
		<div id="container-content-3">
			<%!Item game = new Item("game", "Dota 2", 0.99f, "Valve Corporation", "Valve Corporation",
			new GregorianCalendar(2013, Calendar.JULY, 9), new String[] { "MOBA" }, "Source", "../../images/posters/dota2_cover.jpg");

	String serializedObject = "";%>

			<%
				game.increment_and_assign_id();
				try {
					ByteArrayOutputStream bo = new ByteArrayOutputStream();
					ObjectOutputStream so = new ObjectOutputStream(bo);
					so.writeObject(game);
					serializedObject = Base64.getUrlEncoder().encodeToString(bo.toByteArray());
					so.flush();
					so.close();
				} catch (Exception e) {
					System.out.println(e);
				}
			%>

			<h1>Dota 2</h1>
			<div id="intro-div">
				<button type="button" id="back" onclick="previous_image()"></button>
				<img id="game" src="<%=game.getImage()%>" alt="dota 2" />
				<button type="button" id="forward" onclick="next_image()"></button>
				<!-- <script type="text/javascript" src="../../js/script.js"></script>-->

				<form action="${pageContext.request.contextPath}/Cart" method="post">
					<input type="hidden" name="add" value="<%=serializedObject%>" />
					<button type="submit">
						<img src="../../images/Shopping_Cart-512.png" width="50"
							height="40" alt="submit" />
					</button>
					<br /> <label><%="$" + game.getCost()%></label>
				</form>
			</div>
			<p></p>

			<table>
				<tr>
					<th>Type</th>
					<th>Developer</th>
					<th>Publisher</th>
				</tr>

				<tr>
					<td><%=game.getType()%></td>
					<td><%=game.getDeveloper()%></td>
					<td><%=game.getPublisher()%></td>
				</tr>
				<tr>
					<th>Release Date</th>
					<th>Genre</th>
					<th>Game Engine</th>
				</tr>
				<tr>
					<td><%=game.getRelease_Date()%></td>
					<td>
						<%
							for (int x = 0; x < game.getGenre().length; x++) {
								out.println(game.getGenre()[x]);
							}
						%>
					</td>
					<td><%=game.getGame_engine()%></td>
				</tr>
			</table>
		</div>

		<br class="clear" />
	</div>
	<footer>
		<img class="avatar" src="../../images/avatar.jpg" alt="avatar" />
		<ul>
			<li>Created by Daniel Potter.</li>
			<li>Contact through <a
				href="https://steamcommunity.com/id/daniel_faraday"><img
					src="../../images/steam.png" alt="steam icon" /></a></li>
			<li>or by <a href="mailto:">email</a>.
			</li>
		</ul>
	</footer>
</body>
</html>