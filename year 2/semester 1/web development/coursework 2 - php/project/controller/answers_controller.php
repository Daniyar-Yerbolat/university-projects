<?php
require __DIR__ ."/../lib/func.php";

if(isset($_POST["answer"])){
	$user_id = validateInput($_SESSION["userId"]);
	$content = validateInput($_POST["content"]);
	$questionID = validateInput($_POST["question"]);

	$db = getDatabase("questions");
	$query = "INSERT INTO answers VALUES (null, ?, ?, ?, false)";
	$result = $db->prepare($query);
	$result->execute([$user_id, $questionID, $content]);

	header("Location: ".$_SERVER['HTTP_REFERER']);
	exit(); 
}

if(isset($_POST["choose"])){
	$db = getDatabase("questions");

	$answer_id = validateInput($_POST["id"]);

	$query = "UPDATE answers SET correct=1 WHERE answerID=?";
	$result = $db->prepare($query);
	$result->execute([$answer_id]);

	$query2 = "SELECT questionID FROM answers WHERE answerID=?";
	$result2 = $db->prepare($query2);
	$result2->execute([$answer_id]);
	$temp = $result2->fetch(PDO::FETCH_ASSOC);
	$question_id = $temp["questionID"];

	$query3 = "UPDATE questions SET solved=1 WHERE questionID=?";
	$result3 = $db->prepare($query3);
	$result3->execute([$question_id]);

	header("Location: ".$_SERVER['HTTP_REFERER']);
	exit(); 
}