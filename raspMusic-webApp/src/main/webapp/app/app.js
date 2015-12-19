'use strict';

var app = angular.module('raspMusicApp', [ 'ngRoute', 'ngResource', 'gapi', 'ngWebsocket' ]);
app.constant('_', _);
app.config(function($routeProvider) {
	$routeProvider.when('/', {
		templateUrl : 'app/playlist/playlist.html',
		controller : 'PlaylistCtrl',
		controllerAs :'playlistCtrl'
	}).when('/player', {
		templateUrl : 'app/player/player.html',
		controller : 'PlayerCtrl',
		controllerAs :'playerCtrl'
	}).when('/musics', {
		templateUrl : 'app/music/musics.html',
		controller : 'MusicsCtrl',
		controllerAs :'musicsCtrl'
	}).when('/artists', {
		templateUrl : 'app/artist/artists.html',
		controller : 'ArtistsCtrl',
		controllerAs :'artistsCtrl'
	}).when('/artist/:artistName', {
		templateUrl : 'app/artist/artist.html',
		controller : 'ArtistCtrl',
		controllerAs :'artistCtrl'
	}).when('/yt', {
		templateUrl : 'app/yt/yt-search.html',
		controller : 'YtSearchCtrl',
		controllerAs :'ytSearchCtrl'
			
	})

});
app.config(['gapiProvider', '$routeProvider', function(gapiProvider, $routeProvider) {
    gapiProvider.apiKey('AIzaSyB2xUhofT1xuA_eod2v4ap58X5mOtFtNHg') // api for app (you can create them in dev console)
        .clientId('786976564501-i1dgcl989k9qug6brteok2drjl23msud.apps.googleusercontent.com'); // you can obtain them in gogle dev console
       // scopes is space delimited string
}]);



//angular.module('ngm.NgGapi')
//.provider('OauthService', NgGapi.Config)
//.config(function (OauthServiceProvider) {
//    OauthServiceProvider.setScopes('https://www.googleapis.com/auth/drive.file');
//    OauthServiceProvider.setClientID('786976564501-i1dgcl989k9qug6brteok2drjl23msud.apps.googleusercontent.com');
//    OauthServiceProvider.setTokenRefreshPolicy(NgGapi.TokenRefreshPolicy.ON_DEMAND);
//    OauthServiceProvider.setNoAccessTokenPolicy(999);                 // 0 = fail, > 0 = retry after x
//});
//app
//		.value(
//				'GoogleApp',
//				{
//					apiKey : 'AIzaSyB2xUhofT1xuA_eod2v4ap58X5mOtFtNHg',
//					clientId : '786976564501-i1dgcl989k9qug6brteok2drjl23msud.apps.googleusercontent.com',
//					scopes : [ 'https://www.googleapis.com/auth/drive',
//							'https://www.googleapis.com/auth/youtube',
//							'https://www.googleapis.com/auth/userinfo.profile' ]
//				});

// 9ZQMZ_6ZQLCYCd1WHmokebCr
