package com.atguigu.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//演示从HttpSession保存作用域中获取数据
public class Demo05Servlet extends HttpServlet {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取Session
        HttpSession session = req.getSession();
        Object obj = session.getAttribute("uname");
        System.out.println(obj);
    }
}
