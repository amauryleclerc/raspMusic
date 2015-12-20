'use strict';

app.controller('ArtistCtrl', [ '$stateParams', 'Artist', 'Player', function($stateParams, Artist, Player) {
	this.artist = $stateParams.artistName;
	this.musics = Artist.getMusics({artistName: $stateParams.artistName});
	this.add = function(music){
		Player.add(music);
	}
		
} ]);