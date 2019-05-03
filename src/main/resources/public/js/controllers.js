angular.module('app.controllers', []).controller('StockListController', function($scope, $state, popupService, $window, Stock) {
  $scope.stocks = Stock.query(); //fetch all stocks. Issues a GET to /api/vi/stocks
}).controller('StockViewController', function($scope, $stateParams, Stock) {
  $scope.stock = Stock.get({ id: $stateParams.id }); //Get a single stock.Issues a GET to /api/stock/:id
}).controller('StockCreateController', function($scope, $state, $stateParams, Stock) {
  $scope.stock = new Stock();  //create new stock instance. Properties will be set via ng-model on UI

  $scope.addStock = function() { //create a new stock. Issues a POST to /api/stocks
    $scope.stock.$save(function() {
      $state.go('stocks'); // on success go back to the list i.e. stocks state.
    });
  };
}).controller('StockEditController', function($scope, $state, $stateParams, Stock) {
  $scope.updateStock= function() { //Update the edited shipwreck. Issues a PUT to /api/v1/shipwrecks/:id
    $scope.stock.$update(function() {
      $state.go('stocks'); // on success go back to the list i.e. stocks state.
    });
  };

  $scope.loadStock = function() { //Issues a GET request to /api/stocks/:id to get a stock to update
    $scope.stock = Stock.get({ id: $stateParams.id });
  };

  $scope.loadStock(); // Load a stock which can be edited on UI
}).controller('StockDeleteController', function($scope, $state, $stateParams, Stock) {
    $scope.deleteStock= function() { //Update the edited shipwreck. Issues a PUT to /api/v1/shipwrecks/:id
      $scope.stock.$deleteStock(function() {
        $state.go('stocks'); // on success go back to the list i.e. stocks state.
      });
  };

  $scope.loadStock = function() { //Issues a GET request to /api/stocks/:id to get a stock to update
    $scope.stock = Stock.get({ id: $stateParams.id });
  };

  $scope.loadStock(); // Load a stock which can be edited on UI
});
