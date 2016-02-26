// 获取已经登录的用户信息
function getUser($scope, $http) {
	$http.get("http://192.168.31.112:8080/buycar/web/user/get/user").success(
            function (response) {
            	if (response.status == 1) {
        			$scope.user = response.USER;
        		}
            });
};

//jq post提交表单
function postDatas(elementId, url) {
	var form = $(elementId);
    $.post(
        url, 
        form.serialize(),
        function(data)
        {
            if (data.status == 1) {
                alert(data.message);
                window.location.href = data.url;
            } else {
                alert(data.errMessage);
            }
            
        },
        "json"
    );
};