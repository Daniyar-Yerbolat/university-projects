<?php
session_start();

	function getDatabase($dbname){
		$username_db = "root";
		$password_db = "";
		$host = "localhost";
		// $dbname = "movies";
		$connection = new PDO("mysql:dbname=$dbname;host=$host; charset=utf8", $username_db, $password_db);
		$connection->setAttribute(PDO::ATTR_EMULATE_PREPARES, false);
		$connection->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
		return $connection;
	}

	//POST, GET.
	function validateInput($input){
		return strip_tags($input);
	}
	
	// $pattern = "[A-Za-z0-9._%+-]{6,20}";
	function validate_pattern($input, $pattern){
		if (preg_match($pattern, $input) == 1){
			return true;
		} else {
			return false;
		}
	}

	// pass id, get username
	function getUsername($id){
		$id = validateInput($id);
		$users = getDatabase("questions");
		$query = "SELECT * FROM users WHERE userID=?";
		$result = $users->prepare($query);
		$result->execute([$id]);
		$temp = $result->fetch(PDO::FETCH_ASSOC);
		return $temp["username"];
	}

	function find($word, $collection){
		$word = strtolower($word);
		$count = strlen($word);
		$output = array();
		foreach($collection as $row){
			$x = 0;
			$que_length = strlen($row["question"]);
			while($x < $que_length && ($que_length-$x)>=$count){
				if($word == substr(strtolower($row["question"]), $x, $count)){
					$output[] = $row;
					break;
				}
				$x++;
			}
		}
		return $output;
	}
	
?>