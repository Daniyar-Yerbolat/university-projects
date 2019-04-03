<?php
// error_reporting(1);
if(isset($_POST['submit'])){
	
	$username=$_POST['username'];
	$password1=$_POST['password1'];
	$password2=$_POST['password2'];
	$email1=$_POST['email1'];
	$email2=$_POST['email2'];
}

?>
<!DOCTYPE HTML>
<html>
<head>
	<title>Main Page</title>
	<link href="../css/common.css" rel="stylesheet">
	<link href="../css/register.css" rel="stylesheet">
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
		<div id="container-content-4">
		<form class="register" method="POST" action="<?php echo $_SERVER['PHP_SELF']; ?>">
		
			<section>
				Username: <br>
				Password: <br>
				Retype password: <br>
				Email: <br>
				Retype email: <br>
			</section>
			
			<section>
				<input type="text" name="username" required> <br>
				<input type="password" name="password1" pattern="[A-Za-z0-9._%+-]{6,20}" title="Password should be at least 6 characters long" required><br>
				<input type="password" name="password2" pattern="[A-Za-z0-9._%+-]{6,20}" title="Password should be at least 6 characters long" required> <br>
				<input type="email" name="email1" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" required> <br>
				<input type="email" name="email2" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" required> <br>
				<select size="1" style="height=25px; width=300px; padding-left:25px;">
				<option value="">-</option>
				<?php
					$directory = "../flags";
					$array = scandir($directory);
					$country = array();
					for($x=2; $x<count($array); $x++){
						
						$country = explode('.',$array[$x]);
						$country[0] = str_replace('_',' ',$country[0]);
						print "<option style=\"height: 25px; width: 300px; padding-left: 25px; background:url(../flags/".$array[$x].") left center no-repeat;\" value=\"";
						print_r ($country[0]);
						print "\">";
						print_r ($country[0]);
						print "</option> \n";
					}
					
				?>
				</select>
			</section>
			
			<section>
				<?php
				if(isset($_POST['submit'])){
					$server_db = "localhost";
					$username_db = "root";
					$password_db = "";
					$database_db = "usersystem";
					$connection = mysqli_connect($server_db, $username_db, $password_db, $database_db);

					if(!$connection){
						exit;
					}

					$request1 = "SELECT username FROM users";
					$result = mysqli_query($connection, $request1);

					if($result) {
						while($row = mysqli_fetch_assoc($result)){
							if($username == $row["username"]) {
								print "Username already exists.";
								break;
							}
							else{
								$accept1 = true;
							}
						}
					}
					else {
						echo mysql_error();
					}

					
				}
				?>
				<br>
				<?php
				if(isset($_POST['submit'])){
					if(isset($password1) && isset($password2))
					{
						if($password1 != $password2){
						print "Your passwords do not match. ";
						}
						else{
							$accept2 = true;
						}
					}
				}
				?>
				<br>
				<?php
				if(isset($_POST['submit'])){
					
					
					if(isset($email1) && isset($email2)){
						$request2 = "SELECT email FROM users";
						$result2 = mysqli_query($connection, $request2);
						if($result2) {
							$email_validation=true;
							while($row2 = mysqli_fetch_assoc($result2)){
								if($email1 == $row2["email"]) {
									print "Email already exists.";
									$email_validation=false;
									break;
								}
								
							}
						}
						
						if($email1 != $email2){
						print "Your emails do not match.";
						}
						else{
							$accept3 = $email_validation && true;
							
						}
					}
				}
				?>
				<?php
				if(isset($_POST['submit'])){
					if($accept1 && $accept2 && $accept3){
						$request3 = "INSERT INTO Users VALUES (NULL, '$username', '$email1', '$password1')";
						$add = mysqli_query($connection, $request3);
						print "Account was successfully created.";
						mysqli_close($connection);
					}
					else{
						print "Unable to create an account";
						mysqli_close($connection);
					}
				}
				?>
				</section>
		<br class="clear">
		<input type="submit" name="submit">
		</form>
		</div>
		<br class="clear">
	</div>
	
	<footer>
		<img class="avatar" src="../images/avatar.jpg" alt="Avatar">
		<ul>
			<li>Created by Daniel Potter.</li>
			<li>Contact through <a href="https://steamcommunity.com/id/daniel_faraday"><img src="../images/steam.png" alt="Steam"></a></li>
			<li>or by <a href="mailto:danik18_96@mail.ru">email</a>.</li>
		</ul>

	</footer>

</body>
</html>