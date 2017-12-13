<?php
require __DIR__ ."/../lib/func.php";

$db = getDatabase("questions");

if(isset($_POST["word"])){
	$word = validateInput($_POST["word"]);

	if($word!=""){
		$query = "SELECT * FROM questions";
		$result = $db->prepare($query);
		$result->execute(array());

		$array = array();

		$result = find($word, $result);

		foreach ($result as $row) {
			$array[] = $row["question"];
		}

		echo json_encode($array);}
	else {
		echo "";
	}
}
?>