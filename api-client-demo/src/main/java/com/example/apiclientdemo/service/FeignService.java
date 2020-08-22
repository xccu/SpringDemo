package com.example.apiclientdemo.service;

import com.example.apiclientdemo.model.User;
import feign.Param;
import feign.RequestLine;


import java.util.List;

public interface FeignService {

    @RequestLine("GET /user/getall")
    List<User> getAllUsers();

    @RequestLine("GET /user/getbyid/{id}")
    User getUserByID(@Param("id") Integer id);

    @RequestLine("POST /user/adduser")
    void postUser(User user);
}
