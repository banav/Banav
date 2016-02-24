'use strict';

var ResultadoEvents = Object.freeze({
  'CARREGAR_RESULTADOS' : 'ResultadoCtrl.onCarregarResultados'
});

angular.module('vendasApp').controller('ResultadoCtrl', ['$scope', '$rootScope', '$injector', '$interval', '$location', function ($scope, $rootScope, $injector, $interval, $location) {

  $rootScope.$on(ResultadoEvents.CARREGAR_RESULTADOS, function(event, args) {
    var viagemValorClassesResources = $injector.get('ViagemValorClassesResources');

    viagemValorClassesResources.query({origem : args.origem, destino : args.destino, dia : args.dataPartida},
      function(success) {
        $scope.ida = success;
      },
      function(error) {
        alert("Erro interno do servidor. Por favor, verifique sua conexão com a internet.");
      }
    );

    viagemValorClassesResources.query({origem : args.destino, destino : args.origem, dia : args.dataRetorno},
      function(success) {
        $scope.volta = success;
      },
      function(error) {
        alert("Erro interno do servidor. Por favor, verifique sua conexão com a internet.");
      }
    );
  });

  $scope.atualizarTotal = function() {
    $scope.total = 0;

    angular.forEach($scope.ida.resultadoViagensDTO, function(viagem, key) {
      angular.forEach(viagem.valorClassesDTO, function(valorClasse, key) {
        if(valorClasse.quantidade && valorClasse.quantidade > 0) {
          $scope.total += (valorClasse.quantidade * valorClasse.valor);
        }
      });
    });
  };

  $scope.subtrair = function(valorClasse) {
    if(!valorClasse.quantidade) {
      valorClasse.quantidade = 0;
    } else if(valorClasse.quantidade - 1 >= 0) {
      valorClasse.quantidade--;
    }

    valorClasse.subtotal = valorClasse.valor * valorClasse.quantidade;
    $scope.atualizarTotal();
  };

  $scope.somar = function(valorClasse) {
    if(!valorClasse.quantidade) {
      valorClasse.quantidade = 1;
    } else {
      valorClasse.quantidade++;
    }

    valorClasse.subtotal = valorClasse.valor * valorClasse.quantidade;
    $scope.atualizarTotal();
  };

  $rootScope.$emit(ResultadoEvents.CARREGAR_RESULTADOS, {
    origem : 1,
    destino : 2,
    dataPartida : '02/16/2016',
    dataRetorno : '02/16/2016'
  });
}]);
