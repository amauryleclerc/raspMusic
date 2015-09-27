'use strict';

app.controller('PlayerCtrl', [ 'Player', 'PlayerService', function(Player, PlayerService) {
	this.music = PlayerService.currentMusic;
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