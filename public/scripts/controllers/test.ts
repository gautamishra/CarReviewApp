var mayApp = angular.module('myApp');

myApp.controller('createReviewController',['$scope','createReviewService',function($scope , createReviewService){
	console.log("create controller loaded");



	// getting data for manufacturer

 	createReviewService.getManufacturer()
 	.then(function(response){
 		$scope.manufacturers= response;
 		$scope.score=$scope.manufacturers[0];
 	})




 	$scope.bringModel = function(manufacturer){
 		var id = manufacturer.manufacturerId;
 		console.log(id);
 		createReviewService.getModel(id)
 							.then(function(response){
 							// $scope.manufacturers= response;
 					})
 			}


 			// $scope.bringModel=$scope.bringModelt.bind(this);

 		
}]);
