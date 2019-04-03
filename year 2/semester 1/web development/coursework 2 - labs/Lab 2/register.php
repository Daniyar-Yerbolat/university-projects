<?php

$username = "root";
$password = "";
$dbname = "movies";
$connection=new PDO("mysql:dbname=movies;host=localhost; charset=utf8", $username, $password);
$connection->setAttribute(PDO::ATTR_EMULATE_PREPARES, false);
$connection->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

session_start();
?>

<!DOCTYPE html>
<html>
<head>
	<title>Register</title>
	<link rel="stylesheet" type="text/css" href="css/register.css">
</head>
<body>
	<?php if(isset($_SESSION["success_regis"]) && $_SESSION["success_regis"] == true){
		unset($_SESSION["success_regis"]);
		print "Account was successfully created.";
		}
	else { ?>
	<form class="register" method="POST" action="controller/sign_in_controller.php">
			<section>
				Username: <br/><br/>
				Password: <br/><br/>
				Retype password:
			</section>
			<section>
				<input type="text" name="username" required="required"/> <br/><br/>
				<input type="password" name="password1" pattern="[A-Za-z0-9._%+-]{6,20}" title="Password should be at least 6 characters long" required="required"/><br/><br/>
				<input type="password" name="password2" pattern="[A-Za-z0-9._%+-]{6,20}" title="Password should be at least 6 characters long" required="required"/>
			</section>
		<br class="clear"/>
		<input type="submit" name="register" value="Register"/>
	</form>
	<?php } ?>
</body>
</html>