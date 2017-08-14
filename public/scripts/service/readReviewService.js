var myApp = angular.module('myApp');

myApp.factory('readReviewService', ['$http', function($http){

	var readReviewServiceApi = {};

	readReviewServiceApi.getCommentByReviewId = function(id){
			return $http.get("http://localhost:8085/carreview/comment/reply/review/"+id)
						.then(function(response){
							return response.data;
						})
	}

	// posting user comment

	readReviewServiceApi.postComment = function(data){

			return $http.post("http://localhost:8085/carreview/comment", JSON.stringify(data))
						.then(function(response){
							return response.data;
						},function (response){
							return response;
						})
	}



// For Posing like

	readReviewServiceApi.postLike = function(data){
			
			return $http.post("http://localhost:8085/carreview/like",data)
						.then(function(response){
							return response.data;
						},function (){
							return response;
						})
	}

//  For Getting Like On any Review

	readReviewServiceApi.getLikeOnReviewId = function(id){
			return $http.get("http://localhost:8085/carreview/like/review/" + id)
						.then(function(response){
							return response.data;
						},function(response){
							console.log(response.data);
						})
	}

	return readReviewServiceApi;
}]);
	