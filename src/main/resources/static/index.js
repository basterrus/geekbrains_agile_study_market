angular.module('market', []).controller('indexController', function ($scope, $http) {

    $scope.createCart = function (){
        $http.get('http://localhost:8888/api/v1/cart/generate_uuid').then(function (response){
            $scope.cart_uuid = response.data;
        })
    }

    $scope.createCart();

});