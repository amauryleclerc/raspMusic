'use strict';

app.directive('musicItem', function() {

	return {
		restrict : 'E',
		transclude : false,
		// replace : true,
		scope : {
			music : '=',
			add : '&'
		},
		controller : function($scope) {
		},
		templateUrl : 'pages/music-item.html'
	};
});