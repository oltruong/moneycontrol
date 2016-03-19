moneyControlApp.factory('Operation', ['$resource', function ($resource) {
    return $resource('/rest/operations/:id', {id: '@id'},
        {'update': {method: 'PUT'}});
}]);

moneyControlApp.factory('Rule', ['$resource', function ($resource) {
    return $resource('/rest/rules/:id', {id: '@id'},
        {'update': {method: 'PUT'}});
}]);