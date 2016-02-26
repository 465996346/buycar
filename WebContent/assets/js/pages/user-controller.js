/* TYPE YOUR JAVA SCRIPT HERE */
var buycarApp = angular.module('buycarApp', []);

//初始化用户角色下拉菜单
function initRoles($scope) {
	$scope.roles = [
	    {name : "系统管理员", value : "1"},
	    {name : "普通用户", value : "2"}
	];
};

buycarApp.controller('loginController', function($scope, $http) {
	$scope.login = function () {
		var form = $("#loginFrom");
		$.post(
				"http://192.168.31.112:8080/buycar/web/user/login/json", 
		        form.serialize(),
		        function(data)
		        {
		            if (data.status == 1) {
		                window.location.href = data.url;
		            } else {
		            	$("#errMessage").html(data.errMessage);
		            }
		            
		        },
	            "json"
			);
	};
});

buycarApp.controller('listController', function($scope, $http) {
	var pageSize = 10;

	$scope.getsMore = function () {
		pageSize = pageSize + 10;
		$scope.getsDatas();
	};
    $scope.getsDatas = function () {
    	$http.get("http://192.168.31.112:8080/buycar/web/user/list/json?currentPage=1&pageSize=" + pageSize).success(
                function (response) {
                    $scope.names = response.datas;
                    $scope.pagination = response.pagination;
                    $scope.user = response.USER;
                });
    };
    $scope.getsDatas();
});

buycarApp.controller('addController', function($scope, $http) {
	getUser($scope, $http);
	
	$scope.addUser = function () {
		postDatas("#addUserFrom", "http://192.168.31.112:8080/buycar/web/user/save");
	};
});