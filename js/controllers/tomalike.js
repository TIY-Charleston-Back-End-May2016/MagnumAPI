
module.exports = function (app) {
    app.controller('TomALikeController', ['$scope', '$http', 'TomALikeService', function ($scope, $http, TomALikeService) {
        $scope.tomalikes = TomALikeService.getTomALikes();

        $scope.fav = function (tom, looksLike) {
            $http({
                url: '/favs',
                method: 'post',
                data: {
                    tomId: tom.id,
                    looksLikeTom: looksLike,
                }
            });
        };
    }]);
};