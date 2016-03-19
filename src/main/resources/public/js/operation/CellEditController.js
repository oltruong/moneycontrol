'use strict';

moneyControlApp.controller('CellEditController', ['$scope', 'Operation',
    function CellEditController($scope, Operation) {

        $scope.editMode = false;

        $scope.edit = function () {
            console.log("Edit value" + $scope.value + "cell operation id" + $scope.object.id);
                $scope.editMode = false;
        }

    }]);