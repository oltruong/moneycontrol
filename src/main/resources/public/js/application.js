'use strict';


var teamagApp = angular.module('moneyControlApp', ['ngRoute']);

teamagApp.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/home', {templateUrl: 'partials/views/home.html', controller: 'HomeController'});
    $routeProvider.otherwise({redirectTo: '/home'});
}]);

//var teamagApp = angular.module('teamagApp', ['ngRoute', 'ngResource', 'ngCookies', 'ui.calendar', 'colorpicker.module', 'angular-md5']);
