angular.module('market', []).controller('indexController', function ($scope, $http) {

    $scope.loadProducts = function () {
        $http.get('http://localhost:8888/api/v1/products').then(function (response) {
            $scope.productsList = response.data;
        });
    }

    $scope.addToCart = function (productId) {
        $http.get('http://localhost:8888/api/v1/cart/' + (JSON.parse(localStorage.getItem("uuid")).value) + '/add/' + productId).then(function (response) {
            $scope.productInCart = response.data;
        });
    }

    $scope.loadProducts();

});