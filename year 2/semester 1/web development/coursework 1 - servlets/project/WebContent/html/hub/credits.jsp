<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
<title>Main Page</title>
<link href="../../css/common.css" rel="stylesheet" />
<link href="../../css/page-1.css" rel="stylesheet" />
<link href="../../css/credits.css" rel="stylesheet" />
<link href="https://fonts.googleapis.com/css?family=Open+Sans"
	rel="stylesheet" type="text/css" />
<meta charset="UTF-8" />
</head>
<body>
	<header>
		<nav>
			<ul>
				<li id="home"><a href="index.jsp">&#160;</a></li>
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
			<p>
				Advisable to view in widescreen, possibly 1080p. <br /> Do note
				that the site only has 3 webpages: current one, moba and dota.
			</p>
			<hr />
			<ul class="secondnav">
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
		<div class="credits">
			<script type="text/javascript" src="../../js/audio.js"></script>
			<button type="button" id="audio" onclick="play_pause()">Music:
				on</button>
			<div class="wrapper">
				<h1>CEO</h1>
				<p>Daniel Potter</p>
				<h1>Project Manager</h1>
				<p>Daniel Potter</p>
				<h1>Head of HTML team</h1>
				<p>Daniel Potter</p>
				<h1>HTML team</h1>
				<p>Daniel Potter</p>
				<h1>Head of CSS team</h1>
				<p>Daniel Potter</p>
				<h1>CSS team</h1>
				<p>Daniel Potter</p>
				<h1>Head of Javascript team</h1>
				<p>Daniel Potter</p>
				<h1>Javascript team</h1>
				<p>Daniel Potter</p>
				<h1>Head of Servlets and JSPs team</h1>
				<p>Daniel Potter</p>
				<h1>Servlets and JSPs team</h1>
				<p>Daniel Potter</p>
				<h1>Special Thanks To</h1>
				<p>Daniel Potter</p>
			</div>
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