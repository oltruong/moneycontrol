'use strict';


var moneyControlApp = angular.module('moneyControlApp', ['ngRoute', 'ngResource', 'angularMoment']);

moneyControlApp.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/home', {templateUrl: 'partials/views/home.html', controller: 'HomeController'});
    $routeProvider.when('/rules', {templateUrl: 'partials/views/rules.html', controller: 'RuleController'});
    $routeProvider.when('/fileupload', {
        templateUrl: 'partials/views/fileupload.html',
        controller: 'FileUploadController'
    });
    $routeProvider.when('/operations/:year/:month', {
        templateUrl: 'partials/views/operations.html',
        controller: 'OperationController'
    });
    $routeProvider.when('/operations/:any', {
        templateUrl: 'partials/views/operations.html',
        controller: 'OperationController'
    });
    $routeProvider.otherwise({redirectTo: '/home'});
}]);

//var moneyControlApp = angular.module('moneyControlApp', ['ngRoute', 'ngResource', 'ngCookies', 'ui.calendar', 'colorpicker.module', 'angular-md5']);
