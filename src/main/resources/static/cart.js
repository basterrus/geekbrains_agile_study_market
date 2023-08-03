angular.module('market', []).controller('indexController', function ($scope, $http) {

    $scope.loadCart = function () {
        $http.get('http://localhost:8888/api/v1/cart/' + (JSON.parse(localStorage.getItem("uuid")).value)).then(function (response) {
            $scope.cart = response.data;
        });
    }

    $scope.cleanCart = function () {
        $http.get('http://localhost:8888/api/v1/cart/' + (JSON.parse(localStorage.getItem("uuid")).value) + '/clear').then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.decrementProductIdCart = function (productId) {
        $http.get('http://localhost:8888/api/v1/cart/' + (JSON.parse(localStorage.getItem("uuid")).value) + '/delete/' + productId).then(function (response){
            $scope.loadCart();
        });
    }

    $scope.incrementProductIdCart = function (productId) {
        $http.get('http://localhost:8888/api/v1/cart/' + (JSON.parse(localStorage.getItem("uuid")).value) + '/add/' + productId).then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.deleteProductInCart = function (productId) {
        $http.get('http://localhost:8888/api/v1/cart/' + (JSON.parse(localStorage.getItem("uuid")).value) + '/deleteQuantity/' + productId).then(function (response) {
            $scope.loadCart();
        });
    }
    // console.log(JSON.parse(localStorage.getItem("uuid")));
    $scope.loadCart();
});