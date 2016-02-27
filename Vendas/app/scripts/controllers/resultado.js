'use strict';

var ResultadoEvents = Object.freeze({
  'CARREGAR_RESULTADOS' : 'ResultadoCtrl.onCarregarResultados'
});

angular.module('vendasApp').controller('ResultadoCtrl', ['$scope', '$rootScope', '$injector', '$timeout', '$location', function ($scope, $rootScope, $injector, $timeout, $location) {

  $rootScope.$on(ResultadoEvents.CARREGAR_RESULTADOS, function(event, args) {
    var viagemValorClassesResources = $injector.get('ViagemValorClassesResources');

    viagemValorClassesResources.query({origem : args.origem.id, destino : args.destino.id, dia : args.dataPartida},
      function(success) {
        $scope.ida = success;
      },
      function(error) {
        alert("Erro interno do servidor. Por favor, verifique sua conexão com a internet.");
      }
    );

    viagemValorClassesResources.query({origem : args.destino.id, destino : args.origem.id, dia : args.dataRetorno},
      function(success) {
        $scope.volta = success;
      },
      function(error) {
        alert("Erro interno do servidor. Por favor, verifique sua conexão com a internet.");
      }
    );
  });

  $scope.comprar = function() {
    var adicionarPassageiros = function(viagens) {
      angular.forEach(viagens, function(viagem, key) {
        angular.forEach(viagem.valorClassesDTO, function(valorClasse, key) {
          if(valorClasse.quantidade && valorClasse.quantidade > 0) {
            valorClasse.passageiros = [];
            for (var i = 0; i < valorClasse.quantidade; i++) {
              valorClasse.passageiros.push({'id' : null, 'nome' : null, 'cpf' : null});
            }
          }
        });
      });
    };

    if(angular.isDefined($scope.ida)) adicionarPassageiros($scope.ida.resultadoViagensDTO);
    if(angular.isDefined($scope.volta)) adicionarPassageiros($scope.volta.resultadoViagensDTO);

    $location.path('/passageiros');
    var timer = $timeout(function() {
      $rootScope.$emit(PassageirosEvents.CARREGAR_PASSAGENS, {
        ida : angular.copy($scope.ida),
        volta : angular.copy($scope.volta)
      });

      $timeout.cancel(timer);
    }, 300);
  };

  $scope.atualizarTotal = function() {
    $scope.total = 0;

    var calcularTotal = function(viagens) {
      angular.forEach(viagens, function(viagem, key) {
        angular.forEach(viagem.valorClassesDTO, function(valorClasse, key) {
          if(valorClasse.quantidade && valorClasse.quantidade > 0) {
            $scope.total += (valorClasse.quantidade * valorClasse.valor);
          }
        });
      });
    };

    if(angular.isDefined($scope.ida)) calcularTotal($scope.ida.resultadoViagensDTO);
    if(angular.isDefined($scope.volta)) calcularTotal($scope.volta.resultadoViagensDTO);
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
}]);
