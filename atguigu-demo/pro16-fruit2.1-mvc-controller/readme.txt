review:

1. 保存作用域
   原始情况下，保存作用域我们可以认为有四个： page（页面级别，现在几乎不用） , request（一次请求响应范围） , session（一次会话范围） , application（整个应用程序范围）
   1） request：一次请求响应范围
   2） session：一次会话范围有效
   3） application： 一次应用程序范围有效

2. 路径问题
   1） 相对路径
   2） 绝对路径

3. 实现库存系统的功能


调试故障和解决方法：
    1.Method.getParameters();- 获取方法的参数列表：
        结果：arg0,arg1,arg2...
        与实际的形参名称不同，request.getParameter(parameterName);将无法获取参数
            java8以后的解决方法（ideal）:
            File->Settings->Build,Execution,Deployment->Compiler->java Compiler->Additional command line parameters
            添加:-parameters
        结果： oper,keyword,pageNo...
