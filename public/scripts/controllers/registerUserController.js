var mayApp = angular.module('myApp');

myApp.controller('registerUserController',['$scope','registerUserService','$location',function($scope,registerUserService,$location){

	console.log("register controller loaded");
	$scope.error = "";
	$scope.fullnamePattern =/^([a-zA-Z]+\s)*[a-zA-Z]+$/;

	$scope.userName		 = "";
	$scope.userEmail 	 = "";
	$scope.userPassword  = "";
	$scope.userPhone 	 = "";
	
	
	$scope.register = function(){
		var data = {};
		data.name 		= $scope.userName;
		data.email 		= $scope.userEmail;
		data.password 	= $scope.userPassword;
		data.phone		= $scope.userPhone;
		var data = JSON.stringify(data);
		registerUserService.postUser(data)
							.then(function(response){
								$location.path('/login');
							},function(response){
								$scope.error = response.data;
								console.log(response.data);
							})
	}
	// registerUserService.postUser()

}])