<!DOCTYPE html>
<html>
<head>
	<title></title>
</head>
<body>
<?php
		$username = "root";
		$password = "";
		$dbname = "movies";
		//$connection = mysqli_connect('localhost',$username,$password,$dbname);
		$connection=new PDO("mysql:dbname=movies;host=localhost; charset=utf8", $username, $password);
		$connection->setAttribute(PDO::ATTR_EMULATE_PREPARES, false);
		$connection->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
	if($connection == true) {
		$query = "SELECT movietitle FROM Movies where movieID=1";
		$result = $connection->prepare($query);
		$result->execute(array());

		$value = $result->fetch(PDO::FETCH_ASSOC);
		print($value["movietitle"]);
	}
?>

</body>
</html>