'use strict';

app.controller('ArtistsCtrl', [ 'Artists',  function(Artists) {
	this.artists = Artists.query();

	

		
} ]);