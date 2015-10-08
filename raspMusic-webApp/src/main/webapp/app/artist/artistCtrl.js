'use strict';

app.controller('ArtistCtrl', [ '$routeParams', 'Artist', function($routeParams, Artist) {
	this.artist = $routeParams.artistName;
//	this.artist = Artist.get({name: $routeParams.artistName});
	this.musics = Artist.getMusics({artistName: $routeParams.artistName});

		
} ]);