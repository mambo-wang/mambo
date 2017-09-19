(function () {

    angular.module('app')
        .controller('UserController', ['$rootScope', '$state', 'UserService', '$stateParams',
            function ($rootScope,  $state, UserService, $stateParams) {

                var isModify = $stateParams.editType === 2;
                var userVm = this;
                userVm.user = {};
                activate();

                function activate() {
                    if (isModify) {
                        userVm.header = "修改用户";
                        userVm.user = UserService.get({id: $stateParams.userId});
                    } else {
                        userVm.header = "增加用户";
                    }
                }

                userVm.header = "";
                userVm.users = [];
                queryAll();

                function queryAll() {
                    console.error("获取用户列表");
                    userVm.users = UserService.query({}, function (data) {
                    });
                    console.info(userVm.users);
                }

                userVm.save = save;

                function save() {

                    if (isModify) {
                        console.error("是修改");
                        UserService.update({}, userVm.user, function () {
                            $state.go('app.userList');
                        })
                    } else {
                        console.error("是增加");
                        UserService.save({}, userVm.user, function () {
                            $state.go('app.userList');
                        });
                    }
                }
            }]);

})();