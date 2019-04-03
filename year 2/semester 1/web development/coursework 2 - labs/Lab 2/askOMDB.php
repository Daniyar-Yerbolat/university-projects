<?php
// Making a RESTfull call to OMDB API with PHP:
//For example for the Home Alone movie:
$movie = $_POST["movieTitle"];
$url = "http://www.omdbapi.com/?S=$movie";
$url = str_replace(" ", "%20", $url);
$fcon = fopen($url,"r");         //  Open a connection with the OMDB API, in read (r) mode
$data_json = "";
if ($fcon) {
	while (!feof($fcon)){
		$data_json .= fgets($fcon, 4096);
	}
	// Get all the data collected from the OMDB API,
	// until the end of file, line by line, where max length of a line is 4096
	fclose($fcon);
	if($data_json!='') {
		// Convert JSON string into a PHP array variable
		$data_array = json_decode($data_json, true);
		echo $data_array["Search"][0]["Poster"];
	} else {
		echo "no data";
	}
}
?>


 