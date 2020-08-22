package web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller//1.注解声明为一个控制器
public class HomeController {

    //2.利用@RequestMapping配置url和方法之间的映射
    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }
}
