angular.module('market', []).controller('indexController', function ($scope, $http) {

    $scope.loadCart = function () {
        $http.get('http://localhost:8888/api/v1/cart/' + (JSON.parse(localStorage.getItem("uuid")).value),{
            headers: {
                'Authorization': 'Bearer ' + (JSON.parse(localStorage.getItem("jwt")).data.token)
            }}).then(function (response) {
            $scope.cart = response.data;
        });
    }

    $scope.cleanCart = function () {
        $http.get('http://localhost:8888/api/v1/cart/' + (JSON.parse(localStorage.getItem("uuid")).value) + '/clear',{
                headers: {
                    'Authorization': 'Bearer ' + (JSON.parse(localStorage.getItem("jwt")).data.token)
                }}).then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.decrementProductIdCart = function (productId) {
        $http.get('http://localhost:8888/api/v1/cart/' + (JSON.parse(localStorage.getItem("uuid")).value) + '/delete/' + productId, {
                headers: {
                    'Authorization': 'Bearer ' + (JSON.parse(localStorage.getItem("jwt")).data.token)
                }}).then(function (response){
            $scope.loadCart();
        });
    }

    $scope.incrementProductIdCart = function (productId) {
        $http.get('http://localhost:8888/api/v1/cart/' + (JSON.parse(localStorage.getItem("uuid")).value) + '/add/' + productId, {
                headers: {
                    'Authorization': 'Bearer ' + (JSON.parse(localStorage.getItem("jwt")).data.token)
                }}).then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.deleteProductInCart = function (productId) {
        $http.get('http://localhost:8888/api/v1/cart/' + (JSON.parse(localStorage.getItem("uuid")).value) + '/deleteQuantity/' + productId, {
                headers: {
                    'Authorization': 'Bearer ' + (JSON.parse(localStorage.getItem("jwt")).data.token)
                }}).then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.createOrder = function (productId) {
        $http.post('http://localhost:8888/api/v1/order/' + (JSON.parse(localStorage.getItem("uuid")).value) + '/createOrder',
            {},{
            headers: {
                'Authorization': 'Bearer ' + (JSON.parse(localStorage.getItem("jwt")).data.token)
            }}).then(function (response) {
            $scope.productInCart = response.data;
            $scope.loadCart();
        });
    }

    // console.log(JSON.parse(localStorage.getItem("uuid")));
    $scope.loadCart();
});