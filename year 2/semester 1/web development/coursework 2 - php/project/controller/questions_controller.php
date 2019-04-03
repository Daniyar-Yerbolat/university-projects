<?php
require __DIR__ ."/../lib/func.php";
// $query = "SELECT * FROM questions AS q INNER JOIN answers AS a ON q.questionID=a.questionID WHERE a.correct=false;";
if(isset($_POST["ask"]) && isset($_SESSION["userId"])){
	$question = validateInput($_POST["question"]);
	$description = validateInput($_POST["description"]);

	$db = getDatabase("questions");
	$query = "INSERT INTO questions VALUES (null, ?, ?, ?, false)";
	$result = $db->prepare($query);
	$result->execute([$_SESSION["userId"], $question, $description]);

	$query = "SELECT questionID FROM questions WHERE question=?";
	$result = $db->prepare($query);
	$result->execute([$question]);
	$temp = $result->fetch(PDO::FETCH_ASSOC);
	$id = $temp["questionID"];

	header("Location:http://localhost/website_php/view/question.php?id=$id");
	exit();
} else{
	$_SESSION["error"] = array();
	array_push($_SESSION["error"], "You are not logged in.");
	header("Location: ".$_SERVER['HTTP_REFERER']);
	exit();
}
?>