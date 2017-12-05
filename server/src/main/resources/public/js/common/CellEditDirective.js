moneyControlApp.directive('celledit', function () {
    return {
        controller: 'CellEditController',
        templateUrl: "partials/directives/celledit.html",
        restrict: "E",
        scope: {
            value: '=',
            object: '='
        }
    };
});