var mayApp = angular.module('myApp');

myApp.controller('createReviewController',['$scope','createReviewService','$window','$location'
								,function($scope , createReviewService,$window,$location){
	console.log("create controller loaded");

$scope.onlybetween05 = /[0-5]/;
$scope.manufacturers= [{manufacturerId:null, manufacturerName:'Select'}];
$scope.manufacturerModelNames=[{ modelId:null,modelName:'Select', manufacturer:[]}]
$scope.imgFile=null;
$scope.filePreview=null;
$scope.writeReviewFlag = false ;
var user;
// Review Data

$scope.activeManufacturer  		= "";
$scope.activeModelName 	   		= "";
$scope.reviewTitle         		= ""; 
$scope.reviewRating       		= ""; 
$scope.reviewWhatGood     		= ""; 
$scope.reviewWhatToImprove 		= ""; 
$scope.reviewMileage       		= ""; 
$scope.reviewMaintanenceCost    = ""; 
$scope.reviewAnyComment         = ""; 
$scope.reviewPhoto       		= ""; 
			

	//  Checking User Is Login Or Not 

	if($window.localStorage.getItem("user") != undefined ){
		$scope.writeReviewFlag = false ;
	}
	else{
		$scope.writeReviewFlag = true ;
	}

	// getting data for manufacturer

 	createReviewService.getManufacturer()
 	.then(function(response){
 			$scope.manufacturers= response;
 			$scope.activeManufacturer=$scope.manufacturers[0];
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

// $scope.uploadFile = function (input) {
//  	console.log("upload function ");
//     if (input.files && input.files[0]) {
//         var reader = new FileReader();
//         reader.onload = function (e) {
 
//             //Sets the Old Image to new New Image
//             // $('#photo-id').attr('src', e.target.result);

//  			$scope.filePreview = e.target.result;
 			
//             //Create a canvas and draw image on Client Side to get the byte[] equivalent
//             // var canvas = document.createElement("canvas");
//             var imageElement = document.createElement("img");
 
//             imageElement.setAttribute('src', e.target.result);
//             canvas.width = imageElement.width;
//             canvas.height = imageElement.height;
//             var context = canvas.getContext("2d");
//             context.drawImage(imageElement, 0, 0);
//             var base64Image = canvas.toDataURL("image/jpeg");
 	
//             // Removes the Data Type Prefix 
//             // And set the view model to the new value
//             $scope.reviewPhoto = base64Image.replace(/data:image\/jpeg;base64,/g, '');
//             console.log(base64Image);
//             // $scope.reviewPhoto = base64Image;
//             $scope.$apply()	;																																																																																											
//         }
                
//         //Renders Image on Page
//         reader.readAsDataURL(input.files[0]);
//     }


// };
 		
 		
 //  Reading image 

	$scope.processImage=function(val){

		this.imgFile=val;
			 console.dir(val);
			  if (this.imgFile && this.imgFile.files[0]) {
		    var reader = new FileReader();
		    reader.onload =(e)=> {


		    	this.filePreview=e.target.result;
		    	
				$scope.$apply();
		    	console.dir(this);
		    	$scope.reviewPhoto = this.filePreview.replace(/data:image\/jpeg;base64,/g, '');
		    	console.log($scope.reviewPhoto);
		    };
		    reader.readAsDataURL(this.imgFile.files[0]);
		  }
	}

//  Sending Review To Api

	$scope.sendReview = function(){
			if($window.localStorage.getItem("user") != undefined){
	 		var data = {};
	 		user = JSON.parse($window.localStorage.getItem("user"));
	 		 
	 		data.userId 			= user.userId;
	 		data.manufacturerId 	= $scope.activeManufacturer.manufacturerId;
	 		data.modelId			= $scope.activeModelName.modelId;
	 		data.title 				= $scope.reviewTitle ;
	 		data.rating 			= $scope.reviewRating;
	 		data.whatGood 			= $scope.reviewWhatGood;
	 		data.whatImprove 		= $scope.reviewWhatToImprove;
	 		data.mileage 			= $scope.reviewMileage;
	 		data.maintanenceCost 	= $scope.reviewMaintanenceCost;
	 		data.anyComment 		= $scope.reviewAnyComment;
	 		data.addPhoto        	= $scope.reviewPhoto;

	 		var data = JSON.stringify(data);
	 		console.log(data);
	 		createReviewService.postReview(data)
	 							.then(function(response){
	 								console.log(response);
	 								$location.path('/readReview/' + response.reviewId);
	 							})
	 		}
	 		else{
	 			$scope.writeReviewFlag = true ;
	 		}
	 }	
 		

 // Convert Image In BLOB

//  	function dataURItoBlob(dataURI) {
//     // convert base64/URLEncoded data component to raw binary data held in a string
//     var byteString;
//     if (dataURI.split(',')[0].indexOf('base64') >= 0)
//         byteString = atob(dataURI.split(',')[1]);
//     else
//         byteString = unescape(dataURI.split(',')[1]);

//     // separate out the mime component
//     var mimeString = dataURI.split(',')[0].split(':')[1].split(';')[0];

//     // write the bytes of the string to a typed array
//     var ia = new Uint8Array(byteString.length);
//     for (var i = 0; i < byteString.length; i++) {
//         ia[i] = byteString.charCodeAt(i);
//     }

//     return new Blob([ia], {type:mimeString});
// }


// var BASE64_STRING = 'data:image/jpeg;base64,/9j/4QN6RXhpZgAASUkqAAgAAAAIA';

//     function convertURIToBinary(dataURI) {
//       var base64Index = dataURI.indexOf(BASE64_STRING ) + BASE64_STRING.length;
//       var base64 = dataURI.substring(base64Index);
//       var raw = window.atob(base64);
//       var rawLength = raw.length;
//       var array = new Uint8Array(new ArrayBuffer(rawLength));

//       for(i = 0; i < rawLength; i++) {
//         array[i] = raw.charCodeAt(i);
//       }
//       return array;
//     }


}])

  