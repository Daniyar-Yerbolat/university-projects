var x = 0;
var string = document.getElementById("array_of_images").value;
var array = string.split("  ");
var image = document.getElementById("game");
var current = "";
function next_image(){
	image.setAttribute("src", "<%=game.getImage()[1]%>");
}