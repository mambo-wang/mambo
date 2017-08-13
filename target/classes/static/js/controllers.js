angular.module("controllers", [])
    .controller("RegisterController", ['$scope','$state', function ($scope,$state) {
        $scope.user = {};
        $scope.register = function(){
            $state.go('login');
        }

    }])
    .controller('LoginController', ['$scope','$state', function ($scope,$state) {

        $scope.user = {};
        $scope.cannot = false;
        $scope.login = function(){
            if($scope.user.username == "wang" && $scope.user.password == "1992"){
                $state.go('app.welcome');
            }else{
                $scope.cannot = true;
            }
        }

    }])
    .controller("PracticeController", ["$scope", function ($scope) {
        $scope.listItems = ["能力", "智慧", "亲啦"];
    }])
    .controller("BlogController", ["$scope", function ($scope) {
        $scope.ListThings = ["robot", "cat", "icecream"];
    }])
    .controller("WelcomeController", ['$scope','$http','$resource', function ($scope,$http,$resource) {
        $scope.loginUser = $resource('/wang/users/1').get();

    }])
    .controller("H5CSS3Controller", ["$scope", function ($scope) {
        $scope.loginUser = "wangbao";

    }])
  .controller('CourseController', ['$scope', '$timeout', '$resource', function ($scope, $timeout, $resource) {

    //$resource("/course").query(function (courses) {
    //    $scope.courseList = courses;
    //    console.info(courses);
    //});
      $scope.courseList = [];
      var course1 = {name:"数学"};
      var course2 = {name:"english"};
      var course3 = {name:"chinese"};
      $scope.courseList.push(course1);
      $scope.courseList.push(course2);
      $scope.courseList.push(course3);

      console.info($scope.courseList);
      $scope.selected = {};

      


}]);
