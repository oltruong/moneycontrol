'use strict';

moneyControlApp.controller('RuleController', ['$scope', 'Rule',
    function RuleController($scope, Rule) {
        $scope.rules = Rule.query();


        $scope.delete = function (rule) {
            Rule.delete(rule).$promise.then(function () {
                var index = $scope.rules.indexOf(rule);
                if (index > -1) {
                    $scope.rules.splice(index, 1);
                }
            });
        };

        $scope.duplicate = function (rule) {

            var newRule = {};
            newRule.ruleOrder = rule.ruleOrder;
            newRule.nameCondition = rule.nameCondition;
            newRule.amountCondition = rule.amountCondition;
            newRule.category = rule.category;
            newRule.subcategory = rule.subcategory;
            newRule.recipient = rule.recipient;
            newRule.comment = rule.comment;


            Rule.save(newRule).$promise.then(function (savedRule) {
                $scope.rules.push(savedRule);
            });
        };

    }]);