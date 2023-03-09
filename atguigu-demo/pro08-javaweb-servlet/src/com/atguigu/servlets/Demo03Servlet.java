package com.atguigu.servlets;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//演示Session
public class Demo03Servlet extends HttpServlet {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取Session
        HttpSession session = req.getSession();
        System.out.println("session ID:"+ session.getId());
        System.out.println("isNew:"+ session.isNew());
        System.out.println("MaxInactiveInterval:"+ session.getMaxInactiveInterval());
    }
}
