angular.module('market', []).controller('indexController', function ($scope, $http) {

    $scope.showOrders = function () {
        $http.get('http://localhost:8888/api/v1/order', {
                headers: {
                    'Authorization': 'Bearer ' + (JSON.parse(localStorage.getItem("jwt")).data.token)
                }}).then(function (response) {
            $scope.order = response.data;
        });
    }

    $scope.showOrders();
});