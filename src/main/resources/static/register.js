angular.module('market', []).controller('indexController', function ($scope, $http) {

    $scope.addUser = function () {
        $http.post('http://localhost:8888/api/v1/auth/register', $scope.newUser).then(function (response) {
            $scope.userToken = response.data;
            alert("Пользователь " + $scope.newUser.username + " успешно зарегистрирован.")
        });
    }
});