(function () {

    angular.module('app')
        .controller('UserController', ['$rootScope', '$scope', '$state', '$resource', 'UserService', '$stateParams',
            function ($rootScope, $scope, $state, $resource, UserService, $stateParams) {

                var isModify = $stateParams.editType === 2;
                $scope.user = {};
                activate();

                function activate() {
                    if (isModify) {
                        $scope.header = "修改用户";
                        $scope.user = UserService.get({id: $stateParams.userId});
                    } else {
                        $scope.header = "增加用户";
                    }
                }

                $scope.header = "";
                $scope.users = [];
                queryAll();

                function queryAll() {
                    console.error("获取用户列表");
                    $scope.users = UserService.query({}, function (data) {
                    });
                }

                $scope.save = save;

                function save() {

                    if (isModify) {
                        console.error("是修改");
                        UserService.update({}, $scope.user, function () {
                            $state.go('app.userList');
                        })
                    } else {
                        console.error("是增加");
                        UserService.save({}, $scope.user, function () {
                            $state.go('app.userList');
                        });
                    }
                }
            }]);

})();