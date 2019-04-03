var image = document.getElementById("game");

var string = "<%=array_in_string%>";

var array = string.split(" ");
var x = 0;

for(var y = 0; y<array.length; y++){
	document.write(array[y]);
}

var current = image1;

function next_image() {

	if(x<array.legth){
		x++;
		current = array[x];
	}

	image.setAttribute("src", current);
}

function previous_image() {

	if(x>0){
		x--;
		current = array[x];
	}

	image.setAttribute("src", current);
}