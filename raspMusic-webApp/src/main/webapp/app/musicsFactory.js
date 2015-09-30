app.factory('Musics', [ '$resource', function($resource) {
	return $resource('/api/musics', null, null);
} ]);