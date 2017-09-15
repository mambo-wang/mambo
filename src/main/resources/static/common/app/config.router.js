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
        .state('blog', {
            url: '/blog',
            templateUrl: 'common/blog/blog.html',
            controller:'BlogController'
        })
        .state('app', {
            url: '/app',
            templateUrl: 'common/app/app.html',
            controller:'AppCtrl'
        })
        .state('app.welcome', {
            url: '/welcome',
            templateUrl: 'common/dashboard/welcome.html',
            controller:'WelcomeController'
        })
        .state('app.userList', {
            url: '/userList',
            templateUrl: 'common/user/user.html',
            controller:'UserController',
            controllerAs:'userVm'
    }) .state('app.user-edit', {
            url: '/editUser/:editType/:userId',
            templateUrl: 'common/user/user-edit.html',
            controller:'UserController',
            controllerAs:'userVm'
    });
    $urlRouterProvider.otherwise('/app/welcome');//这里是.app和.app.welcome的url的拼接
}]);