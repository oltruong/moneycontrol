moneyControlApp.factory('Operation', ['$resource', function ($resource) {
    return $resource('/rest/operations/period/2015/9/:id', {id: '@id'},
        {'update': {method: 'PUT'}});
}]);