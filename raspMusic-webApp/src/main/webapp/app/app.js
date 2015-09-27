'use strict';

var app =  angular.module('raspMusicApp',  ['ngRoute']);
app.constant('_', _);
app.config(function($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl : 'pages/player.html',
            controller  : 'PlayerCtrl as playerCtrl'
        })

 
//        .when('/article/:libelle', {
//            templateUrl : 'pages/article.html',
//            controller  : 'ArticleDetailCtrl as articleDetailCtrl'
//        })
//    .when('/ajouter', {
//            templateUrl : 'pages/ajoutArticle.html',
//            controller  : 'AjoutArticleCtrl as ajoutArticleCtrl'
//        })

});