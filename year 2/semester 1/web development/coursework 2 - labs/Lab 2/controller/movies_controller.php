<?php
$username_db = "root";
$password_db = "";
$dbname = "movies";
$connection=new PDO("mysql:dbname=movies;host=localhost; charset=utf8", $username_db, $password_db);
$connection->setAttribute(PDO::ATTR_EMULATE_PREPARES, false);
$connection->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

session_start();

if(!$connection){
	exit();
}

if(isset($_POST["add_movie"])){
	$query = "SELECT userID FROM users WHERE username=?";
	$result = $connection->prepare($query);
	$result->execute([$_SESSION['user']]);

	foreach($result as $row){
		$id = $row["userID"];
	}
	
	$movieID = $_POST["movie"];

	$query2 = "INSERT INTO users_movies VALUES (?, ?)";
	$result2 = $connection->prepare($query2);
	$result2->execute([$id, $movieID]);

	header("Location: ".$_SERVER['HTTP_REFERER']);
	exit();
}