'use strict';

moneyControlApp.filter('operationFilter', function () {
    return function ($filter, operations, text) {
        if (text !== undefined && text.length >= 3) {
            return $filter('filter')(operations, text);
        } else {
            return operations;
        }
    }
});