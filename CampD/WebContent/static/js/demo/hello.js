/**
 * angularjs访问rest风格的接口demo
 */

angular.module('hello', []).controller('Hello', 
	function($scope, $http) {
	$http.get('http://localhost:8080/campD_server/demo/greeting').
	    success(function(data) {
	        $scope.greeting = data;
	    }
	);
});