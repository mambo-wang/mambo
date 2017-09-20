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

                        console.info(response);
                        if('SUCCESS' === response.result){
                            sessionStorage.loginUser = JSON.stringify(response.data);
                            location.href = "home.html";
                            console.error(sessionStorage.loginUser);
                        }else {
                            self.errorMessage = response.data;
                            console.error(self.errorMessage);
                        }
                    })
                }
            }]);
})();