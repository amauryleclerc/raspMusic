'use strict';

app.controller('ArtistCtrl', [ '$routeParams', 'Artist', 'Player', function($routeParams, Artist, Player) {
	this.artist = $routeParams.artistName;
	this.musics = Artist.getMusics({artistName: $routeParams.artistName});
	this.add = function(music){
		Player.add(music);
	}
		
} ]);