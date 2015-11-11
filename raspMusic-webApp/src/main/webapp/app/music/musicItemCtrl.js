'use strict';

app.directive('musicItem', function() {

	return {
		restrict : 'EA',
		transclude : false,
		// replace : true,
		scope : {
			music : '=',
			isPlaylist: '=',
			isCurrent: '=',
			add : '&',
			remove : '&'
				
		},
		controller : function($scope) {
		},
		templateUrl : 'pages/music-item.html'
	};
});