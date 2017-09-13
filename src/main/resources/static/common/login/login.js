/**
 * 初始登录页面的controller
 */
(function () {
    'use strict';

    angular.module('loginApp')
        .controller('LoginController', ['LoginService','$state',
            function (LoginService, $state) {

                var self = this;
                self.user = {};

                self.login = function () {
                    var sendUser = self.user;
                    LoginService.login(sendUser, function (response) {

                        if('SUCCESS' === response.result){
                            console.info(response);
                            sessionStorage.loginUser = JSON.stringify(response.data);
                            location.href = "home.html";
                            console.info(sessionStorage.loginUser);
                        }else {
                            self.errorMessage = response.data;
                        }
                    })
                }
            }]);
})();