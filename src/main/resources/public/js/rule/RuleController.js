'use strict';

moneyControlApp.controller('RuleController', ['$scope',
    function RuleController($scope) {
        $scope.rules=[
            {
                "id": 3,
                "ruleOrder": 1,
                "nameCondition": "MARKET",
                "amountCondition": null,
                "category": "Nourriture",
                "subcategory": "Supermarché",
                "recipient": "Market",
                "comment": null
            }];


        $scope.delete = function (rule) {
            console.log("Delete rule" + rule.id);
        };

        $scope.duplicate = function (rule) {
            console.log("Duplicate rule" + rule.id);
        };


    }]);