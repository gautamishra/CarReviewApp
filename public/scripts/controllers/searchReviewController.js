var mayApp = angular.module('myApp');
myApp.filter('bytetobase', function () {
    return function (buffer) {
        var binary = '';
        var bytes = new Uint8Array(buffer);
        var len = bytes.byteLength;
        for (var i = 0; i < len; i++) {
            binary += String.fromCharCode(bytes[i]);
        }
        return window.btoa(binary);
    };
})

.controller('searchReviewController' , ['$scope','createReviewService','searchReviewService'
										  ,function($scope,createReviewService,searchReviewService){
		console.log("search review");
		$scope.manufacturers= [{manufacturerId:null, manufacturerName:'Select'}];
		$scope.manufacturerModelNames=[{ modelId:null,modelName:'Select', manufacturer:[]}];
		$scope.reviews = [];

// for Loading Manufacturer Details From DB==================

		createReviewService.getManufacturer()
 							.then(function(response){
						 			$scope.manufacturers= response;
						 			$scope.activeManufacturer=$scope.manufacturers[0];
						 			$scope.bringModel($scope.manufacturers[0]);
			 	})


// getting data for Model Names

 	$scope.bringModel = function(manufacturer){
 		var id = manufacturer.manufacturerId;
 		console.log(id);
 		createReviewService.getModel(id)
 							.then(function(response){
 							 $scope.manufacturerModelNames= response;
 							 $scope.activeModelName=$scope.manufacturerModelNames[0];

 					})
 			}

//  function for searching reviews

	$scope.search = function(){
		$scope.reviews =[];
		$scope.error =[];
		console.log( $scope.activeManufacturer.manufacturerId + " "+ $scope.activeModelName.modelId);
				searchReviewService.getReview($scope.activeManufacturer.manufacturerId ,$scope.activeModelName.modelId)
									.then(function(response){
										$scope.reviews = response;
										console.log($scope.reviews)
										// $scope.$apply();
									},function(response){
										$scope.error = response.data;
										console.log($scope.error);
									});
			}
}])