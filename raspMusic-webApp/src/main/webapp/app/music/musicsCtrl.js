'use strict';

app.controller('MusicsCtrl', [ 'Musics', 'Player',  function(Musics, Player) {
	this.musics = Musics.query();
	this.add = function(music){
		console.log(music);
		Player.add(music);
	}
	

		
} ]);