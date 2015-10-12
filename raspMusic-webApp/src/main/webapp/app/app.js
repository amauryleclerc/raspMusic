'use strict';

var app = angular.module('raspMusicApp', [ 'ngRoute', 'ngResource', 'gapi' ]);
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
	}).when('/artist/:artistName', {
		templateUrl : 'pages/artist.html',
		controller : 'ArtistCtrl as artistCtrl'
	}).when('/yt', {
		templateUrl : 'pages/yt-search.html',
		controller : 'YtSearchCtrl as ytSearchCtrl'
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
