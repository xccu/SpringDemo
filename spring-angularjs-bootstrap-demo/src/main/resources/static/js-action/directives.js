/**
 * 自定义命令
 */
//1.定义一个指令名为datePicker
actionApp.directive('datePicker',function(){
    return {
        //2.限制为属性指令和样式指令
        restrict: 'AC',
        //3.使用link方法来定义指令，在林肯方法内可使用当前scope，当前元素和元素属性
        link:function(scope,elem,attrs){
            // scope.treeObj = $.fn.zTree.init(elem, scope.settings);
            //4.初始化jqueryui的datePicker
            elem.datepicker();
        }
    };
});


