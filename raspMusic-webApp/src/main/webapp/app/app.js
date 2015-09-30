'use strict';

var app =  angular.module('raspMusicApp',  ['ngRoute','ngResource']);
app.constant('_', _);
app.config(function($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl : 'pages/player.html',
            controller  : 'PlayerCtrl as playerCtrl'
        })
             .when('/musics', {
            templateUrl : 'pages/musics.html',
            controller  : 'MusicsCtrl as musicsCtrl'
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