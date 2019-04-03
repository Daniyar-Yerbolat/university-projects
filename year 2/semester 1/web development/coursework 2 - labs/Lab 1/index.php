<?php
		$username = "root";
		$password = "";
		$dbname = "movies";
		$connection = mysqli_connect('localhost',$username,$password,$dbname);
?>
<!DOCTYPE html>
<html>
<head>
	<title></title>
</head>
<body>
<?php
	if($connection===false){
	}
	else{
		$query = mysqli_query("SELECT * from movies");
		if($query===true){
		 $rows = array();
    	while ($row = mysqli_fetch_assoc($query)) {
        $rows[] = $row;
    	}
	}
}

	print "<table>";
	$x = 0;
	while($x<count($rows){
		print "<tr>$rows[$x]</tr>";
		$x++;
	}
	print "</table>";
?>

</body>
</html>