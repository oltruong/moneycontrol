'use strict';

moneyControlApp.controller('CellEditController', ['$scope', 'Operation', 'Rule',
    function CellEditController($scope, Operation, Rule) {

        $scope.editMode = false;

        $scope.edit = function () {
            if ($scope.object.nameCondition != undefined) {
                Rule.update($scope.object);
            } else {
                Operation.update($scope.object);
            }
            $scope.editMode = false;
        }

    }]);