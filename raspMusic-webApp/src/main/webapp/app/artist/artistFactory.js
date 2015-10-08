app.factory('Artist', [ '$resource', function($resource) {
	return $resource('/api/artist', null,  {
		'getMusics' : {
			method : 'GET', url:'/api/artist/:artistName/musics', isArray:true
		}
		
	});
} ]);