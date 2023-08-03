angular.module('market', []).controller('indexController', function ($scope, $http) {

    $scope.login = function () {
        $http.post('http://localhost:8888/api/v1/auth/login', $scope.user).then(function (response) {
            $scope.userToken = response;
            // console.log($scope.userToken);
            localStorage.setItem("jwt", JSON.stringify($scope.userToken));
        });
    }

});