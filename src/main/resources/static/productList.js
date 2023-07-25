angular.module('market', []).controller('indexController', function ($scope, $http) {

    $scope.loadProducts = function () {
        $http.get('http://localhost:8888/api/v1/products').then(function (response) {
            $scope.productsList = response.data;
        });
    }

    $scope.deleteProductById = function (productId) {
        $http.delete('http://localhost:8080/market/api/v1/products/' + productId).then(function (response) {
            $scope.loadProducts();
        });
    }

    $scope.createCart = function (){
        $http.get('http://localhost:8888/api/v1/cart/generate_uuid').then(function (response){
            $scope.cart_uuid = response.data;
        })
    }
    $scope.addToCart = function (productId) {
        $http.get('http://localhost:8888/api/v1/cart/5c14f3ec-9eb0-43ca-a4ff-25c549c00836/add/' + productId).then(function (response) {
            $scope.productInCart = response.data;
        });
    }

    $scope.loadProducts();

});