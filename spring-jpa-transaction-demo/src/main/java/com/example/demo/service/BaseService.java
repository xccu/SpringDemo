package com.example.demo.service;

import com.example.demo.domain.User;

public interface BaseService {
    User saveUserWithRollback(User user);
    User saveUserWithoutRollback(User user);
}
