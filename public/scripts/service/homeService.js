var myApp = angular.module('myApp');

myApp.factory('homeService', ['$http', function($http){
	
	var homeServiceApi = {};
	homeServiceApi.getReviews = function(){
		return $http.get("http://localhost:8085/carreview/reviews")
		.then(function(response){
			return response.data;
		});
	}

	return homeServiceApi;

}])