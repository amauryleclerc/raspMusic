
'use strict';
app.directive('playerItem', function() {
return {
restrict : 'EA',
transclude : false,
// replace : true,
scope : {
},
controller : 'PlayerCtrl',
controllerAs : 'playerCtrl',
templateUrl : 'app/player/player.html'
};
});