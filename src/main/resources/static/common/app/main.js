(function () {
    'use strict';

    function AppCtrl($scope, $http) {
        var self = this;

        self.loginUser = JSON.parse(sessionStorage.loginUser);
    }

    angular.module('app').controller('AppCtrl', ['$scope', '$http', AppCtrl]);
})();