<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
<title>Main Page</title>
<link href="../../css/common.css" rel="stylesheet" />
<link href="../../css/page-1.css" rel="stylesheet" />
<link href="https://fonts.googleapis.com/css?family=Open+Sans"
	rel="stylesheet" type="text/css" />
<meta charset="UTF-8" />
</head>
<body>
	<header>
		<nav>
			<ul>
				<li id="home"><a href="index.jsp">&#160;</a></li>
				<li ><a href="">Developers</a></li>
				<li ><a href="">Country</a></li>
				<li id="current"><a href="index.jsp">Genres<span id="icon"></span></a></li>
				<li ><a href="">Engine</a></li>
				<li ><a href="">Year</a></li>
				<li ><a href="">Publishers</a></li>
				<li ><a href="">Franchises</a></li>
				<li ><a href="">Reviews</a></li>
				<li><a href="credits.jsp">Credits</a></li>
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
					<form method="post" action="">
						<h2>Log In</h2>
						<input type="text" placeholder="username" /> <input
							type="password" placeholder="password" /> <a>Register</a><br /> <a>Password
							recovery</a><br /> <input type="submit" />
					</form>
				</li>
				<li class="search">
					<form method="get" action="">
						<h2>Search:</h2>
						<input type="search" placeholder="type here" /> <input
							type="submit" name="search" />
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
		<div id="container-content-1">
			<h1 id="headline">GENRES</h1>
			<ul>
				<li><h1>MOBAs</h1> <a href="../sub-hub/moba.jsp" title="MOBA"><img
						src="../../images/moba.jpg" alt="MOBA" /></a></li>
				<li><h1>Shooters</h1> <a href="../sub-hub/shooters.jsp"><img
						src="../../images/shooter-games.jpg" alt="Shooters" /></a></li>
				<li ><h1>Fighters</h1> <a href=""><img
						src="../../images/fighters-genre.jpg" alt="Fighters" /></a></li>
			</ul>
			<ul>
				<li><h1>Turn Based Strategies</h1> <a href="../sub-hub/4x.jsp"><img
						src="../../images/turn-based-strategies.jpg"
						alt="Turn Based Strategies" /></a></li>
				<li><h1>Real Time Strategies</h1> <a href="../sub-hub/rts.jsp"><img
						src="../../images/real-time-strategies.jpg"
						alt="Real Time Strategies" /></a></li>
				<li ><h1>Tactical Shooters</h1> <a href=""><img
						src="../../images/tactical-shooters.jpg" alt="Tactical Shooters" /></a></li>
			</ul>
			<ul>
				<li><h1>Open World</h1> <a href="../sub-hub/open_world.jsp"><img
						src="../../images/open-world-genre.jpg" alt="Open World" /></a></li>
				<li><h1>Stealth</h1> <a href="../sub-hub/stealth.jsp"><img
						src="../../images/stealth-genre.jpg" alt="Stealth" /></a></li>
				<li ><h1>Platformers</h1> <a href=""><img
						src="../../images/platformers.jpg" alt="Platformers" /></a></li>
			</ul>
			<ul>
				<li ><h1>Online Games</h1> <a href=""><img
						src="../../images/online-genre.jpg" alt="Online Games" /></a></li>
				<li><h1>Role Playing Games</h1> <a href="../sub-hub/rpg.jsp"><img
						src="../../images/role-playing-games.jpg" alt="Role Playing Games" /></a></li>
				<li ><h1>Point &amp; Click Adventures</h1> <a href=""><img
						src="../../images/action-adventure-genre.jpg"
						alt="Point &amp; Click Adventures" /></a></li>
			</ul>

		</div>
		<br class="clear" />
	</div>

	<footer>
		<img class="avatar" src="../../images/avatar.jpg" alt="Avatar" />
		<ul>
			<li>Created by Daniel Potter.</li>
			<li>Contact through <a
				href="https://steamcommunity.com/id/daniel_faraday"><img
					src="../../images/steam.png" alt="Steam" /></a></li>
			<li>or by <a href="mailto:">email</a>.
			</li>
		</ul>
	</footer>
</body>
</html>
