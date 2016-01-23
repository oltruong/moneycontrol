'use strict';

moneyControlApp.controller('OperationController', ['$scope', 'Operation',
    function OperationController($scope, Operation) {

        $scope.operations = [{
            "id": 1878,
            "creationDate": 1443571200000,
            "year": 2015,
            "month": 9,
            "name": "TESTTEST ",
            "amount": -200.0,
            "category": "Category",
            "subcategory": "SubCategory",
            "recipient": "Recipient",
            "comment": null
        }, {
            "id": 1879,
            "creationDate": 1443484800000,
            "year": 2015,
            "month": 9,
            "name": "TEST",
            "amount": 330.67,
            "category": "Category",
            "subcategory": "SubCategory",
            "recipient": null,
            "comment": null
        }];

        $scope.totalOperations = function () {
            return $scope.operations.length;
        };

        $scope.showTab = function (tab) {
            if (tab === 0) {
                return 'active';
            } else {
                return '';
            }
        }
        ;

    }]);