moneyControlApp.directive('navbar', function () {
    return {
        controller: 'NavBarController',
        templateUrl: "partials/directives/navbar.html",
        restrict: "E"
    };
});