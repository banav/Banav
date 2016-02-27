'use strict';

angular.module('vendasApp.resources').factory('PagSeguroResources', ['$resource', function ($resource) {
    return $resource(app.rootContext + 'ws/pagseguro');
}]);
