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

if(isset($_POST["login"])){

		$username = $_POST["username"];
		$password = $_POST["password"];
		$found = false;

		$query = "SELECT * FROM users";
		$result = $connection->prepare($query);
		$result->execute(array());

		foreach($result as $row){
			if ($username == $row["username"] && $password == $row["password"]){
				$found = true;
				$_SESSION["user"] = $username;
				$_SESSION["userId"] = $row["userID"];
				break;
			}
		}
		if ($found == false){
			$_SESSION["success_login"] = false;
			header("Location: ".$_SERVER['HTTP_REFERER']);
			exit();
		}
		else{
			header("Location: ".$_SERVER['HTTP_REFERER']);
			exit();
		}
}

if(isset($_POST["logout"])){
	unset($_SESSION["user"]);
	header("Location: ".$_SERVER['HTTP_REFERER']);
	exit();
}

if(isset($_POST['register'])){

	if(isset($_POST['username'])){
	$username = $_POST["username"];
	$accept1 = true;

	$query = "SELECT * FROM users";
	$result = $connection->prepare($query);
	$result->execute(array());

	foreach($result as $row){
		if ($username == $row["username"]){
			print "Username already exists.\n";
			$accept1 = false;
			break;
			}
		}
	}else{
		$accept1 = false;
	}
	
	if(isset($_POST['password1'], $_POST['password2'])){
		$password1 = $_POST["password1"];
		$password2 = $_POST["password2"];
		$accept2 = true;
		
		if($password1 != $password2){
		$accept2 = false;
		print "Your passwords do not match.\n";
		}
		else{
			$accept2 = true;
		}
	}
	else{
		$accept2 = false;
	}
	if($accept1 && $accept2){
			$query = "INSERT INTO users (username, password) VALUES (:name,:password)";
			$result2 = $connection->prepare($query);
			$result2->execute(["name" => $username, "password" => $password1]);
			$_SESSION["success_regis"] = true;
			header("Location: ".$_SERVER['HTTP_REFERER']);
			exit();
		}
		else{
			print "Unable to create the account.\n";
	}
}
?>