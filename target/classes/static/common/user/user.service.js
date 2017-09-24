(function () {

  'use strict';

  angular.module('app').factory('UserService', ['$resource', function ($resource) {
    var extMethod = {
        'update': {method: 'PUT'},
        'download':{
            method:'GET',
            isArray:true,
            params:{secondaryUrl:'downloads'}
        },
        'loginUser':{
            method:'GET',
            isArray:false,
            params:{secondaryUrl:'loginUser'}
        }
    };
    return $resource('/mambo/users/:id:idList:secondaryUrl', null, extMethod);
  }]);

})();