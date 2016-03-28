'use strict';

moneyControlApp.controller('FileUploadController', ['$scope', '$http', '$location',
    function FileUploadController($scope, $http, $location) {


        $scope.upload = function (data) {

            $http.post("/rest/bankfileupload", data).then(function successCallback(response) {
                $location.path('/operations/unclassified');
            }, function errorCallback(response) {
                console.log(response);
            });

        };
    }]);