var myApp = angular.module('myApp');

myApp.factory('searchReviewService', ['$http', function($http){

	var searchReviewServiceApi = {};
	
	searchReviewServiceApi.getReview = function(manufacturerId,modelId){
					return  $http.get("http://localhost:8085/carreview/reviews/search?manufacturerId="+manufacturerId+"&modelId="+modelId)
								.then(function(response){
									return response.data;
								})
				}


	searchReviewServiceApi.getReviewById = function(id){
				return  $http.get("http://localhost:8085/carreview/reviews/"+id)
								.then(function(response){
									return response.data;
								})
	}			
	return searchReviewServiceApi;
}]);
