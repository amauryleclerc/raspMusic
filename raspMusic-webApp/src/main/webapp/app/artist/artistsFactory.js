app.factory('Artists', [ '$resource', function($resource) {
	return $resource('/api/artists', null, null);
} ]);