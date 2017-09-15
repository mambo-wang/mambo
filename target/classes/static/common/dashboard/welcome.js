
/*//加(function(){})(); 才會自動執行,否则启动会加载不上这个controller*/
(function () {
    angular.module("app")
        .controller("BlogController", ["$scope", function ($scope) {
            $scope.ListThings = ["robot", "cat", "icecream"];
        }])
        .controller("WelcomeController", ['$rootScope', '$scope', '$http', '$resource', function ($rootScope, $scope, $http, $resource) {

            console.error("登录成功了");
            $scope.loginUser = JSON.parse(sessionStorage.loginUser);

        }])
        .controller('CourseController', ['$scope', '$timeout', '$resource', function ($scope, $timeout, $resource) {

            $scope.courseList = [];
            var course1 = {name: "数学"};
            var course2 = {name: "english"};
            var course3 = {name: "chinese"};
            $scope.courseList.push(course1);
            $scope.courseList.push(course2);
            $scope.courseList.push(course3);

            console.info($scope.courseList);
            $scope.selected = {};
        }]);
})();


