<!DOCTYPE HTML>
<html>
	<head>
		<title>Dota 2</title>
		<link href="../css/common.css" rel="stylesheet">
		<link href="../css/page-3.css" rel="stylesheet">
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
		<aside id="aside-block-3">
		<hr>
			<ul class="secondnav">
				
			<li class="login">
			<form method="post" action="">
					<h2>Log In</h2>
					<input type="text" placeholder="username">
					<input type="password" placeholder="password">
					<a>Register</a><br>
					<a>Password recovery</a><br>
					<input type="submit">
			</form>
			
				
			</li>
			<li class="search">
				<form method="post" action="">
				<h2>Search:</h2>
				<input type="text" placeholder="type here">
				<input type="submit">
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
	<div id="container-content-3">
	<h1>Dota 2</h1>
	<img id="game" src="../images/dota-2.png">
	<p></p>
	<table>
	<tr>
			<th>Developer</th>
			<th>Publisher</th>
			<th>Year</th>
	</tr>
	<?php
		$server_db = "localhost";
		$username_db = "root";
		$password_db = "";
		$database_db = "gamedevs";
		$connection = mysqli_connect($server_db, $username_db, $password_db, $database_db);

		if(!$connection){
			exit;
		}

		$request = "SELECT name, image, founded FROM publisher";
		$result = mysqli_query($connection, $request);

		

		/*while ($row = mysqli_stmt_fetch($result)) {
 	 	$results_array[] = $row;
		}
		
		echo "<tr>\n";
		echo "<td>".$results_array["name"]."</td>\n";
		echo "<td>".$results_array['image']."</td>\n";
		echo "<td>".$results_array["founded"]."</td>\n";
		echo "</tr>\n";
		echo "</table>\n";
		printf ('<tr>\n<td>'.$row["name"].'</td></tr>\n</table>\n');
		*/
		

		$row = mysqli_fetch_assoc($result);
		echo '<tr>'."\n";
		echo '<td>'.$row["name"].'</td>'."\n";
		echo '<td><img src='."'".$row['image']."'".'></td>'."\n";
		echo '<td>'.$row["founded"].'</td>'."\n";
		echo '</tr>'."\n";
		echo '</table>'."\n";
		
		mysqli_close($connection);
	?>
	<!--
		<tr>
			<th>Developer</th>
			<th>Publisher</th>
			<th>Year</th>
		</tr>
		<tr>
			<td>
				<h1>Valve</h1>
				<a>
					<img src="../images/valve-logo.png" title="Valve">
				</a>

			</td>
			<td>
				<h1>Valve</h1>
				<a>
					<img src="../images/valve-logo.png" title="Valve">
				</a>

			</td>
			<td>
				<h1>2013<h1>
				<a>
					<img>
				</a>

			</td>
		</tr>
			<tr>
			<th >Genre</th>
			<th>Country of Origin</th>
			<th>Game Engine</th>
		</tr>
		<tr>
			<td>
				<h1>MOBA; Online<h1>
				<a>
					<img>
				</a>

			</td>
			<td>
				<h1>Washington, USA</h1>
				<a>
					<img src="../images/usa.png">
				</a>

			</td>
			<td>
				<a>
					<img src="../images/source-engine-logo.png" title="Source Engine">
				</a>

			</td>
		</tr>
	</table> -->
</div>
<br class="clear">
</div>
	<footer>
		<img class="avatar" src="../images/avatar.jpg">
		<ul>
			<li>Created by Daniel Potter.</li>
			<li>Contact through <a href="https://steamcommunity.com/id/daniel_faraday"><img src="../images/steam.png"></a></li>
			<li>or by <a href="mailto:danik18_96@mail.ru">email</a>.</li>
		</ul>

	</footer>
	</body>
</html>