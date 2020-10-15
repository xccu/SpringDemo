package com.example.demo.controller;

import com.example.demo.controller.request.UserRequest;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 获取所有user，以json形式返回
     * url:"http://localhost:8080/user/all"
     * @return
     */
    @GetMapping(value = "/all")
    @ResponseBody
    public List<User> getAllUsers() {
        log.info("getAllUsers");
        return userService.getAll();
    }

    /**
     * http://localhost:8080/user/1
     * 通过url参数请求
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ResponseBody
    public User getUserById(@PathVariable("id") Integer id) {
        log.info("getUserById/"+id);
        return userService.getById(id);
    }

    /**
     * http://localhost:8080/user/
     * 通过param参数请求(默认返回json)
     * @param name
     * @return
     */
    @GetMapping(path = "/", params = "name")
    @ResponseBody
    public User getUserByName(@RequestParam String name) {
        log.info("getUserByName/"+name);
        return userService.getByName(name);
    }

    /**
     * http://localhost:8080/user/
     * 通过param参数请求并返回xml
     * produces指定指定返回的内容类型为 APPLICATION_XML_VALUE（XML）
     * @param id
     * @return
     */
    @RequestMapping(path = "/", method = RequestMethod.GET, params = "id",
            produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public User getUserByIdXml(@RequestParam Integer id) {
        log.info("getUserById/"+id);
        return userService.getById(id);
    }

    /**
     * http://localhost:8080/user/add
     * 传入UserRequest验证对象，自定义错误请求处理
     * consumes指定处理请求的提交内容类型为 APPLICATION_FORM_URLENCODED_VALUE
     * BindingResult记录错误信息
     * @param userRequest
     * @param result
     * @return
     */
    @PostMapping(path = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@Valid UserRequest userRequest, BindingResult result) {
        log.info("addUser");
        //请求出现错误时返回null
        if (result.hasErrors()) {
            // 这里先简单处理一下，后续讲到异常处理时会改
            log.warn("Binding Errors: {}", result);
            return null;
        }
        return userService.getByName(userRequest.getName());
    }

    /**
     * http://localhost:8080/user/add
     * 传入UserRequest验证对象，自定义错误请求处理
     * consumes指定处理请求的提交内容类型为 MULTIPART_FORM_DATA_VALUE(表单)
     * BindingResult记录错误信息
     * @param userRequest
     * @param result
     * @return
     */
    @PostMapping(path = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public User addUserForm(@Valid UserRequest userRequest, BindingResult result) {
        log.info("addUserForm");
        //请求出现错误时返回null
        if (result.hasErrors()) {
            // 这里先简单处理一下，后续讲到异常处理时会改
            log.warn("Binding Errors: {}", result);
            return null;
        }
        return userService.getByName(userRequest.getName());
    }

    /**
     * http://localhost:8080/user/
     * produces指定指定返回的内容类型为 APPLICATION_JSON_UTF8_VALUE（UTF8格式json）
     * consumes指定处理请求的提交内容类型为 APPLICATION_JSON_VALUE
     * @param userRequest
     * @return
     */
    @PostMapping(path = "/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public User addUserJson(@RequestBody UserRequest userRequest) {
        log.info("addUserJson");
        log.info("Receive new userRequest {}", userRequest);
        User user = userService.getById(userRequest.getId());
        return user;
    }

    /**
     * http://localhost:8080/upload
     * 文件上传
     * consumes使用MULTIPART_FORM_DATA_VALUE，接收文件类型
     * @param file
     * @return
     */
    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public List<User> Upload(@RequestParam("file") MultipartFile file) {
        log.info("upload");
        List<User> Users = new ArrayList<>();
        if (!file.isEmpty()) {
            BufferedReader reader = null;
            try {
                //流读取文件
                reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
                String str;
                //逐行读取
                while ((str = reader.readLine()) != null) {
                    String[] arr = StringUtils.split(str, ";");
                    if (arr != null && arr.length > 0) {
                        Users.add(userService.getByName(arr[0]));
                    }
                }
            } catch (IOException e) {
                log.error("exception", e);
            } finally {
                IOUtils.closeQuietly(reader);
            }
        }
        return Users;
    }


    @PostMapping(path = "/download", produces  = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity <byte[]> download() throws IOException {
        File file = new File("D://WorkSpace//Git//SpringDemo//springmvc-request-demo//src//main//resources//Users.txt");
        //File file = new File("Users.txt");
        byte[] body = null;
        InputStream is = new FileInputStream(file);
        body = new byte[is.available()];
        is.read(body);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attchement;filename=" + file.getName());
        HttpStatus statusCode = HttpStatus.OK;
        ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(body, headers, statusCode);
        return entity;
    }
}