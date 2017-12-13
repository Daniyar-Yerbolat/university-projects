function findQuestions(){
	var word = document.getElementById("word").value;
	var param = "word=" + word;
	var xmlhttp;

	if (window.XMLHttpRequest) {
	// IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp=new XMLHttpRequest();
	} else {
	// IE6, IE5
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}

	xmlhttp.onreadystatechange = function() {
		var div = document.getElementById("update");
		if ((this.readyState === 4) && (this.status === 200)) {
			div.innerHTML = "";
			var decoded = JSON.parse(this.responseText);
			console.log(decoded);
			var x = 0;
			while(x < decoded.length){
				div.innerHTML += decoded[x] +"<br/>";
				x++;
			}
			}
		}

xmlhttp.open("POST", "../controller/ajax_controller.php", true);
xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
xmlhttp.send(param);
}