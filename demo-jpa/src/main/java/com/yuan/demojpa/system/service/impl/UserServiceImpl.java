package com.yuan.demojpa.system.service.impl;

import com.yuan.demojpa.system.repository.UserDao;
import com.yuan.demojpa.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;


}
