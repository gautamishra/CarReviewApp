var myApp = angular.module('myApp');

myApp.factory('createReviewService', ['$http', function($http){
	
	var createReviewServiceApi = {};
	createReviewServiceApi.getManufacturer = function(){
		return $http.get("http://localhost:8085/carreview/manufacturer")
		.then(function(response){
			return response.data;
		});
	}

	createReviewServiceApi.getModel = function(id){
		return $http.get("http://localhost:8085/carreview/carmodel/manuf/" + id)
		.then(function(response){
			return response.data;
		});
	}

//  Post request To Save Review In Data Base

	createReviewServiceApi.postReview = function(data){

		return $http.post("http://localhost:8085/carreview/reviews", data)
					.then(function(response){
						return response.data;
					});
	}



	return createReviewServiceApi;

}])