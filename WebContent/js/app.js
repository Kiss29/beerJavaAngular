'use strict';

/* App Module */

var angularBeer = angular.module('AngularBeer', [
  'ngRoute',
  'BeerControllers',
  'BeerFilters',
  'BeerServices'
]);

angularBeer.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/beers', {
        templateUrl: 'partials/beer-list.html',
        controller: 'BeerListCtrl'
      }).
      when('/beers/:beerId', {
        templateUrl: 'partials/beer-detail.html',
        controller: 'BeerDetailCtrl'
      }).
      when('/beer-create', {
    	  templateUrl: 'partials/beer-create.html',
    	  controller: 'BeerCreateCtrl'
      }).
      when('/beer-modify/:beerId', {
    	  templateUrl: 'partials/beer-modify.html',
    	  controller: 'BeerModifyCtrl'
      }).
      otherwise({
        redirectTo: '/beers'
      });
  }]);