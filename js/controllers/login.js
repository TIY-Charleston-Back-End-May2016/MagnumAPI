
module.exports = function (app) {
    app.controller('LoginController', ['$scope', '$http', '$location', function ($scope, $http, $location) {
        $scope.name = '';

        $scope.login = function () {
            console.log(`Logging ${$scope.name} in as we speak`);

            $http({
                url: '/users',
                method: 'post',
                data: {
                    name: $scope.name,
                    password: 'password123',
                },
            }).then(function () {
                $location.path('/tomalikes');
            }).catch(function () {
                console.error('INTRUDER');
            });
        };
    }]);
}