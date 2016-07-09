'use strict';

/* Services */

angular.module('BeerServices', [])
	.factory('Beers', function($http){	
		var promise = {
			get: function (){
				var promise = $http.get("http://localhost:8080/BeerApp/BeerList").then(function(response){
					console.log(response);
					return response.data;
				});
				return promise;
			}
		}
		return promise;
	})
	.factory('Beer', function($http){	
		var promise = {
			get: function (id){
				var promise = $http.get("http://localhost:8080/BeerApp/BeerDetail?id="+id).then(function(response){
					console.log(response);
					return response.data;
				});
				return promise;
			},
			create: function (data) {
			  return $http({
		          headers: {'Content-Type': 'application/json'},
		          url: 'http://localhost:8080/BeerApp/BeerCreate',
		          method: "POST",
		          data: data,
		        });
			  	return promise;
		      },
		      remove: function (id){
		    	  return $http({
			          headers: {'Content-Type': 'application/json'},
			          url: 'http://localhost:8080/BeerApp/BeerDelete',
			          method: "POST",
			          data: id,
			      });
				  return promise;
		      },
		      modify: function (data) {
		    	  return $http({
			          headers: {'Content-Type': 'application/json'},
			          url: 'http://localhost:8080/BeerApp/BeerModify',
			          method: "POST",
			          data: data,
			        });
				  	return promise;
			      }
		      }
		   return promise;
	});