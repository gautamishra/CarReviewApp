var mayApp = angular.module('myApp');

// filter for reading newline 

myApp.filter('breakFilter', function () {
    return function (text) {
        if (text !== undefined) return text.replace(/\n/g, '<br />');
    };
})

myApp.controller('createReviewController',['$scope','createReviewService','$window','$location','$uibModal'
								,function($scope , createReviewService,$window,$location ,$uibModal){
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
			
$scope.signup = false;

// for rating ========

$scope.rate = 1;
$scope.max = 5;



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

	// getting data for manufacturer
	var getManufacturers = function(){
 	createReviewService.getManufacturer()
 	.then(function(response){
 			$scope.manufacturers= response;
 			$scope.activeManufacturer=$scope.manufacturers[0];
 			$scope.bringModel($scope.manufacturers[0]);
 		});
	}

	getManufacturers();


 		
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

//   Creating A  Review  =========================================================================

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
	 			$scope.showForm();
	 		}
	 }	
 		

	 //  Code For Modal Dialog For Sign up Or Login===================================================

	  $scope.showForm = function () {
            $scope.message = "Model Is Loaded";
            console.log($scope.message);

            var modalInstance = $uibModal.open({
                templateUrl: 'view/signupOrLogin.html',
                controller: ModalInstanceCtrl,
            });

            modalInstance.result.then(function (selectedItem) {
                $scope.selected = selectedItem;
            }, function () {
                // console.log("canceled" + new Date())
            });
        };


        $scope.change = function(){
        	$scope.signup = !$scope.signup;
        }

//  Checking User Is Login Or Not 

	if($window.localStorage.getItem("user") != undefined ){
		$scope.writeReviewFlag = false ;
	}
	else{
		// $scope.writeReviewFlag = true ;

		$scope.showForm();
	}



	 

}]);







//  Controller For Modal===============================================================================

var ModalInstanceCtrl	 = function ($scope, $uibModalInstance , loginUserService,$window,registerUserService) {
    $scope.form = {}
    $scope.signup = false;

    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };

    $scope.change = function(){
        $scope.signup = ! $scope.signup;
    }


// Method for user Sign Up through Modal ===============================================================

   $scope.submitForm = function(user){
		var data = {};
		data.name 		= user.fullname;
		data.email 		= user.email;
		data.password 	= user.password;
		data.phone		= user.phone;
		var data = JSON.stringify(data);
		registerUserService.postUser(data)
							.then(function(response){
								$scope.signup = ! $scope.signup;
							},function(response){
								$scope.error = response.data;
								console.log(response.data);
							})
	}


//  Method for user Log in Through Modal ==================================================================

    $scope.login = function(user1){
    	console.log(user1);
    	var data = {};
		data.userName = user1.email;
		data.password = user1.password;
		loginUserService.loginAuthentication(data)
						.then(function(response){
							
							$window.localStorage.setItem('user',JSON.stringify(response));							
							 $uibModalInstance.dismiss('cancel');
							
						},
						function(response){
							console.log(response.data);
							$scope.error = response.data;
							
						});					
    }
};

  