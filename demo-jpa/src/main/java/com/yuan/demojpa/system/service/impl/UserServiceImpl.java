package com.yuan.demojpa.system.service.impl;

import com.yuan.demojpa.commons.service.impl.BaseServiceImpl;
import com.yuan.demojpa.system.dao.UserDao;
import com.yuan.demojpa.system.pojo.User;
import com.yuan.demojpa.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, String, UserDao> implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserDao getBaseRepository() {
        return userDao;
    }


}
