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
			method : 'PUT', url:'/api/player/add'
		},
		'next' : {
			method : 'POST', url:'/api/player/next'
		},
		'previous' : {
			method : 'POST', url:'/api/player/previous'
		},
		'getCurrent' : {
			method : 'GET', url:'/api/player/current'
		},
		
	});
} ]);