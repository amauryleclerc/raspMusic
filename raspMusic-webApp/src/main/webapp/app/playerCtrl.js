'use strict';

app.controller('PlayerCtrl', [ 'Player', function(Player) {

	this.add = function() {
		Player.add();
	}
	this.play = function() {
		Player.play();
	}
	this.pause = function() {
		Player.pause();
	}
	this.stop = function() {
		Player.stop();
	}
} ]);