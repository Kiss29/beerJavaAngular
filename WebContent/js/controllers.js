'use strict';

/* Controllers */

angular
  .module('BeerControllers', [])
  .controller('BeerListCtrl', ['$scope', 'Beers', function($scope, Beers) {
	console.log("lol");
    Beers.get().then(function(response){
    	$scope.beers = response;
    });

    $scope.orderProp = 'alcohol';
  }])
  .controller('BeerDetailCtrl', ['$scope', '$routeParams', '$location', 'Beer', function($scope, $routeParams, $location,  Beer) {
	console.log($routeParams.beerId);
    Beer.get($routeParams.beerId).then(function(response){
    	$scope.beer = response;
    });
    $scope.modify = function(){
    	$location.url('/beer-modify/'+$routeParams.beerId);
    }
    $scope.remove = function(){
    	var id = {
    			'id': $routeParams.beerId
    			}
    	console.log(id);
	    Beer.remove(id).then(function(response){
	    	$scope.remove = "bière supprimée"; 	  
	    	$location.url('/beers');
	  	  	console.log("success : "+response);
		    },function(error){
					console.log(error);
					$scope.error=true;
					$scope.errorMessage=error.data;	    			
		  });
    };
  }])
  .controller('BeerCreateCtrl', ['$scope', '$location', '$routeParams', 'Beer', function($scope, $location, $routeParams, Beer) {
	  $scope.error=false;
	  $scope.errorMessage="";
	  
      $scope.submit = function(data) {
    	    var beer = ({
    	    	  'name': data.name,
    	    	  'alcohol': data.alcohol,
    	    	  'description': data.description,
    	    	  'img': data.img,
    	    	  'availability': data.availability,
    	    	  'brewery': data.brewery,
    	    	  'label': data.label,
    	    	  'serving': data.serving,
    	    	  'style': data.style
    	      })
    	      Beer.create(beer).then(function(response){
    	    	  $location.url('/beers');
    	    	  console.log("success : "+response);
    	      },function(error){
	    			console.log(error);
	    			$scope.error=true;
	    			$scope.errorMessage=error.data;	    			
    	    });
      };
  }])
  .controller('BeerModifyCtrl', ['$scope', '$routeParams', '$location', 'Beer', function($scope, $routeParams, $location,  Beer) {
	console.log($routeParams.beerId);
	Beer.get($routeParams.beerId).then(function(response){
	   $scope.beer = response;
	});
	 
	$scope.error=false;
	$scope.errorMessage="";
	  
	$scope.modify = function(data) {
		var beer = ({
	    	  'name': data.name,
	    	  'alcohol': data.alcohol,
	    	  'description': data.description,
	    	  'img': data.img,
	    	  'availability': data.availability,
	    	  'brewery': data.brewery,
	    	  'label': data.label,
	    	  'serving': data.serving,
	    	  'style': data.style
	      })
	      Beer.modify(beer).then(function(response){
	    	  $location.url('/beers');
	    	  console.log("success : "+response);
	      },function(error){
				console.log(error);
				$scope.error=true;
				$scope.errorMessage=error.data;	    			
	    });
      
    };
  }]);

