<!DOCTYPE HTML>
<html>
<head>
	<title>Main Page</title>
	<link href="../css/common.css" rel="stylesheet">
	<link href="../css/page-1.css" rel="stylesheet">
	<link href='https://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
	<meta charset="UTF-8">

</head>
<body>

	<header>
		<nav>			
			<ul>
				<li id="home"><a href="index.php">&nbsp;</a></li>
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
	<hr>

	<div class="block">
		<aside id="aside-block-1">
			<p> Advisable to view in widescreen, possibly 1080p. <br> Do note that the site only has 3 webpages: current one, moba and dota.</p>
			<hr>
			<ul class="secondnav">
			<?php
			if(isset($_POST['submit'])){
				$incorrectlogin = false;
				
				$username = $_POST['username'];
				$password = $_POST['password'];
				
				$server_db = "localhost";
				$username_db = "root";
				$password_db = "";
				$database_db = "usersystem";
				$connection = mysqli_connect($server_db, $username_db, $password_db, $database_db);
				if(!$connection){
						exit;
					}
				$request1 = "SELECT username, password FROM users WHERE username='$username'";
				$result = mysqli_query($connection, $request1);
				
				if($result) {
						while($row = mysqli_fetch_assoc($result)){
							if($username == $row["username"] && $password == $row["password"]) {
								$incorrectlogin = false;
								print "<li class=\"login\"> \n";
								print "Welcome $username \n";
								print "</li> \n";
								
							}
							else{
								$incorrectlogin = true;
							}
						}
				}
				else{
					$incorrectlogin = true;
				}		
			}
			if(!isset($_POST['submit']) || $incorrectlogin){
			$button = $_SERVER['PHP_SELF'];
			print "<li class=\"login\"> \n";
			print "<form method='POST'action=\"$button\"> \n";
			print "<h2>Log In</h2> \n";
			if(isset($incorrectlogin)){
				if($incorrectlogin == true){
					print "<input type=\"text\" name=\"username\" placeholder=\"incorrect username\"> \n";
					print "<input type=\"password\" name=\"password\" placeholder=\"incorrect password\"> \n";
				}
			}
			else {
			print "<input type=\"text\" name=\"username\" placeholder=\"username\"> \n";
			print "<input type=\"password\" name=\"password\" placeholder=\"password\"> \n";
			}
			print "<a href=\"register.php\">Register</a><br> \n";
			print "<a>Password recovery</a><br> \n";
			print "<input type=\"submit\" name=\"submit\"> \n";
			print "</form> \n";
			print "</li> \n";
			}
			?>
			<li class="search">
				<form method="post" action="">
				<h2>Search:</h2>
				<input type="search" placeholder="type here">
				<input type="submit" name="search">
				</form>
			</li>
			</ul>
			
			<hr>
			<div class="creed">
						<h2>THE CREED</h2>
						<ul>
							<li>This is my website.</li>
							<li>There are many like it, but this one is mine.</li>
							<li>My website is my best friend.</li>
							<li>It is my life.
							<li>I must master it as I must master my life.</li>
							<li>Without me, my website is useless.
							<li>Without my website, I am useless.</li>
							<li>Before God, I swear this creed.</li>
						</ul>
						<hr>
			</div>
		</aside>
		<div id="container-content-1">
			<h1 id="headline">GENRES</h1>
			<ul>
				<li><h1>MOBAs</h1>
					<a href="moba.php" title="MOBA"><img src="../images/moba.jpg" alt="MOBA"></a></li>
				<li><h1>Shooters</h1>
					<a href=""><img src="../images/shooter-games.jpg" alt="Shooters"></a></li>
				<li><h1>Fighters</h1>
					<a href=""><img src="../images/fighters-genre.jpg" alt="Fighters"></a></li>
			</ul>
			<ul>
				<li><h1>Turn Based Strategies</h1>
					<a href=""><img src="../images/turn-based-strategies.jpg" alt="Turn Based Strategies"></a></li>
				<li><h1>Real Time Strategies</h1>
					<a href=""><img src="../images/real-time-strategies.jpg" alt="Real Time Strategies"></a></li>
				<li><h1>Tactical Shooters</h1>
					<a href=""><img src="../images/tactical-shooters.jpg" alt="Tactical Shooters"></a></li>
			</ul>
			<ul>
				<li><h1>Open World</h1>
					<a href=""><img src="../images/open-world-genre.jpg" alt="Open World"></a></li>
				<li><h1>Stealth</h1>
					<a href=""><img src="../images/stealth-genre.jpg" alt="Stealth"></a></li>
				<li><h1>Platformers</h1>
					<a href=""><img src="../images/platformers.jpg" alt="Platformers"></a></li>
			</ul>
			<ul>
				<li><h1>Online Games</h1>
					<a href=""><img src="../images/online-genre.jpg" alt="Online Games"></a></li>
				<li><h1>Role Playing Games</h1>
					<a href=""><img src="../images/role-playing-games.jpg" alt="Role Playing Games"></a></li>
				<li><h1>Point &amp; Click Adventures</h1>
					<a href=""><img src="../images/action-adventure-genre.jpg" alt="Point &amp; Click Adventures"></a></li>
			</ul>

		</div>
	<br class="clear">
	</div>
	
	<footer>
		<img class="avatar" src="../images/avatar.jpg" alt="Avatar">
		<ul>
			<li>Created by Daniel Potter.</li>
			<li>Contact through <a href="https://steamcommunity.com/id/daniel_faraday"><img src="../images/steam.png" alt="Steam"></a></li>
			<li>or by <a href="mailto:danik18_96@mail.ru">email</a>.</li>
		</ul>

	</footer>
</body>
</html>
