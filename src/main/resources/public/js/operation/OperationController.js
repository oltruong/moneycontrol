'use strict';

moneyControlApp.controller('OperationController', ['$scope', '$routeParams', '$location', 'Operation',
    function OperationController($scope, $routeParams, $location, Operation) {

        $scope.selectedTab = 0;
        var categoryParam;
        if ($location.path().indexOf('unclassified') > -1) {
            categoryParam = 'empty';
            $routeParams.year = undefined;
            $routeParams.month = undefined;
        }

        $scope.operations = [];
        Operation.query({
            year: $routeParams.year,
            month: $routeParams.month,
            category: categoryParam
        }).$promise.then(function (operations) {
            $scope.operations = operations;
            loadCategories()
        });


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


        $scope.categoryOrder = 'categoryName';

        $scope.setCategoryOrder = function (value) {
            if ($scope.categoryOrder === value) {
                $scope.categoryOrder = '-' + value;

            } else {
                $scope.categoryOrder = value;

            }
        };


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

                var operationCategory = operation.category;
                if (operationCategory === null) {
                    operationCategory = 'Non classé';
                }

                if (operationCategory in categoryMap) {
                    var currentcategory = categoryMap[operationCategory];
                    currentcategory.amount += operation.amount;
                } else {
                    categoryMap[operationCategory] = ({name: operationCategory, amount: operation.amount})
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


                operationCategory = operation.category;
                if (operationCategory === null) {
                    operationCategory = 'Non classé';
                }

                var operationSubCategory = operation.subcategory;
                if (operation.subcategory == null) {
                    operationSubCategory = 'Non classé';
                }

                var operationDescription = operationCategory + operationSubCategory;

                if (operationDescription in detailedCategoryMap) {
                    var currentsubcategory = detailedCategoryMap[operationDescription];
                    currentsubcategory.amount += operation.amount;
                } else {
                    detailedCategoryMap[operationDescription] = ({
                        categoryName: operationCategory,
                        name: operationSubCategory,
                        amount: operation.amount
                    })
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