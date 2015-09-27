app.factory('Player', [ '$resource', function($resource) {
	return $resource(null, null, {
		'play' : {
			method : 'POST', url:'/api/player/play'
		},
		'pause' : {
			method : 'POST', url:'/api/player/pause'
		},
		'stop' : {
			method : 'POST', url:'/api/player/stop'
		},
		'add' : {
			method : 'POST', url:'/api/player/add'
		},
	});
} ]);