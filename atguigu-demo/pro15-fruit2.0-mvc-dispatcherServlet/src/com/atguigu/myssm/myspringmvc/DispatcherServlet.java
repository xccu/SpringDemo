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
import java.util.HashMap;
import java.util.Map;

//中央控制器类
//*.do表示匹配所有以.do结尾的请求
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {

    private Map<String,Object> beanMap = new HashMap<>();

    public DispatcherServlet() {

    }

    //解析加载applicationContext.xml配置文件
    public void init(){
        try {

            System.out.println("DispatcherServlet init config...");
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
                    Object beanObj = controllerBeanClass.newInstance();

                    //反射调用setServletContext设置Servlet上下文
                    Method setServletContextMethod = controllerBeanClass.getDeclaredMethod("setServletContext",ServletContext.class);
                    setServletContextMethod.invoke(beanObj , this.getServletContext());

                    //System.out.println("bean:" +className);
                    //bean实例写入map
                    beanMap.put(beanId , beanObj) ;
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
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
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

        //得到bean实例
        Object controllerBeanObj = beanMap.get(servletPath);


        String operate = request.getParameter("operate");
        if(StringUtil.isEmpty(operate)){
            operate = "index" ;
        }

        try {

            //输出operate,请求类型和url
            System.out.println("operate:" + operate+"\t"+request.getMethod()+":"+request.getServletPath());

            //通过方法名称获取方法
            Method method = controllerBeanObj.getClass().getDeclaredMethod(operate,HttpServletRequest.class,HttpServletResponse.class);
            if(method!=null){
                method.setAccessible(true);
                method.invoke(controllerBeanObj,request,response);
            }else{
                throw new RuntimeException("operate值非法!");
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
