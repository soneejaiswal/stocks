angular.module('app.services', []).factory('Stock', function($resource) {
  return $resource('/api/stocks/:id', { id: '@id' }, {
    update: {
      method: 'PUT'
    },
  	save:{
      url:'/api/stocks/',
      method:'POST'
  	},
  	deleteStock:{
  		 method: 'DELETE'
  	}
  });
}).service('popupService',function($window){
    this.showPopup=function(message){
        return $window.confirm(message);
    }
});
