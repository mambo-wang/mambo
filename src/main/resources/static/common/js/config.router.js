angular.module('app')
.run(
    ['$rootScope', '$state', '$stateParams',
        function ($rootScope, $state, $stateParams) {

            $rootScope.$state = $state;
            $rootScope.$stateParams = $stateParams;

        }
    ]
).config(['$stateProvider','$urlRouterProvider', function($stateProvider,$urlRouterProvider) {
    $stateProvider
        .state('register', {
            url: '/register',
            templateUrl: 'login/register.html',
            controller:'RegisterController'
        })
        .state('login', {
            url: '/login',
            templateUrl: 'login/login.html',
            controller:'LoginController'
        })
        .state('practice', {
            url: '/practice',
            templateUrl: 'views/ownh5css3.html',
            controller: 'PracticeController'
        })
        .state('blog', {
            url: '/blog',
            templateUrl: 'blog/blog.html',
            controller:'BlogController'
        })
        .state('app', {
            url: '/app',
            templateUrl: 'views/app.html'
        })
        .state('app.welcome', {
            url: '/welcome',
            templateUrl: 'dashboard/welcome.html',
            controller:'WelcomeController'
        });
    $urlRouterProvider.otherwise('/login');
}]);