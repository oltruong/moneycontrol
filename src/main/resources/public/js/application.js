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
    $routeProvider.when('/operations/:year', {
        templateUrl: 'partials/views/operations.html',
        controller: 'OperationController'
    });
    $routeProvider.when('/operations/unclassified', {
        templateUrl: 'partials/views/operations.html',
        controller: 'OperationController'
    });
    $routeProvider.when('/operations', {
        templateUrl: 'partials/views/operations.html',
        controller: 'OperationController'
    });
    $routeProvider.otherwise({redirectTo: '/home'});
}]);