package com.yuan.demojooqjpa.system.service.impl;

import com.yuan.demojooqjpa.commons.service.impl.BaseServiceImpl;
import com.yuan.demojooqjpa.system.pojo.User;
import com.yuan.demojooqjpa.system.repository.UserRepository;
import com.yuan.demojooqjpa.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, String, UserRepository> implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserRepository getBaseRepository() {
        return userRepository;
    }
}
