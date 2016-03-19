'use strict';

moneyControlApp.controller('FileUploadController', ['$scope',
    function FileUploadController($scope) {

        $scope.sendFile = function (value){
            console.log("sendfile");
        }
    }]);