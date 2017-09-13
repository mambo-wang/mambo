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
            templateUrl: 'common/app/app.html',
            controller:'AppCtrl'
        })
        .state('app.welcome', {
            url: '/app/welcome',
            templateUrl: '../js/dashboard/welcome.html',
            controller:'WelcomeController'
        })
        .state('app.userList', {
            url: '/userList',
            templateUrl: 'user/user.html',
            controller:'UserController'
    }) .state('app.user-edit', {
            url: '/addUser/:editType/:userId',
            templateUrl: 'user/user-edit.html',
            controller:'UserController'
    });
    $urlRouterProvider.otherwise('/app/welcome');
}]);