/**
 * Created by dell on 2016/5/4.
 */
//(function (window, document, undefined) {
'use strict';
var app = angular.module('myApp', ['ngResource']);

app.controller('FormCtrl', ['$scope','$timeout','$resource', function ($scope,$timeout,$resource) {
    $scope.bool = true;
    var timeout;
    $scope.$watch('name', function (newVal, oldVal) {
        if(newVal){
            if(timeout) $timeout.cancel();
            timeout = $timeout(function () {
                if (newVal == oldVal) {
                    console.info("初始化");
                } else {
                    console.info("数据模型变化");
                }
            }, 2000);
        }

    })

    $scope.num = "123";

    console.info(typeof  $scope.num);

    $scope.num = + $scope.num;

    console.info($scope.num);

    $resource("/course").query(function(courses){
        $scope.courseList = courses;
        console.info(courses);
    });



}]);
//})();