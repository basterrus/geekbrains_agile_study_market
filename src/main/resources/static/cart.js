angular.module('market', []).controller('indexController', function ($scope, $http) {

    $scope.loadCart = function () {
        $http.get('http://localhost:8888/api/v1/cart/5c14f3ec-9eb0-43ca-a4ff-25c549c00836').then(function (response) {
            $scope.cart = response.data;
        });
    }

    $scope.cleanCart = function () {
        $http.get('http://localhost:8888/api/v1/cart/5c14f3ec-9eb0-43ca-a4ff-25c549c00836/clear').then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.decrementProductIdCart = function (productId) {
        $http.get('http://localhost:8888/api/v1/cart/5c14f3ec-9eb0-43ca-a4ff-25c549c00836/delete/' + productId).then(function (response){
            $scope.loadCart();
        });
    }

    $scope.incrementProductIdCart = function (productId) {
        $http.get('http://localhost:8888/api/v1/cart/5c14f3ec-9eb0-43ca-a4ff-25c549c00836/add/' + productId).then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.deleteProductInCart = function (productId) {
        $http.get('http://localhost:8888/api/v1/cart/5c14f3ec-9eb0-43ca-a4ff-25c549c00836/deleteQuantity/' + productId).then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.loadCart();

});