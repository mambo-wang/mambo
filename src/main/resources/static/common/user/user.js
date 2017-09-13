(function () {

    angular.module('app')
        .controller('UserController', ['$rootScope', '$scope', '$state', '$resource', 'UserService', '$stateParams',
            function ($rootScope, $scope, $state, $resource, UserService, $stateParams) {

            var isModify = $stateParams.editType == 2;

            activate();

            function activate() {
                if(isModify){
                    $scope.header = "修改用户";
                    $scope.user = UserService.get({id:$stateParams.userId});
                } else {
                    $scope.header = "增加用户";
                }
            }

                $scope.header="";
                $scope.users = [];
                $scope.user = {};
                queryAll();

                function queryAll() {
                    console.error("获取用户列表");
                    $scope.users = UserService.query({}, function (data) {
                    });
                }

                $scope.save = save;

                function save() {

                    if(isModify){
                        UserService.update({}, $scope.user, function () {
                            $state.go('app.userList');
                        })
                    }else {
                        UserService.save({}, $scope.user, function () {
                            $state.go('app.userList');
                        });
                    }
                }
            }]);

})();