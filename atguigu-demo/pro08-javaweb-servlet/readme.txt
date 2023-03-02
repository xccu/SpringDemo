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


2.Servlet的继承关系 -重点查看Service方法
    1)继承关系
        javax.Servlet.Servlet接口
            javax.Servlet.GenericServlet抽象类
                javax.Servlet.http.HttpServlet抽象子类
    2)相关方法
        javax.Servlet.Servlet接口
            void init(ServletConfig var1) -初始化方法
            void service(ServletRequest var1, ServletResponse var2) -服务方法
            void destroy() -销毁方法
        javax.Servlet.GenericServlet抽象类
            void service(ServletRequest var1, ServletResponse var2) -仍然是抽象的
        javax.Servlet.http.HttpServlet抽象子类
            void service(HttpServletRequest req, HttpServletResponse resp) -不是抽象的
                1.String method = req.getMethod(); -获取请求方式
                2.各种if判断，根据请求方式不同，决定去调用不同的do方法
                3.在HttpServlet抽象类中，do方法基本差不多
                 if (method.equals("GET")) {
                     this.doGet(req, resp);
                 } else if (method.equals("HEAD")) {
                     this.doHead(req, resp);
                 } else if (method.equals("POST")) {
                      this.doPost(req, resp);
                 } else if (method.equals("PUT")) {
                     this.doPut(req, resp);
                 } else if (method.equals("DELETE")) {
                     this.doDelete(req, resp);
                 } ...
                 4.小结:
                    1)继承关系:HttpServlet->GenericServlet->Servlet
                    2)Servlet核心方法:init,service,destroy
                    3)Service方法:当有请求时，Service方法会自动响应(由Tomcat容器调用)
                        在HttpServlet中分析请求方式: get/post/put/delete,再决定是哪个do方法
                        do方法默认都是405实现:需要子类去实现，否则报405错误
                    4)在新建Servlet时，我们才回去考虑请求方法，从而决定重写哪个do方法

3.Servlet的生命周期

4.Http协议

5.会话

6.Thymeleaf




































