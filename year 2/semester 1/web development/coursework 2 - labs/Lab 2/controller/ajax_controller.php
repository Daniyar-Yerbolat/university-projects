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

if(isset($_POST["deleteID"]) && isset($_SESSION["user"])){
	$deletemovie = $_POST["deleteID"];

	$result = $connection->prepare("SELECT userID from users where username=?");
	$result->execute([$_SESSION["user"]]);
	$temp = $result->fetch(PDO::FETCH_ASSOC);
	$userID = $temp["userID"];

	
	$result2 = $connection->prepare("DELETE FROM users_movies WHERE userID=? AND movieID=?");
	$result2->execute([$userID, $deletemovie]);

	$count = $result2->rowCount();
 
	if($count>0){
	echo "done";}
}

?>