1.设置编码
    1)get请求方式
        //get方式目前不需要设置编码（基于tomcat8）
        //如果是get请求发送的中文数据，转码稍微有点麻烦（tomcat8之前）
        String fname = request.getParameter("fname");
        //1.将字符串打散成字节数组
        byte[] bytes = fname.getBytes("ISO-8859-1");
        //2.将字节数组按照设定的编码重新组装成字符串
        fname = new String(bytes,"UTF-8");
        */
    2)post请求方式
        //post方式下，设置编码，防止中文乱码

    注意：需要注意的是，设置编码这一句代码必须在所有的获取参数动作之前


2.Servlet的继承关系
    1)继承关系
        javax.Servlet.Servlet接口
            javax.Servlet.GenericServlet抽象类
                javax.Servlet.http.HttpServlet
    2)相关方法
        javax.Servlet.Servlet接口
            void init(ServletConfig var1) -初始化方法
            void service(ServletRequest var1, ServletResponse var2) -服务方法
            void destroy() -销毁方法
        javax.Servlet.GenericServlet抽象类
            void service(ServletRequest var1, ServletResponse var2) -仍然是抽象的

3.Servlet的生命周期

4.Http协议

5.会话

6.Thymeleaf