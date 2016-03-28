'use strict';

moneyControlApp.controller('DateBarController', ['$scope', '$routeParams', '$location',
    function DateBarController($scope, $routeParams, $location) {


        $scope.display=true;
        if ($location.path().indexOf('unclassified') > -1) {
            $routeParams.year = undefined;
            $routeParams.month = undefined;
            $scope.display=false;
        }


        var count = 0;
        $scope.months = [];
        while (count < 12) $scope.months.push(count++);
        $scope.years = ["2014","2015","2016"];

        $scope.year = $routeParams.year;
        $scope.currentMonth =  $routeParams.month;

        $scope.checkYear = function (year) {
            return year=== $scope.year? "active":"";
        };

        $scope.checkMonth = function (month) {
            return month+1== $scope.currentMonth? "active":"";
        };

        $scope.displayMonth = function (month) {
          return moment().locale("fr").month(month).format('MMM');
        }


    }]);