let app = angular.module('TomALikeApp', ['ngRoute']);

require('./services/tomalike')(app);
require('./controllers/login')(app);
require('./controllers/tomalike')(app);

app.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
        .when('/', {
            controller: 'LoginController',
            templateUrl: 'templates/login.html',
        })
        .when('/tomalikes', {
            controller: 'TomALikeController',
            templateUrl: 'templates/tomalike.html',
        })
        .when('/upload', {
            templateUrl: 'templates/upload.html',
        });
}])