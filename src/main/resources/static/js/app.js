    angular.module("app", [
        "ui.router",
        "controllers",
        "ngResource"
    ]).config(["$stateProvider","$urlRouterProvider", function($stateProvider,$urlRouterProvider) {
        $stateProvider
            .state('register', {
                url: "/register",
                templateUrl: "views/login/register.html",
                controller:"RegisterController"
            })
            .state('login', {
                url: "/login",
                templateUrl: "views/login/login.html",
                controller:"LoginController"
            })
            .state('practice', {
                url: "/practice",
                templateUrl: "views/ownh5css3.html",
                controller: "PracticeController"
            })
            .state('blog', {
                url: "/blog",
                templateUrl: "views/blog.html",
                controller:"BlogController"
            })
            .state('h5-css3', {
                url: "/h5-css3",
                templateUrl: "views/h5-css3/h5-css3-1.html",
                controller:"H5CSS3Controller"
            })
            .state('app', {
                url: "/app",
                templateUrl: "views/app.html"
            })
            .state('app.welcome', {
                url: "/welcome",
                templateUrl: "views/welcome.html",
                controller:"WelcomeController"
            })
            .state('app.courseList', {
                url: "/courseList",
                templateUrl: "views/course/course-list.html",
                controller:"CourseController"
            });
        $urlRouterProvider.otherwise("/login");
    }])