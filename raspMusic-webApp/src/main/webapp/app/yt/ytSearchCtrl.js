'use strict';

app.controller('YtSearchCtrl', [ '$scope', 'gapi', function($scope, gapi) {
	this.query = "";
	this.videos = [];
	this.search = function() {

		gapi.login().then(callbackCreator(this.query, this.videos));
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

			for (var i = 0, len = response.items.length; i < len; ++i) {
				videos.push(response.items[i]);
			}

		}
	}
} ]);