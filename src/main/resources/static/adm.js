angular.module('market', []).controller('indexController', function ($scope, $http) {

    $scope.loadUsersList = function () {
        $http.get('http://localhost:8888/api/v1/users', {
            headers: {
                'Authorization': 'Bearer ' + (JSON.parse(localStorage.getItem("jwt")).data.token)
            }
        }).then(function (response) {
            $scope.usersList = response.data;

        });
    }

    $scope.dellUser = function (userId) {
        $http.delete('http://localhost:8888/api/v1/users/' + userId ,{
        headers: {
            'Authorization':'Bearer ' + (JSON.parse(localStorage.getItem("jwt")).data.token)
        }}).then(function (response) {
        $scope.loadUsersList();
    });
}

// console.log(JSON.parse(localStorage.getItem("jwt")).data);
$scope.loadUsersList();

})
;