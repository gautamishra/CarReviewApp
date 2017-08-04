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
.controller('homeController',['homeService','$scope' ,function( homeService , $scope ){
	console.log("home controller loaded");
	
	homeService.getReviews()
	.then(function(data){
		$scope.reviews = data; 
		console.log($scope.reviews)

	});
}])
