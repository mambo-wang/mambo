(function () {

  'use strict';

  angular.module('app').factory('UserService', ['$resource', function ($resource) {
    var extMethod = {
        'update': {method: 'PUT'},
        'download':{
            method:'GET',
            isArray:true,
            params:{secondaryUrl:'downloads'}
        }
    };
    return $resource('/mambo/users/:id:idList:secondaryUrl', null, extMethod);
  }]);

})();