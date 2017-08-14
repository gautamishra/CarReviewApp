var myApp = angular.module('myApp');

myApp.factory('registerUserService', ['$http', function($http){

	var registerUserServiceApi = {};

//  Registering a User 

	registerUserServiceApi.postUser = function(data){
		return $http.post("http://localhost:8085/carreview/user", data)
			 .then(function(response){
			 	return response.data; 
			 })
	}


//  Register User For Comment

	registerUserServiceApi.postUserForComment = function(data){

		return $http.post("http://localhost:8085/carreview/user/comment", data)
			 .then(function(response){
			 	return response.data; 
			 },function(response){
			 	return response;
			 });
	}

	return registerUserServiceApi;

}])
