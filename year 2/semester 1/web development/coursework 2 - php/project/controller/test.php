<?php
require __DIR__ ."/../lib/func.php";

$db = getDatabase("questions");

$query = "SELECT * FROM questions";
$result = $db->prepare($query);
$result->execute(array());

$array = array();

$result = find("win", $result);

foreach ($result as $row) {
	$array[] = $row["question"];
}

print json_encode($array);

?>