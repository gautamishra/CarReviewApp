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
.controller('readReviewController',['$scope' , '$stateParams','readReviewService','searchReviewService','$window','registerUserService'
			,function($scope ,  $stateParams,readReviewService,searchReviewService , $window,registerUserService){
	$scope.like = "Like";
	var reviewId = $stateParams.id;
	var commentUser;
	var commentBoxLogInFlag = false;
	var commentBox = true;
	$scope.review = "";
	$scope.comments = "";
	$scope.commentObj={
		phoner:null,emailr:null,namer:null
	}
	$scope.comment = "";
	$scope.checklogin = false;
	$scope.showCommentArea = false;
	$scope.commentName = "";
	$scope.name = "";
	$scope.phone = "";
	$scope.email = "";
	$scope.namer = "";
	$scope.phoner = "";
	$scope.emailr = "";
	$scope.reviewComment = "";
	$scope.count = 200;
	var commentDiv;
	$scope.commentOrReply = '';
	$scope.parentId = '';
	
	 // var commentDiv = $('#commentDivId').html();
	 // // commentDiv = commentDiv[0].innerHTML;
	 // console.log(commentDiv);
	// Getting Inf Of Review

	// $.get("http://localhost:8085/carreviewfrontend/public/view/commentSignUpDiv.html",function(data){
	// 	 commentDiv = data;
	// 	console.log(commentDiv);
	// })

	searchReviewService.getReviewById(reviewId)
						.then(function(data){
							$scope.review = data;
							console.log(data);
						});

	//  Getting Comments On Review 
	
	var getComment = function(reviewId){
				readReviewService.getCommentByReviewId(reviewId)
						.then(function(data){
							$scope.comments = data;
							console.log(data);
						});
					}
	getComment(reviewId);

// check user is login or not

 	$scope.checkUserLogin = function(id , value){
 		$scope.parentId = id;
 		$scope.commentOrReply = value;
 		console.log("in checkLogin");
		 	if($window.localStorage.getItem("user") != undefined ){
		 		console.log("user is log in ");
		 		commentUser = JSON.parse($window.localStorage.getItem("user"));
				$scope.commentName = commentUser.name;
		 		$scope.showCommentArea = commentBox;
		 		commentBox  =  !commentBox;

		 	}else if( $window.localStorage.getItem("userComment") !=undefined){
		 		console.log("user is log in ");
		 		commentUser = JSON.parse($window.localStorage.getItem("userComment"));
				$scope.commentName = commentUser.name;
		 		$scope.showCommentArea = commentBox;
		 		commentBox  =  !commentBox;
		 	}
		 	else if(commentBoxLogInFlag){
		 		commentBoxLogInFlag = false;
		 		$scope.checklogin = false;


		 	}
		 	else {
		 		$scope.checklogin = true;
		 		commentBoxLogInFlag = true;
		 		

		 	}
 }


//  sign up for comment 

$scope.postUserdata = function (){
	var data = {};
	data.name = $scope.name;
	data.phone = $scope.phone;
	data.email = $scope.email;
	// data = JSON.stringify(data);
	registerUserService.postUserForComment(data)
						.then(function(response){
							$window.localStorage.setItem("userComment" , JSON.stringify(response));
							commentUser = JSON.parse($window.localStorage.getItem("userComment"));
							$scope.commentName = commentUser.name;
							$scope.checklogin = false;
							$scope.showCommentArea = true;
						});

			
}


// //  sign up for  Reply

// $scope.postUserdataForReply = (obj) =>{
// 	console.log(arguments);
// 	console.log($scope.commentObj);

// 	console.dir(this);
// 	console.dir(obj);
// 	 let data = JSON.stringify(obj);
// 	console.log(data);
// 	// registerUserService.postUserForComment(data)
// 	// 					.then(function(response){
// 	// 						$window.localStorage.setItem("userComment" , JSON.stringify(response));
// 	// 						 commentUser = JSON.parse($window.localStorage.getItem("userComment"));
// 	// 						$scope.commentName = commentUser.name;
// 	// 						console.log(response);
// 	// 						$scope.showCommentArea = true;
// 	// 					});

			
// }

//  For posting Comment

	$scope.postComment = function(){
		var data = {};
		data.userId  = commentUser.userId;
		data.reviewId = $scope.review.reviewId;
		if($scope.commentOrReply == 'comment')
		data.parentId = -1;
		else{
			data.parentId = $scope.parentId;
		}
		data.comment = $scope.reviewComment;
		
		readReviewService.postComment(data)
							.then(function(response){
								console.log(response);
								$scope.comments.push(response);
								$scope.showCommentArea = false;
								getComment(reviewId);
							},function(){
								console.log(response.data);
							})
	}

//  For Posting Likes

	$scope.postLike = function(id){
	var data = {};
		
		data.reviewId = $scope.review.reviewId;
		data = JSON.stringify(data);
		readReviewService.postLike(data)
							.then(function(response){
								// console.log(response);
								$scope.like = "Liked"
							},function(response){
								console.log(response.data);
							})
	}

	// Check USer Login For Reply

	// $scope.checkUserLoginForReply = function(id){
			
			
	// 	 	if($window.localStorage.getItem("user") != undefined ){
	// 	 		console.log("user is log in ");
	// 	 		commentUser = JSON.parse($window.localStorage.getItem("user"));
	// 			$scope.commentName = commentUser.name;
	// 	 		// $scope.showCommentArea = commentBox;
	// 	 		// commentBox  =  !commentBox;

	// 	 	}else if( $window.localStorage.getItem("userComment") !=undefined){
	// 	 		console.log("user is log in ");
	// 	 		commentUser = JSON.parse($window.localStorage.getItem("userComment"));
	// 			$scope.commentName = commentUser.name;
	// 	 		// $scope.showCommentArea = commentBox;
	// 	 		// commentBox  =  !commentBox;
	// 	 	}
	// 	 	else if(commentBoxLogInFlag){
		 		
	// 	 		commentBoxLogInFlag = false;
	// 	 		// $("#commentDivId").remove();
	// 	 			performRecursion(id,false,$scope.comments);
	// 	 	}
	// 	 	else {

	// 	 		// var a = "#i"+id;
	// 	 		commentBoxLogInFlag = true;

	// 	 		// $(a).after(commentDiv);
	// 	 		// $scope.$apply();
	// 	 		performRecursion(id,true,$scope.comments);
	// 	 	}
	// }

//  Function For Comment Length varification

	$scope.chechCommentLength = (event)=>{
		if(event.keyCode != 8 && $scope.count>0){
			$scope.count = $scope.count-1;
		}
		else if (event.keyCode == 8){
			$scope.count = $scope.count+1;
		}
	}

// Recursion For Reply Pop UP Form

	var performRecursion  = (id,bool,comm)=>{

		for(let comment of comm){
			if(comment.commentId == id){
				comment.show = bool;
				return;
			}
			else if(comment.hasOwnProperty('subComments') && comment.subComments instanceof Array && 
				comment.subComments.length > 0){
				performRecursion(id,bool,comment.subComments);
			}
		}
	};

}])
.directive('loginPopup',['registerUserService','$window', function(registerUserService,$window) {
  return {
    templateUrl: 'view/commentSignUpDiv.html',
      restrict: 'E',
        scope: {
        	options:'=',
            onClick: '&'
        },
        controller: function($scope) {
        	  $scope.options={
        	  	namer:'',phoner:'',emailr:''

        	  }
          $scope.click =function(opt){
          		var data = {};
				data.name = opt.namer;
				data.phone = opt.phoner;
				data.email = opt.emailr;
              
             registerUserService.postUserForComment(data)
						.then(function(response){
							$window.localStorage.setItem("userComment" , JSON.stringify(response));
							commentUser = JSON.parse($window.localStorage.getItem("userComment"));
							// performRecursion(id,false,$scope.comments);
						});
                 
            }

        }
  };
}]);