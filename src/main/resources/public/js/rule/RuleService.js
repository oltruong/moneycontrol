moneyControlApp.factory('Rule', ['$resource', function ($resource) {
    return $resource('/rest/rules/:id', {id: '@id'},
        {'update': {method: 'PUT'}});
}]);