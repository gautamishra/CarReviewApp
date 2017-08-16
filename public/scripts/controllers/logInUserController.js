var mayApp = angular.module('myApp');

myApp.controller('logInUserController',['$scope','loginUserService','$location','$window'
				,function($scope,loginUserService,$location,$window){

	console.log("login controller loaded");
 	
	
	
	$scope.user  = {};
	$scope.error = "";
	$scope.isLogIn = false;
	$scope.notPartialRegister = true;
	$scope.userName = "";

	if($scope.userName != ""){
		$scope.notPartialRegister = false;
	}

//  checking user is already logged in or not

	if($window.localStorage.getItem("user"))
	{
		$scope.isLogIn = true;
		$scope.user = JSON.parse($window.localStorage.getItem("user"));

	}


	$scope.login = function(user){
		var data = {};
		data.userName = user.userName;
		data.password = user.password;
		loginUserService.loginAuthentication(data)
						.then(function(response){
							$scope.user = response;
							$window.localStorage.setItem('user',JSON.stringify(response));
							$location.path("/home")
						},
						function(response){
							console.log(response.data);
							if(response.data.message == "You need to set Your Password first"){
								$scope.notPartialRegister = false;
								$scope.userName = user.userName;
							}
							$scope.error = response.data;
						});					
	}

	$scope.logout= function(){
		console.log("logout");
		if(!$window.localStorage.getItem("user")){
			$scope.error = {message : "Please Log in First"};
			$window.localStorage.removeItem("userComment");
		}
		else{
			$scope.isLogIn = false;
			$window.localStorage.removeItem("user");
			$window.localStorage.removeItem("userComment");
			$scope.error = {message : "You are succesfully Logged Out "};
		}
	}

	// set Password =================

	$scope.setPassword = function(user){
		if(user.password == user.confirmPassword){
			var data = {};
			data.userName = $scope.userName;
			data.password = user.password;
			console.log(data);
		}
		else{
			$scope.error.message = "password does not match";
		}

	}

}])