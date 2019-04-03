<?php
$username_db = "root";
$password_db = "";
$dbname = "movies";
$connection=new PDO("mysql:dbname=movies;host=localhost; charset=utf8", $username_db, $password_db);
$connection->setAttribute(PDO::ATTR_EMULATE_PREPARES, false);
$connection->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

session_start();
?>

<!DOCTYPE html>
<html>
<head>
	<title></title>
	<link rel="stylesheet" type="text/css" href="css/login.css">
	<script type="text/javascript">
	function deletemovie(movieid){
	var param = "deleteID=" + movieid;

	var xmlhttp;
	if (window.XMLHttpRequest) {
	// IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp=new XMLHttpRequest();
	} else {
	// IE6, IE5
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}

	xmlhttp.onreadystatechange = function() {
		var del = document.getElementById("delete" + movieid);
		if ((this.readyState === 4) && (this.status === 200)) {
			if(this.responseText=="done"){
                    del.innerHTML = "deleted";
            }
            else{
            	del.innerHTML = "sorry";
            }                
		}
	}
xmlhttp.open("POST", "controller/ajax_controller.php", true);
xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
xmlhttp.setRequestHeader("Content-length", param.length);
xmlhttp.setRequestHeader("Connection", "close");
xmlhttp.send(param);
return false;
}

function getPoster(id, movieTitle) {
			//movieTitle = movieTitle.toLowerCase();
			//movieTitle = movieTitle.replace(" ", "%20");
			param = "movieTitle=" + movieTitle;
            var xmlhttp = new XMLHttpRequest();
            xmlhttp.open('POST', "askOMDB.php", true);
            xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			xmlhttp.setRequestHeader("Content-length", param.length);
			xmlhttp.setRequestHeader("Connection", "close");
            xmlhttp.send(param);
           	xmlhttp.onreadystatechange = function() {
                if(xmlhttp.readyState == 4 && xmlhttp.status==200) {
                	var poster = document.getElementById("poster");
                    poster.setAttribute("src", xmlhttp.responseText);
                }
            }
            return false;
        }
</script>
</head>
<body>
<?php
function getTable($rows){
	$html_table = <<<mytable
	<table>
		<tr>
			<th>Title</th>
			<th>Genre</th>
			<th>Available in English</th>
			<th>Rating</th>
			<th>Poster</th>
			<th>Add</th>
			<th>Remove</th>
		</tr>
		<tfoot>
			$rows
		</tfoot>
	</table>
mytable;
	return $html_table;
}

function getRows($connection){
	$html_row=<<<rows
rows;
	$query = "SELECT * FROM Movies";
	$result = $connection->prepare($query);
	$result->execute(array());

	foreach ($result as $row) {
		$english = "";
		$already_added = false;
		$id = $row['movieID'];
		$title = $row["movieTitle"];
		$genre = $row['movieGenre'];
		$rating = $row['movieRating'];

		if($row['movieType']==1){
			$english = "yes";
		}
		else{
			$english = "no";
		}

		$query2 = "SELECT movieID FROM users_movies where userID=?";
		$result2 = $connection->prepare($query2);
		$result2->execute([$_SESSION["userId"]]);

		foreach($result2 as $row2){
			if($row2["movieID"]==$row["movieID"]){
				$already_added = true;
			}
		}
	$add_row = "Already added.";
	if(!$already_added){
		$add_row = <<<rows
		<form method="post" action="controller/movies_controller.php">
				<input type="hidden" name="movie" value="$id"/>
				<input type="submit" name="add_movie" value="Add Movie"/>
		</form>
rows;
}

	$html_row=<<<rows
	$html_row
	<tr>
		<td>$title</td>
		<td>$genre</td>
		<td>$english</td>
		<td>$rating</td>
		<td id="poster$id">
		<button  onclick="return getPoster('poster$id', '$title')">Poster</button>
		</td>
		<td>
			$add_row
		</td>
		<td>
			<button id="delete$id" onclick="return deletemovie($id)">Delete</button>
		</td>
	</tr>
rows;
}
return $html_row;
}
	if(isset($_SESSION["user"]) && (isset($_POST["logout"]) == false)){
		$user = $_SESSION["user"];
		print "Welcome back, $user.<br/>\n";
		print "Your favourite movies are: <br/>\n";
		print getTable(getRows($connection));
		?>

		<form method="post" action="controller/sign_in_controller.php">
			<input type="submit" name="logout" value="Log Out"/>
		</form>
	<?php }else{ ?>
			<form class="login" method="POST" action="controller/sign_in_controller.php">
				<section>
					Username:<br/><br/>
					Password:
				</section>
				<section>
					<input type="text" name="username" required="required"/><br/><br/>
					<input type="password" name="password" pattern="[A-Za-z0-9._%+-]{6,20}" title="Password should be at least 6 characters long" required="required"/>
				</section>
				<input type="submit" name="login" value="Log In"/>
			</form>
	<a href="register.php">Register</a>
	<?php }

	if(isset($_SESSION["success_login"]) && $_SESSION["success_login"]==false){
		unset($_SESSION["success_login"]);
		print "<br/>Either username or password are incorrect.\n";
	}
	?>
<img id="poster" src=""/>
</body>
</html>
