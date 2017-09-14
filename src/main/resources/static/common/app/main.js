(function () {
    'use strict';

    function AppCtrl($scope, $http) {

        $scope.loginUser = JSON.parse(sessionStorage.loginUser);
    }

    angular.module('app').controller('AppCtrl', ['$scope', '$http', AppCtrl]);
})();