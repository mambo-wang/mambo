/**
 * 初始登录页面的service
 */
(function () {
    'use strict';

    angular.module('loginApp').factory('LoginService', ['$resource', function ($resource) {
        return $resource('/mambo/:url', null, {
            login: {
                method:'POST',
                params:{url:'login'}
            },
            isAuthenticated:{
                method:'GET',
                params:{url:'isAuthenticated'}
            }
        });
    }]);
})();