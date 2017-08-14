var myApp = angular.module('myApp',['ui.router','LocalStorageModule','ui.bootstrap','ngSanitize', 'ngRateIt']);

myApp.config(['$stateProvider' ,'$urlRouterProvider',function($stateProvider, $urlRouterProvider) {

//  setting localstorage info


    $urlRouterProvider.otherwise('/home');

    $stateProvider

        // HOME Page ========================================

        .state('home', {
            url: '/home',
            templateUrl: 'view/home.html',
            controller : 'homeController'
        })

        // Create Review  Page =================================

        .state('createReview', {
            url: '/createReview',
            templateUrl: 'view/createReview.html',
            controller : 'createReviewController'

        })

        // Create Login/SignUp  Page =================================
        .state('login', {
            url: '/login',
            templateUrl: 'view/login.html',
            controller : 'logInUserController'

        })

        // Route For Register page =================================

        .state('register', {
            url: '/register',
            templateUrl: 'view/register.html',
            controller : 'registerUserController'

        })

        // Route For Search Review page =================================

        .state('searchReview',{
            url: '/searchReview',
            templateUrl: 'view/searchReview.html',
            controller : 'searchReviewController'
        })

        // Route For read Review Page

        .state('readReview',{
            url: '/readReview/:id',
            templateUrl: 'view/readReview.html',
            controller : 'readReviewController'
        })

        // Route For User Profile

        .state('profile' , {
            url : '/profile',
            templateUrl: 'view/profile.html',
            controller : 'profileController'
        });

}]);