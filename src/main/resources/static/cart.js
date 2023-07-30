angular.module('market', []).controller('indexController', function ($scope, $http) {

    $scope.loadCart = function () {
        $http.get('http://localhost:8888/api/v1/cart/' + '$scope.cart_uuid').then(function (response) {
            $scope.cart = response.data;
        });
    }

    $scope.cleanCart = function () {
        $http.get('http://localhost:8888/api/v1/cart/' + '$scope.cart_uuid' + '/clear').then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.decrementProductIdCart = function (productId) {
        $http.get('http://localhost:8888/api/v1/cart/' + '$scope.cart_uuid' + '/delete/' + productId).then(function (response){
            $scope.loadCart();
        });
    }

    $scope.incrementProductIdCart = function (productId) {
        $http.get('http://localhost:8888/api/v1/cart/' + '$scope.cart_uuid' + '/add/' + productId).then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.deleteProductInCart = function (productId) {
        $http.get('http://localhost:8888/api/v1/cart/' + '$scope.cart_uuid' + '/deleteQuantity/' + productId).then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.loadCart();

});