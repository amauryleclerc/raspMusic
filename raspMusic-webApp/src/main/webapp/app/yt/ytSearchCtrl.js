'use strict';

app.controller('YtSearchCtrl', [ '$scope', 'gapi', 'Player', function($scope, gapi, Player) {
	this.query = "";
	this.videos = [];

	this.search = function() {
		console.log("search");
		gapi.login().then(callbackCreator(this.query, this.videos));
	}
	this.add = function(video){
		console.log("add");
		var music = {id:video.id.videoId, title:video.snippet.title, album:{name:video.snippet.channelTitle}};
		Player.add(music);
	}
	
	
	function callbackCreator(query, videos) {
		return function() {
			console.log("query" + query)
			gapi.call("youtube", "v3", "search", "list", {
				part : "snippet",
				type : "video",
				q : query
			}).then(callbackCreatorVideo(videos));
		}
	}
	function callbackCreatorVideo(videos) {
		return function(response) {
			console.log(response);
			videos.length = 0;
			for (var i = 0, len = response.items.length; i < len; ++i) {
				videos.push(response.items[i]);
			}

		}
	}
} ]);
