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
Servlet生命周期是Tomcat容器维护的
    1) 生命周期：从从出生到死亡的过程，对应Servlet的三个方法：:init,service,destroy
    2) 默认情况下
        - 第一次请求时，该Servlet会进行实例化，init，然后service
        - 从第二次请求开始，每次都是service
        - 当容器关闭时，其中所有servlet实例会被销毁，调用destroy方法
    3) 通过案例我们发现
        - tomcat只会创建一个Servlet实例，所有请求都是这个实例响应。
        - 第一次请求时，tomcat才会对servlet进行实例化,优点是提高启动速度，缺点是第一次请求时耗时较长
        - 结论:如果需要提供系统启动速度，当前默认情况就是这样，如果要提高响应速度，应该设置Servlet响应时机
    4) Servlet的初始化时机:
        - 默认第一次接收请求时
        - 可以通过<load-on-startup>设置Servlet启动先后顺序，数字越小，启动越靠前，最小值0
    5) Servlet在容器中是单例的，线程不安全的，启发：尽量不要再Servlet中定义成员变量，如果不得不使用，不要根据成员变量的值做逻辑判断或修改成员变量的值

4.Http协议
    1) http称之为超文本传输协议
    2) http是无状态的
    3) http请求响应包含两部分：请求和响应
        请求:
            包含三部分：请求行，请求消息头，请求主体
            1 请求行包含三个信息:请求方式，请求的url，请求的协议（一般都是HTTP1.1）
            2 请求消息头中包含很多客户端需要告诉服务器的信息，如浏览器型号，版本，能接收的内容的类型，发送的内容类型，内容的长度等等
            3 请求体分三种情况：
                get方式:无请求体，但是有queryString在URL后
                post方式:有请求体，form data
                json格式:有请求体，request payload
        响应:
            包含三部分:响应行，响应头，响应体
            1 响应行包含三个信息：协议，响应状态码(200)，响应状态(ok)
            2 响应头（response header） 包含了服务器的信息，服务器发给浏览器的信息（内容发媒体信息，编码，内容长度等）
            3 响应体：响应的实际内容（如请求的html页面等）

5.会话

6.Thymeleaf


常见响应状态码:
//200:正常响应
//404:找不到资源
//405:请求方式不支持
//500：服务器内部错误






































