<?php
require __DIR__ ."/../lib/func.php";
require __DIR__ ."/../lib/heredocs.php";

?>

<!DOCTYPE html>
<html>
<head>
	<title>Register</title>
	<meta name="viewport" content="width=device-width, initial-scale=1"/>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>

	<link rel="stylesheet" type="text/css" href="./css/index.css"/>
</head>
<body>
<div class="container">
	<?php
		print register();
	if(isset($_SESSION["error"])){
		$array = $_SESSION["error"];
		$x=0;
		while($x<count($array)){
			print $array[$x];
			$x++;
		}
		unset($_SESSION["error"]);
	}
	?>
	</div>
</body>
</html>