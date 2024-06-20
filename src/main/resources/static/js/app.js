angular.module('SpringSmoketest', ['info', 'ngRoute', 'ui.directives']).
    config(function ($locationProvider, $routeProvider) {
        // $locationProvider.html5Mode(true);
        
        $routeProvider.when('/', {
            controller: 'InfoController',
            templateUrl: 'templates/info.html'
        });
        $routeProvider.otherwise({
            controller: 'InfoController',
            templateUrl: 'templates/info.html'
        });
    }
);
