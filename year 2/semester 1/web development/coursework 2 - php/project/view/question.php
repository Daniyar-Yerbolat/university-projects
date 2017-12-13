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
		$db = getDatabase("questions");
		$question_id = validateInput($_GET["id"]);

		$query = "SELECT * FROM questions WHERE questionID=?";
		$result = $db->prepare($query);
		$result->execute([$question_id]);

		$temp = $result->fetch(PDO::FETCH_ASSOC);

		print $temp["question"]."<br/>";
		print $temp["description"]."<br/>";
		print "Asked by ".getUsername($temp["userID"])."<br/>";

		$query2 = "SELECT * FROM answers WHERE questionID=?";
		$result2 = $db->prepare($query2);
		$result2->execute([$temp["questionID"]]);

		foreach ($result2 as $row){
			print "<div class=\"container\">";
			print "<p>".$row["content"]."</p>";
			print getUsername($row["userID"]);
			if (isset($_SESSION["userId"]) && $_SESSION["userId"]==$temp["userID"] && $temp["solved"]==false){
			?>
			<form method="post" action="../controller/answers_controller.php">
				<input type="hidden" value="<?php print $row["answerID"] ?>" name="id"/>
				<input type="submit" value="Choose" name="choose"/>
			</form>
			<?php
			}
			print "</div>";

		}


	if($temp["solved"]==false){
		if(isset($_SESSION["userId"]) && $_SESSION["userId"]!=$temp["userID"]){
			print "<div>";
		?>
			<form method="post" action="../controller/answers_controller.php">
				<input type="hidden" value="<?php print $question_id; ?>" name="question"/>
				<textarea rows="4" cols="50" name="content" placeholder="type answer here" required="required"></textarea>
				<input type="submit" name="answer"/>
			</form>
		<?php
		}
	}
	?>
</body>
</html>