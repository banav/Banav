angular.module('vendasApp.resources').factory('CidadesResources', ['$resource', function ($resource) {
    return $resource(app.rootContext + 'ws/cidades/:id');
}]);
