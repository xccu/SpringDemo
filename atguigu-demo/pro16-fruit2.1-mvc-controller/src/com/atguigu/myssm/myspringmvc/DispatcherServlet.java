package com.atguigu.myssm.myspringmvc;

import com.atguigu.myssm.util.StringUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

//中央控制器类
//*.do表示匹配所有以.do结尾的请求
@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet{

    private Map<String,Object> beanMap = new HashMap<>();

    public DispatcherServlet(){
    }

    public void init() throws ServletException {
        super.init();

        System.out.println("DispatcherServlet init config...");
        System.out.println("get beans:");
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("applicationContext.xml");
            //1.创建DocumentBuilderFactory
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            //2.创建DocumentBuilder对象
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder() ;
            //3.创建Document对象
            Document document = documentBuilder.parse(inputStream);

            //4.获取所有的bean节点
            NodeList beanNodeList = document.getElementsByTagName("bean");
            for(int i = 0 ; i<beanNodeList.getLength() ; i++){
                Node beanNode = beanNodeList.item(i);
                if(beanNode.getNodeType() == Node.ELEMENT_NODE){
                    //获取并强制转换为元素节点
                    Element beanElement = (Element)beanNode ;
                    //获取元素节点的id属性
                    String beanId =  beanElement.getAttribute("id");
                    //获取元素节点的class属性
                    String className = beanElement.getAttribute("class");
                    //创建bean实例
                    Class controllerBeanClass = Class.forName(className);
                    Object beanObj = controllerBeanClass.newInstance() ;

                    System.out.println(className);
                    //bean实例写入map
                    beanMap.put(beanId , beanObj);
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("UTF-8");
        //假设url是：  http://localhost:8080/pro15/hello.do
        //那么servletPath是：    /hello.do
        // 我的思路是：
        // 第1步： /hello.do ->   hello   或者  /fruit.do  -> fruit
        // 第2步： hello -> HelloController 或者 fruit -> FruitController
        String servletPath = request.getServletPath();
        servletPath = servletPath.substring(1);
        int lastDotIndex = servletPath.lastIndexOf(".do") ;
        servletPath = servletPath.substring(0,lastDotIndex);

        Object controllerBeanObj = beanMap.get(servletPath);

        String operate = request.getParameter("operate");
        if(StringUtil.isEmpty(operate)){
            operate = "index" ;
        }

        try {
            Method[] methods = controllerBeanObj.getClass().getDeclaredMethods();
            for(Method method : methods){
                if(operate.equals(method.getName())){
                    //1.统一获取请求参数

                    //1-1.获取当前方法的参数，返回参数数组
                    /*
                    获取到的方法参数名：arg0,arg1,arg2...
                    request.getParameter(parameterName);将无法获取参数
                    java8以后的解决方法（ideal）:
                        File->Settings->Build,Execution,Deployment->Compiler->java Compiler->Additional command line parameters
                        添加:-parameters
                     */
                    Parameter[] parameters = method.getParameters();
                    //1-2.parameterValues 用来承载参数的值
                    Object[] parameterValues = new Object[parameters.length];
                    for (int i = 0; i < parameters.length; i++) {
                        Parameter parameter = parameters[i];
                        String parameterName = parameter.getName() ;
                        //如果参数名是request,response,session 那么就不是通过请求中获取参数的方式了
                        if("request".equals(parameterName)){
                            parameterValues[i] = request ;
                        }else if("response".equals(parameterName)){
                            parameterValues[i] = response ;
                        }else if("session".equals(parameterName)){
                            parameterValues[i] = request.getSession() ;
                        }else{
                            //从请求中获取参数值
                            String parameterValue = request.getParameter(parameterName);
                            String typeName = parameter.getType().getName();

                            Object parameterObj = parameterValue ;

                            if(parameterObj!=null) {
                                if ("java.lang.Integer".equals(typeName)) {
                                    parameterObj = Integer.parseInt(parameterValue);
                                }
                            }

                            parameterValues[i] = parameterObj ;
                        }
                    }

                    //输出operate,请求类型和url
                    System.out.println("operate:" + operate+"\t"+request.getMethod()+":"+request.getServletPath());

                    //2.controller组件中的方法调用
                    method.setAccessible(true);
                    Object returnObj = method.invoke(controllerBeanObj,parameterValues);

                    System.out.println("return:"+returnObj);
                    //3.视图处理
                    String methodReturnStr = (String)returnObj ;
                    if(methodReturnStr.startsWith("redirect:")){        //比如：  redirect:fruit.do
                        String redirectStr = methodReturnStr.substring("redirect:".length());
                        response.sendRedirect(redirectStr);
                    }else{
                        super.processTemplate(methodReturnStr,request,response);    // 比如：  "edit"
                    }
                }
            }

            /*
            }else{
                throw new RuntimeException("operate值非法!");
            }
            */
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

// 常见错误： IllegalArgumentException: argument type mismatch