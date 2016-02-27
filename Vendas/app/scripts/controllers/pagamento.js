'use strict';

var PagamentoCtrl = Object.freeze({
  'CARREGAR_PASSAGEIROS' : 'PagamentoCtrl.onCarregarPassageiros'
});

angular.module('vendasApp').controller('PagamentoCtrl', ['$scope', '$rootScope', '$injector', '$interval', '$location', function ($scope, $rootScope, $injector, $interval, $location) {

  $rootScope.$on(PagamentoCtrl.CARREGAR_PASSAGEIROS, function(event, args) {
    $scope.passagens = args.passagens;
    $scope.total = 0;

    angular.forEach($scope.passagens, function(valorClasse, key) {
      $scope.total += (valorClasse.quantidade * valorClasse.valor);
    });
  });

  $scope.pagar = function() {
    $injector.get('PagSeguroResources').save($scope.passagens,
      function(success) {
        console.log(success)
      },
      function(error) {
        alert("Erro interno do servidor. Por favor, verifique sua conexão com a internet.");
      }
    );
  };
}]);
