var myApp = angular.module('myApp');

myApp.factory('loginUserService', ['$http', function($http){
	
	var loginUserServiceApi = {};

	//  Checking Login Credential

	loginUserServiceApi.loginAuthentication = function(data){
			return $http.post("http://localhost:8085/carreview/user/login", data)
						.then(function(response){
							return response.data;
						});
	}

	loginUserServiceApi.setPassword = function(data){
		return $http.post("http://localhost:8085/carreview/user/setPassword" , data)
					.then(function(response){
						return response.data;
					},function(response){
						return response.data;
					});
	}


return loginUserServiceApi;
}]);