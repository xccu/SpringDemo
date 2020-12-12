package com.example.demo.controller;

import com.example.demo.common.PageUtil;
import com.example.demo.model.User;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
//@RestController
@Controller
@RequestMapping("/layui/user")
public class UserController {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss ");

    /**
     * http://localhost:8080/layui/user/?id=1
     * @param id
     * @param model
     * @return
     */
    @GetMapping(path = "/")
    public String userEdit(int id,Model model){

        List<User> users= getUserList();
        User user = users.stream().filter(t->t.getId().equals(id)).findFirst().orElse(null);


        model.addAttribute("user", user);

        return "userEdit";
    }

    /**
     * http://localhost:8080/layui/user/detail?id=1
     * @param id
     * @param model
     * @return
     */
    @GetMapping(path = "/detail")
    public String userDetail(int id,Model model){

        List<User> users= getUserList();
        User user = users.stream().filter(t->t.getId().equals(id)).findFirst().orElse(null);


        model.addAttribute("user", user);

        return "userDetail";
    }

    /**
     * josn格式字符串
     * url:"http://localhost:8080/layui/user/allStr"
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/allStr")
    public String getAllUserStr() {
        String result =
                "{\n" +
                "    \"msg\":\"success\",\n" +
                "    \"code\":0,\n" +
                "    \"count\":15,\n" +
                "    \"data\": [\n" +
                "                {\"id\":1,\"name\":\"Weslie\",\"age\":12,\"sex\":\"male\"},\n" +
                "                {\"id\":2,\"name\":\"Wolffy\",\"age\":34,\"sex\":\"male\"},\n" +
                "                {\"id\":3,\"name\":\"Tibbie\",\"age\":11,\"sex\":\"female\"},\n" +
                "                {\"id\":4,\"name\":\"Sparky\",\"age\":12,\"sex\":\"male\"},\n" +
                "                {\"id\":5,\"name\":\"Paddi\",\"age\":10,\"sex\":\"male\"},\n" +
                "                {\"id\":6,\"name\":\"Weslie\",\"age\":12,\"sex\":\"male\"},\n" +
                "                {\"id\":7,\"name\":\"Wolffy\",\"age\":34,\"sex\":\"male\"},\n" +
                "                {\"id\":8,\"name\":\"Tibbie\",\"age\":11,\"sex\":\"female\"},\n" +
                "                {\"id\":9,\"name\":\"Sparky\",\"age\":12,\"sex\":\"male\"},\n" +
                "                {\"id\":10,\"name\":\"Paddi\",\"age\":10,\"sex\":\"male\"}\n" +
                "     ]\n" +
                "}\n";
        return result;
    }

    /**
     * json对象
     * url:"http://localhost:8080/layui/user/all"
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/all")
    public JSONObject getAllUserJson(int page,int limit){

        Date d = new Date();
        log.info(sdf.format(d)+" /all:"+"page:"+page+"\tlimit:"+limit);
        List<User> users= getUserList();
        int count = users.size();
        users = PageUtil.startPage(users,page,limit);
        //在传入中，为了满足Layui的格式要求，加上了一些头部：code、msg、count、data
        JSONObject json = new JSONObject();
        json.put("msg", "success");
        json.put("code", "0");
        json.put("status", "200");
        json.put("count", count);
        json.put("data", users);
        return json;
    }

    /**
     * 带参数的get请求
     * url:"http://localhost:8080/layui/user/getByName?name={name}"
     * @param name
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/getByName")
    public JSONObject getByName(String name){

        Date d = new Date();
        log.info(sdf.format(d)+" /getByName:"+name);

        List<User> users= getUserList();
        users = users.stream().filter(t->t.getName().equals(name)).collect(Collectors.toList());

        //在传入中，为了满足Layui的格式要求，加上了一些头部：code、msg、count、data
        JSONObject json = new JSONObject();
        json.put("msg", "success");
        json.put("code", "0");
        json.put("status", "200");
        json.put("count", users.size());
        json.put("data", users);
        return json;
    }

    @ResponseBody
    @GetMapping(value = "/getById")
    public JSONObject getById(Integer id){

        Date d = new Date();
        log.info(sdf.format(d)+" /getById:"+id);

        List<User> users= getUserList();
        User user = users.stream().filter(t->t.getId().equals(id)).findFirst().orElse(null);

        //在传入中，为了满足Layui的格式要求，加上了一些头部：code、msg、count、data
        JSONObject json = new JSONObject();
        json.put("msg", "success");
        json.put("code", "0");
        json.put("status", "200");
        json.put("flag", true);
        //json.put("count", users.size());
        json.put("data", user);
        return json;
    }


    private List<User> getUserList(){
        List<User> userList = new ArrayList<>();
        User user;

        user = new User();
        user.setId(1);
        user.setName("Weslie");
        user.setAge(12);
        user.setSex("male");
        user.setAvatarUrl("/avatar/weslie.jpg");
        user.setEmail("Weslie@cpe.com");
        userList.add(user);

        user = new User();
        user.setId(2);
        user.setName("Wolffy");
        user.setAge(34);
        user.setSex("male");
        user.setAvatarUrl("/avatar/wolffy.jpg");
        user.setEmail("wolffy@cpe.com");
        userList.add(user);

        user = new User();
        user.setId(3);
        user.setName("Tibbie");
        user.setAge(11);
        user.setSex("female");
        user.setAvatarUrl("/avatar/tibbie.jpg");
        user.setEmail("tibbie@cpe.com");
        userList.add(user);

        user = new User();
        user.setId(4);
        user.setName("Sparky");
        user.setAge(12);
        user.setSex("male");
        user.setAvatarUrl("/avatar/sparky.jpg");
        user.setEmail("sparky@cpe.com");
        userList.add(user);

        user = new User();
        user.setId(5);
        user.setName("Paddi");
        user.setAge(10);
        user.setSex("male");
        user.setAvatarUrl("/avatar/paddi.jpg");
        user.setEmail("paddi@cpe.com");
        userList.add(user);

        user = new User();
        user.setId(6);
        user.setName("Jonie");
        user.setAge(12);
        user.setSex("female");
        user.setAvatarUrl("/avatar/jonie.jpg");
        user.setEmail("jonie@cpe.com");
        userList.add(user);

        user = new User();
        user.setId(7);
        user.setName("Slowy");
        user.setAge(70);
        user.setSex("male");
        user.setAvatarUrl("/avatar/slowy.jpg");
        user.setEmail("slowy@cpe.com");
        userList.add(user);

        user = new User();
        user.setId(8);
        user.setName("Wolnie");
        user.setAge(33);
        user.setSex("female");
        user.setAvatarUrl("/avatar/wolnie.jpg");
        user.setEmail("wolnie@cpe.com");
        userList.add(user);

        user = new User();
        user.setId(9);
        user.setName("Wilie");
        user.setAge(5);
        user.setSex("male");
        user.setAvatarUrl("/avatar/wilie.jpg");
        user.setEmail("wilie@cpe.com");
        userList.add(user);


        user = new User();
        user.setId(10);
        user.setName("Weslie");
        user.setAge(12);
        user.setSex("male");
        user.setAvatarUrl("/avatar/weslie.jpg");
        user.setEmail("weslie@cpe.com");
        userList.add(user);

        user = new User();
        user.setId(11);
        user.setName("Wolffy");
        user.setAge(34);
        user.setSex("male");
        user.setAvatarUrl("/avatar/wolffy.jpg");
        user.setEmail("wolffy@cpe.com");
        userList.add(user);

        user = new User();
        user.setId(12);
        user.setName("Tibbie");
        user.setAge(11);
        user.setSex("female");
        user.setAvatarUrl("/avatar/tibbie.jpg");
        user.setEmail("tibbie@cpe.com");
        userList.add(user);

        user = new User();
        user.setId(13);
        user.setName("Sparky");
        user.setAge(12);
        user.setSex("male");
        user.setAvatarUrl("/avatar/sparky.jpg");
        user.setEmail("sparky@cpe.com");
        userList.add(user);

        user = new User();
        user.setId(14);
        user.setName("Paddi");
        user.setAge(10);
        user.setSex("male");
        user.setAvatarUrl("/avatar/paddi.jpg");
        user.setEmail("paddi@cpe.com");
        userList.add(user);

        user = new User();
        user.setId(15);
        user.setName("Jonie");
        user.setAge(12);
        user.setSex("female");
        user.setAvatarUrl("/avatar/jonie.jpg");
        user.setEmail("jonie@cpe.com");
        userList.add(user);

        user = new User();
        user.setId(16);
        user.setName("Slowy");
        user.setAge(70);
        user.setSex("male");
        user.setAvatarUrl("/avatar/slowy.jpg");
        user.setEmail("slowy@cpe.com");
        userList.add(user);

        user = new User();
        user.setId(17);
        user.setName("Wolnie");
        user.setAge(33);
        user.setSex("female");
        user.setAvatarUrl("/avatar/wolnie.jpg");
        userList.add(user);

        user = new User();
        user.setId(18);
        user.setName("Wilie");
        user.setAge(5);
        user.setSex("male");
        user.setAvatarUrl("/avatar/wilie.jpg");
        user.setEmail("wilie@cpe.com");
        userList.add(user);


        return userList;
    }
}