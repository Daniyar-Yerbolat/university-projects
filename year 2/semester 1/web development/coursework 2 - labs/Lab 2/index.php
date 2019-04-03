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
		$connection=new PDO("mysql:dbname=movies;host=localhost; charset=utf8", $username, $password);
		$connection->setAttribute(PDO::ATTR_EMULATE_PREPARES, false);
		$connection->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
	if($connection == true) {
		$query = "SELECT * FROM Movies";
		$result = $connection->prepare($query);
		$result->execute(array());
		session_start();
		if(count($result) != 0){
			print "<table>\n";
			print "<tr>\n";
			print "<th>ID</th>\n";
			print "<th>Title</th>\n";
			print "<th>Genre</th>\n";
			print "<th>Available in english?</th>\n";
			print "<th>Rating</th>\n";
			print "<th>Add</th>";
			print "<th>Delete</th>";
			print "</tr>\n";
	    	foreach ($result as $row) {
	        	print "<tr>\n";
				print "<td>".$row['movieID']."</td>\n";
				print "<td>".$row['movieTitle']."</td>\n";
				print "<td>".$row['movieGenre']."</td>\n";
				if($row['movieType']==1){
					print "<td>yes</td>\n";}
				else{
					print "<td>no</td>\n";
				}
				print "<td>".$row['movieRating']."</td>\n";
				print "<td><form action=\"controller/movies_controller.php\" method=\"post\">\n";
				$id = $row['movieID'];
				print "<input type=\"hidden\" name=\"movie\" value=".$id."/>\n";
				print "<input type=\"submit\" name=\"add_movie\" value=\"Add Movie\"/>\n";
				print "</form>\n";
				print "</td>\n";
				print "<td><button id=\"delete$id\" onclick=\"return deletemovie($id)\">Delete</button></td>\n";
				print "</tr>\n";
	    	}
	       	print "</table>\n";
		}
	}
?>

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
</script>



</body>
</html>