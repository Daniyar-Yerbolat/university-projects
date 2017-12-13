<?php
	require __DIR__ ."/../lib/func.php";
?>

<!DOCTYPE html>
<html>
<head>
	<title></title>
	<meta name="viewport" content="width=device-width, initial-scale=1"/>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>

	<link rel="stylesheet" type="text/css" href="./css/index.css"/>
</head>
<body>
<?php
if(isset($_POST["search"])){
	$filter = validateInput($_POST["filter"]);
	$keyword = strtolower(validateInput($_POST["keyword"]));
	$count = strlen($keyword);

	$db = getDatabase("questions");

	if($filter=="all"){
		$query = "SELECT * FROM questions";
		$result = $db->prepare($query);
		$result->execute(array());
	} else if($filter=="unsolved"){
		$query = "SELECT * FROM questions AS q WHERE q.solved<>true GROUP BY question";
		$result = $db->prepare($query);
		$result->execute(array());
	} else if($filter=="solved"){
		$query = "SELECT * FROM questions AS q WHERE q.solved=true GROUP BY question";
		$result = $db->prepare($query);
		$result->execute(array());
	}



	if($keyword!=null && $keyword!=""){
		$result=find($keyword, $result);
	}

	// if the query was successful.
	if($result==true){
		foreach ($result as $row) {
			$id = $row["questionID"];
			print "<div>";
			print "<a href='question.php?id=$id'>".$row["question"]."</a><br/>";
			print $row["description"]."<br/>";
			print getUsername($row["userID"])."<br/>";
			print "</div>";
		}
	}
}
?>
</body>
</html>