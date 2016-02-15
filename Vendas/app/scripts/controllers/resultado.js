'use strict';

var ResultadoEvents = Object.freeze({
  'CARREGAR_RESULTADOS' : 'ResultadoCtrl.onCarregarResultados'
});

angular.module('vendasApp').controller('ResultadoCtrl', ['$scope', '$rootScope', '$injector', '$timeout', '$location', function ($scope, $rootScope, $injector, $timeout, $location) {

  $rootScope.$on(ResultadoEvents.CARREGAR_RESULTADOS, function(event, args) {
    var viagemValorClassesResources = $injector.get('ViagemValorClassesResources');
    viagemValorClassesResources.query({origem : args.origem.id, destino : args.destino.id, dia : args.dataPartida},
      function(success) {
        console.log(success);
      },
      function(error) {
        alert("Erro interno do servidor. Por favor, verifique sua conexão com a internet.");
      }
    );
  });
}]);
