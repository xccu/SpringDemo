package com.example.demo.controller;

import com.example.demo.model.User;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss ");

    /**
     * 获取所有user，以json形式返回
     * url:"http://localhost:8080/user/all"
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public List<User> getAllUser() {
        log.info("getAllUsers");
        return getUserList();
    }

    /**
     * josn格式字符串
     * url:"http://localhost:8080/user/allStr"
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
     * url:"http://localhost:8080/user/allJson"
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/allJson")
    public JSONObject getAllUserJson(){

        Date d = new Date();
        log.info(sdf.format(d)+":allJson");
        List<User> users= getUserList();
        //在传入中，为了满足Layui的格式要求，加上了一些头部：code、msg、count、data
        JSONObject json = new JSONObject();
        json.put("msg", "success");
        json.put("code", "0");
        json.put("status", "200");
        json.put("count", users.size());
        json.put("data", users);
        return json;
    }

    /**
     * 带参数的get请求
     * url:"http://localhost:8080/user/getByName?name={name}"
     * @param name
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/getByName")
    public JSONObject getByName(String name){

        Date d = new Date();
        log.info(sdf.format(d)+":getByName");

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


    private List<User> getUserList(){
        List<User> userList = new ArrayList<>();
        User user;

        user = new User();
        user.setId(1);
        user.setName("Weslie");
        user.setAge(12);
        user.setSex("male");
        userList.add(user);

        user = new User();
        user.setId(2);
        user.setName("Wolffy");
        user.setAge(34);
        user.setSex("male");
        userList.add(user);

        user = new User();
        user.setId(3);
        user.setName("Tibbie");
        user.setAge(11);
        user.setSex("female");
        userList.add(user);

        user = new User();
        user.setId(4);
        user.setName("Sparky");
        user.setAge(12);
        user.setSex("male");
        userList.add(user);

        user = new User();
        user.setId(5);
        user.setName("Paddi");
        user.setAge(10);
        user.setSex("male");
        userList.add(user);

        user = new User();
        user.setId(6);
        user.setName("Wilie");
        user.setAge(5);
        user.setSex("male");
        userList.add(user);

        user = new User();
        user.setId(7);
        user.setName("Wolnie");
        user.setAge(33);
        user.setSex("female");
        userList.add(user);


        user = new User();
        user.setId(8);
        user.setName("Weslie");
        user.setAge(12);
        user.setSex("male");
        userList.add(user);

        user = new User();
        user.setId(9);
        user.setName("Wolffy");
        user.setAge(34);
        user.setSex("male");
        userList.add(user);

        user = new User();
        user.setId(10);
        user.setName("Tibbie");
        user.setAge(11);
        user.setSex("female");
        userList.add(user);

        user = new User();
        user.setId(11);
        user.setName("Sparky");
        user.setAge(12);
        user.setSex("male");
        userList.add(user);

        user = new User();
        user.setId(12);
        user.setName("Paddi");
        user.setAge(10);
        user.setSex("male");
        userList.add(user);

        user = new User();
        user.setId(13);
        user.setName("Wilie");
        user.setAge(5);
        user.setSex("male");
        userList.add(user);

        user = new User();
        user.setId(14);
        user.setName("Wolnie");
        user.setAge(33);
        user.setSex("female");
        userList.add(user);

        return userList;
    }
}