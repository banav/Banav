angular.module('vendasApp.resources').factory('ViagemValorClassesResources', ['$resource', function ($resource) {
    return $resource(app.rootContext + 'ws/viagemvalorclasse/:id', {id : '@id'}, {
      'query' : { method : 'GET', isArray : false}
    });
}]);
