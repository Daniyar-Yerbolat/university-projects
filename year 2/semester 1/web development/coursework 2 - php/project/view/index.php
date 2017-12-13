<?php
require __DIR__ ."/../lib/func.php";
require __DIR__ ."/../lib/heredocs.php";
// 

// <link rel="stylesheet" type="text/css" href="./index.css"/>
?>

<!DOCTYPE html>
<html>
<head>
	<title></title>
	<meta name="viewport" content="width=device-width, initial-scale=1"/>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
	<script type="text/javascript" src="../js/ajax_javascript.js"></script>

	<link rel="stylesheet" type="text/css" href="./css/index.css"/>
</head>
<body>
	<div class="container">
		<h1>Step 1</h1>
			<div>
				<h2>Log In</h2>
				<?php
				if(!isset($_SESSION["user"])){
					print "\n".login()."\n";
				} else {
					$user = $_SESSION["user"];
					print "\n"."Hello $user.\n";
					print "\n".logout()."\n";
				}
				?>
		<h1>Step 2</h1>
				<h2>ASK</h2>
				<?php
				print "\n".ask()."\n";
				?>
			</div>
			<h2>ANSWER</h2>
	</div>

	<div class="container">
		<h1>VIEW</h1>
		<form method="post" action="search.php">
			<select name="filter">
				<option value="all">All</option>
				<option value="solved">Solved</option>
				<option value="unsolved">Unsolved</option>
			</select>
			<input type="text" name="keyword"/>
			<input type="submit" name="search" value="Search"/>
		</form>
	</div>

	<div id="update">
	Test
	</div>

</body>
</html>