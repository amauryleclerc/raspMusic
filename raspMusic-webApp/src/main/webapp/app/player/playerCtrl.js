'use strict';

app.controller('PlayerCtrl', [ 'Player', 'PlayerService', function(Player, PlayerService) {
	this.music = PlayerService.currentMusic;
	this.playlist = PlayerService.playlist;
	this.state  = PlayerService.state;

	this.play = function() {
		Player.play();
	}
	this.pause = function() {
		Player.pause();
	}
	this.stop = function() {
		Player.stop();
	}
	this.next = function() {
		Player.next();
	}
	this.previous = function() {
		Player.previous();
	}
	

		
} ]);