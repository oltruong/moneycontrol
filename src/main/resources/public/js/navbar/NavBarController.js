'use strict';

moneyControlApp.controller('NavBarController', ['$scope',
    function NavBarController($scope) {

        $scope.currentYear = moment().format('YYYY');
        $scope.currentMonth = moment().format('M');
    }]);