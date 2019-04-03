var state1 = "playing";
var state2 = "paused"
var current_state = state1;
var audio = new Audio("../../audio/animals-house-under-the-rising-sun.mp3");
audio.addEventListener('ended', function() {
    this.currentTime = 0;
    this.play();
}, false);
audio.play();

function play_pause() {
	switch (current_state) {
	case state1:
		current_state = state2;
		audio.pause();
		document.getElementById("audio").innerHTML="Music: off";
		break;
	case state2:
		current_state = state1;
		audio.play();
		document.getElementById("audio").innerHTML="Music: on";
	}
}