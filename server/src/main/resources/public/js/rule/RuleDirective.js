moneyControlApp.directive('rules', function () {
    return {
        controller: 'RuleController',
        templateUrl: "partials/directives/rules.html",
        restrict: "E",
        scope: {
            value: '=',
            object: '='
        }
    };
});