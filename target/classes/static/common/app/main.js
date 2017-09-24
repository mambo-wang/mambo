(function () {
    'use strict';

    function AppCtrl($scope, $http, UserService) {

        $scope.loginUser = JSON.parse(sessionStorage.loginUser);
    }

    angular.module('app').controller('AppCtrl', ['$scope', '$http', 'UserService', AppCtrl]);
})();