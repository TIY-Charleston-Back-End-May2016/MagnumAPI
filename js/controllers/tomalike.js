
module.exports = function (app) {
    app.controller('TomALikeController', ['$scope', 'TomALikeService', function ($scope, TomALikeService) {
        $scope.tomalikes = TomALikeService.getTomALikes();
    }]);
};