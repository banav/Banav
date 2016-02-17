'use strict';

/**
 * @ngdoc overview
 * @name vendasApp
 * @description
 * # vendasApp
 *
 * Main module of the application.
 */
var app = angular
  .module('vendasApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
    'vendasApp.resources'
  ])
  .config(['$routeProvider', function ($routeProvider) {
    $routeProvider
      .when('/pesquisa', {
        templateUrl: 'views/pesquisa.html',
        controller: 'PesquisaCtrl'
      })
      .when('/resultado', {
        templateUrl: 'views/resultado.html',
        controller: 'ResultadoCtrl'
      })
      .otherwise({
        redirectTo: '/pesquisa'
      });
  }]);

angular.module('vendasApp.resources', []);

app.rootContext = 'http://localhost:8080/banav/';
