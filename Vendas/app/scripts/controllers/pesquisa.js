'use strict';

angular.module('vendasApp').controller('PesquisaCtrl', ['$scope', '$rootScope', '$injector', '$timeout', '$location', function ($scope, $rootScope, $injector, $timeout, $location) {
  var swiper = new Swiper('.swiper-container', {
    pagination: '.swiper-pagination',
    nextButton: '.swiper-button-next',
    prevButton: '.swiper-button-prev',
    paginationClickable: true,
    spaceBetween: 30,
    centeredSlides: true,
    autoplay: 2500,
    autoplayDisableOnInteraction: false
  });

  $scope.cidades = [];
  $scope.tipoViagem = 'IV';
  $scope.dataPartida = new Date('02-16-2016');
  $scope.dataRetorno = new Date('02-16-2016');

  $scope.listarCidades = function() {
    var cidadesResources = $injector.get('CidadesResources');
    cidadesResources.query(null,
      function(success) {
        $scope.cidades = success;
      },
      function(error) {
        alert("Erro interno do servidor. Por favor, verifique sua conexão com a internet.");
      }
    );
  };

  $scope.pesquisar = function() {
    $location.path('/resultado');
    var timer = $timeout(function() {
      $rootScope.$emit(ResultadoEvents.CARREGAR_RESULTADOS, {
        origem : $scope.origem,
        destino : $scope.destino,
        dataPartida : moment($scope.dataPartida).format('MM/DD/YYYY'),
        dataRetorno : moment($scope.dataRetorno).format('MM/DD/YYYY')
      });

      $timeout.cancel(timer);
    }, 300);
  };

  $scope.listarCidades();
}]);
