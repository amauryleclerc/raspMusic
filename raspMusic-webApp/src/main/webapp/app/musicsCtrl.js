'use strict';

app.controller('MusicsCtrl', [ 'Musics',  function(Musics) {
	this.musics = Musics.query();

	

		
} ]);