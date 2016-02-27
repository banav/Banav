'use strict';

var PassageirosEvents = Object.freeze({
  'CARREGAR_PASSAGENS' : 'PassageirosCtrl.onCarregarPassagens'
});

angular.module('vendasApp').controller('PassageirosCtrl', ['$scope', '$rootScope', '$injector', '$timeout', '$location', function ($scope, $rootScope, $injector, $timeout, $location) {

  $rootScope.$on(PassageirosEvents.CARREGAR_PASSAGENS, function(event, args) {
    $scope.ida = args.ida;
    $scope.volta = args.volta;
  });

  $scope.continuar = function() {
    var listaPassagens = [];
    var adicionarSomentePassagensSelecionadas = function(viagens) {
      angular.forEach(viagens, function(viagem, key) {
        angular.forEach(viagem.valorClassesDTO, function(valorClasse, key) {
          if(valorClasse.quantidade && valorClasse.quantidade > 0) {
            listaPassagens.push(angular.copy(valorClasse));
          }
        });
      });
    };

    if(angular.isDefined($scope.ida)) adicionarSomentePassagensSelecionadas($scope.ida.resultadoViagensDTO);
    if(angular.isDefined($scope.volta)) adicionarSomentePassagensSelecionadas($scope.volta.resultadoViagensDTO);

    $location.path('/pagamento')
    $timeout(function() {
      $rootScope.$emit(PagamentoCtrl.CARREGAR_PASSAGEIROS, {passagens : listaPassagens});
      $timeout.cancel(this);
    }, 300);
  };
}]);
