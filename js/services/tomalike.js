module.exports = function (app) {
    app.factory('TomALikeService', ['$http', function ($http) {
        let tomalikes = [];

        return {
            getTomALikes: function () {
                $http({
                    url: '/tomalikes',
                    method: 'get'
                }).then(function (results) {
                    angular.copy(results.data, tomalikes)
                });

                return tomalikes;
            },
        };
    }]);
};