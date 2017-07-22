(function () {

  'use strict';

  angular.module('app').factory('UserService', ['$resource', function ($resource) {
    return $resource('/users/:id', null,
        {
          'update': {method: 'PUT'}
        });
  }]);

})();