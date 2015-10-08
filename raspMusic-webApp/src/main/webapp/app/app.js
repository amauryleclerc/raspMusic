'use strict';

var app = angular.module('raspMusicApp', [ 'ngRoute', 'ngResource' ]);
app.constant('_', _);
app.config(function($routeProvider) {
	$routeProvider.when('/', {
		templateUrl : 'pages/player.html',
		controller : 'PlayerCtrl as playerCtrl'
	}).when('/musics', {
		templateUrl : 'pages/musics.html',
		controller : 'MusicsCtrl as musicsCtrl'
	}).when('/artists', {
		templateUrl : 'pages/artists.html',
		controller : 'ArtistsCtrl as artistsCtrl'
	})

});