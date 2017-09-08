(function () {

    angular.module('app')
        .controller('LoginController', ['$rootScope', '$scope', '$state', '$resource',
        function ($rootScope, $scope, $state, $resource) {

            $scope.user = {};
            $scope.pass = true;

            $scope.login = function () {

                console.error("进入登录方法");
                var loginUser = $resource('/mambo/login/'+$scope.user.loginName + "/" + $scope.user.password).get();

                console.info(loginUser.data);

                if(loginUser.data!==null){
                    console.error("登陆成功");
                    $rootScope.loginUser = loginUser.data;
                    $state.go("app.welcome");
                }else {
                    $scope.pass = false;
                    console.error("登陆失败 ");
                }

            }

        }]);

    angular.module('app')
        .controller('RegisterController', ['$rootScope', '$scope', '$state', '$resource',
            function ($rootScope, $scope, $state, $resource) {

                $scope.user = {};

                $scope.register = function () {

                    $resource('/mambo/users').save({},$scope.user,function () {
                        $state.go("login");
                    })

                }

            }]);


})();