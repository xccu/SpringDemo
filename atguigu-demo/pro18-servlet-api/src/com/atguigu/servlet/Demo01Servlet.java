package com.atguigu.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//等价于配置web.xml
@WebServlet(urlPatterns = {"/demo01"} ,
        initParams = {
                @WebInitParam(name="hello",value="world"),
                @WebInitParam(name="uname",value="jim")
        })
public class Demo01Servlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        ServletConfig config = getServletConfig();
        String initValue = config.getInitParameter("hello");
        System.out.println("initValue = "+initValue);

        ServletContext servletContext = getServletContext();
        String contextConfigLocation = servletContext.getInitParameter("contextConfigLocation");
        System.out.println("contextConfigLocation = "+contextConfigLocation);
    }

    @Override
    protected void service(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
        req.getServletContext();
        req.getSession().getServletContext();
    }

/*
  1) Servlet生命周期：实例化、初始化、服务、销毁
  2) Servlet中的初始化方法有两个：init() , init(config)
    其中带参数的方法代码如下：
    public void init(ServletConfig config) throws ServletException {
        this.config = config ;
        init();
    }
    另外一个无参的init方法如下：
    public void init() throws ServletException{}

   如果我们想要在Servlet初始化时做一些准备工作，那么我们可以重写init方法
   我们可以通过如下步骤去获取初始化设置的数据
   - 获取config对象：ServletConfig config = getServletConfig();
   - 获取初始化参数值： config.getInitParameter(key);
   */

}
