moneyControlApp.directive('numberedit', function () {
    return {
        controller: 'CellEditController',
        templateUrl: "partials/directives/numberedit.html",
        restrict: "E",
        scope: {
            value: '=',
            object: '='
        }
    };
});