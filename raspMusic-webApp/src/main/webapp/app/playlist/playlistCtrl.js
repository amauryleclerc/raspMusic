'use strict';

app.controller('PlaylistCtrl', [ 'Player', 'PlayerService', function(Player, PlayerService) {
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
	this.remove = function(music) {
		Player.remove(music);
	}
	this.checkActive = function(music) {
		return music.position===this.music.position;
	}
		
} ]);