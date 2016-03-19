'use strict';

moneyControlApp.controller('OperationController', ['$scope', 'Operation',
    function OperationController($scope, Operation) {

        $scope.selectedTab = 0;

        $scope.operations = [{
            "id": 1878,
            "creationDate": 1443571200000,
            "year": 2015,
            "month": 9,
            "name": "TESTTEST ",
            "amount": -200.0,
            "category": "loyer",
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
            "category": "salaire",
            "subcategory": "SubCategory",
            "recipient": null,
            "comment": null
        }, {
            "id": 1879,
            "creationDate": 1443484800000,
            "year": 2015,
            "month": 9,
            "name": "TEST",
            "amount": 50.67,
            "category": "salaire",
            "subcategory": "subok",
            "recipient": null,
            "comment": null
        }];

        $scope.totalOperations = function () {
            return $scope.operations.length;
        };

        $scope.showTab = function (tab) {
            if (tab === $scope.selectedTab) {
                return 'active';
            } else {
                return '';
            }
        };

        $scope.setTab = function (tab) {
            $scope.selectedTab = tab;
        };


        $scope.categoryOrder='categoryName';

        $scope.setCategoryOrder = function (value){
            if ($scope.categoryOrder===value){
                $scope.categoryOrder='-'+value;

            } else {
                $scope.categoryOrder=value;

            }
        };

        loadCategories();

        $scope.total = function () {
            var total = 0;
            angular.forEach($scope.filteredOperations, function (operation) {
                total += operation.amount;
            });
            return total;
        };

        $scope.sum = function (categories) {
            var total = 0;

            if (typeof categories != 'undefined') {

                for (var index = 0; index < categories.length; index++) {
                    total += categories[index].amount;
                }
            }
            return total;
        };

        function loadCategories() {
            var categoryMap = {};


            for (var index = 0; index < $scope.operations.length; index++) {
                var operation = $scope.operations[index];
                if (operation.category !== null) {
                    if (operation.category in categoryMap) {
                        var currentcategory = categoryMap[operation.category];
                        currentcategory.amount += operation.amount;
                    } else {
                        categoryMap[operation.category] = ({name: operation.category, amount: operation.amount})
                    }
                }
            }

            $scope.negative_categories = [];
            $scope.positive_categories = [];


            for (var key in  categoryMap) {
                var category = categoryMap[key];
                if (category.amount > 0) {
                    $scope.positive_categories.push(category);
                } else {
                    $scope.negative_categories.push(category);
                }
            }


            var detailedCategoryMap = {};


            for (var index = 0; index < $scope.operations.length; index++) {
                var operation = $scope.operations[index];
                if (operation.category !== null) {
                    if (operation.category + operation.subcategory in detailedCategoryMap) {
                        var currentcategory = detailedCategoryMap[operation.category];
                        currentcategory.amount += operation.amount;
                    } else {
                        detailedCategoryMap[operation.category + operation.subcategory] = ({
                            categoryName: operation.category,
                            name: operation.subcategory,
                            amount: operation.amount
                        })
                    }
                }
            }

            $scope.negative_subCategories = [];
            $scope.positive_subCategories = [];


            for (var key in  detailedCategoryMap) {
                var detailedCategory = detailedCategoryMap[key];
                if (detailedCategory.amount > 0) {
                    $scope.positive_subCategories.push(detailedCategory);
                } else {
                    $scope.negative_subCategories.push(detailedCategory);
                }
            }
        }

    }]);