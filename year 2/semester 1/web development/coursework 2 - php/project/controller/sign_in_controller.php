<?php

require __DIR__ ."/../lib/func.php";

$database = getDatabase("questions");

if(!$database){
	exit();
} 
else{
	///////////////
	// #1
	///////////////
	if(isset($_POST["login"])){
		$username = validateInput($_POST["username"]);
		$password = validateInput($_POST["password"]);
		$found = false;

		$query = "SELECT * FROM users";
		$result = $database->prepare($query);
		$result->execute(array());

		foreach($result as $row){
			if ($username == $row["username"] && password_verify($password, $row["password"])){
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
	///////////////
	// #2
	///////////////
	else if(isset($_POST["logout"])){
			unset($_SESSION["user"]);
			unset($_SESSION["userId"]);
			header("Location: ".$_SERVER['HTTP_REFERER']);
			exit();
	}
	///////////////
	// #3
	///////////////
	else if(isset($_POST['register'])){
			if(isset($_POST['username'])){
				$username = validateInput($_POST["username"]);
				$accept1 = true;

				$query = "SELECT * FROM users";
				$result = $database->prepare($query);
				$result->execute(array());

				foreach($result as $row){
					if ($username == $row["username"]){
						if(isset($_SESSION["error"])){
							array_push($_SESSION["error"], "Username already exists.");
						} else{
							$_SESSION["error"] = array();
							array_push($_SESSION["error"], "Username already exists.");
						}
						$accept1 = false;
						break;
						}
				}
			}else{
				$accept1 = false;
			}
			
			if(isset($_POST['password1'], $_POST['password2'])){
				$password1 = validateInput($_POST["password1"]);
				$password2 = validateInput($_POST["password2"]);
				$accept2 = true;
				
				if($password1 != $password2){
					$accept2 = false;
						if(isset($_SESSION["error"])){
							array_push($_SESSION["error"], "Your passwords do not match.");
						} else{
							$_SESSION["error"] = array();
							array_push($_SESSION["error"], "Your passwords do not match.");
						}
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
					$result2 = $database->prepare($query);
					$result2->execute(["name" => $username, "password" => password_hash($password1, PASSWORD_BCRYPT)]);
					$_SESSION["success_regis"] = true;
					header("Location: ".$_SERVER['HTTP_REFERER']);
					exit();
				}
				else{
						if(isset($_SESSION["error"])){
							array_push($_SESSION["error"], "Unable to create the account.");
						} else{
							$_SESSION["error"] = array();
							array_push($_SESSION["error"], "Unable to create the account.");
						}
						header("Location: ".$_SERVER['HTTP_REFERER']);
						exit();
			}
			}
			else{
				header("Location: ".$_SERVER['HTTP_REFERER']);
				exit();
			}

	}

?>