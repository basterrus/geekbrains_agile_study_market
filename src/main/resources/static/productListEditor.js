angular.module('market', []).controller('indexController', function ($scope, $http) {

    $scope.loadProducts = function () {
        $http.get('http://localhost:8888/api/v1/products').then(function (response) {
            $scope.productsList = response.data;
        });
    }

    $scope.addProductToList = function () {
        $http.post('http://localhost:8888/api/v1/products/', $scope.newProduct).then(function (response) {
            console.log($scope.newProduct);
            $scope.loadProducts();
        });
    }

    $scope.deleteProductById = function (productId) {
        $http.get('http://localhost:8888/api/v1/products/delete/' + productId).then(function (response) {
            $scope.loadProducts();
        });
    }

    $scope.createCart = function () {
        $http.get('http://localhost:8888/api/v1/cart/generate_uuid').then(function (response) {
            $scope.cart_uuid = response.data;
        })
    }

    $scope.loadProducts();

});