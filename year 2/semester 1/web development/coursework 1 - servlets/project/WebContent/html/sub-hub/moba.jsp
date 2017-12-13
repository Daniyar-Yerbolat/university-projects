<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
<title>MOBA Games</title>
<link href="../../css/common.css" rel="stylesheet" />
<link href="../../css/page-2.css" rel="stylesheet" />
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
		<div id="intro">
			<hr />
			<h1>MOBAs</h1>
			<p>Action-RTS, or MOBAs, otherwise known as Massive Online Battle
				Arena, originated from RTS (Real Time Strategies). To be specific,
				from starcraft 1 and warcraft 3. It started with a mod called dota,
				which stars a large roster of heroes in a 5v5 match. A player could
				only choose one hero, and each hero had his advantages and
				drawbacks. The goal is to destroy the main structure of the opposing
				team, which are located on the edges of the map. The map in
				particular is borrowed from Starcraft - it's a map with 3 roads
				going from one side to the other.</p>
			<p>The idea of the game is to have a huge ecosystem of heroes.
				Balancing those heroes is key, so that none become too underpowered
				or overpowered. If it's done right, the game can provide you with
				tons of hours of enjoyment, without ever getting dull.</p>
			<hr />
		</div>
		<aside id="aside-block-1">

			<hr />
			<ul class="secondnav">

				<li class="login">
					<form method="post" action="">
						<h2>Log In</h2>
						<input type="text" placeholder="username" /> <input
							type="password" placeholder="password" /> <a>Register</a><br />
						<a>Password recovery</a><br /> <input type="submit" />
					</form>


				</li>
				<li class="search">
					<form method="post" action="">
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

		<div id="container-content-2">
			<ul>
				<li><a href="../games/dota_2.jsp"><h2>Dota 2</h3><img
						src="../../images/dota-2.png" alt="Dota 2" /></a></li>
			</ul>
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