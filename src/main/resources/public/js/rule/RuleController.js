'use strict';

moneyControlApp.controller('RuleController', ['$scope', 'Rule',
    function RuleController($scope, Rule) {
        $scope.rules = Rule.query();


        $scope.delete = function (rule) {
            console.log("Delete rule" + rule.id);
        };

        $scope.duplicate = function (rule) {
            console.log("Duplicate rule" + rule.id);
        };


    }]);