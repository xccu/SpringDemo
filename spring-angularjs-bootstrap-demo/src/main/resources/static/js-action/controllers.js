/**
 * 控制器的定义
 */

//1.定义控制器View1Controller，并注入$rootScope, $scope和$http
actionApp.controller('View1Controller', ['$rootScope', '$scope', '$http', function($rootScope, $scope,$http) {
    //2.使用$scope.$on监听$viewContentLoaded事件，可以再页面内容加载完成后进行一个操作
    $scope.$on('$viewContentLoaded', function() {
    	console.log('页面加载完成');
    });
    
    //3.核心代码，结合view1界面一起理解
    $scope.search = function(){ //3.1在scope内定义一个方法search，在页面上通过ng-click调用
      //3.2通过$scope.personName获取页面定义的ng-model="personName"的值
      personName = $scope.personName;
      //3.3$http.get向服务端地址search发送get请求
      $http.get('search',{
          //3.4使用params添加请求参数
    	  params:{personName:personName}
      }).success(function(data){  //3.5使用success方法作为请求成功后的回调
         //3.6将服务端数据data通过$scope.person赋值给模型person
    	 $scope.person=data;
      });;
     
    };
}]);

actionApp.controller('View2Controller', ['$rootScope', '$scope',  function($rootScope, $scope) {
    $scope.$on('$viewContentLoaded', function() {
    	console.log('页面加载完成');
    });
}]);


