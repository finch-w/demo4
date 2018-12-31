package com.yuan.demojpa.system.service.impl;

import com.yuan.demojpa.commons.service.impl.BaseServiceImpl;
import com.yuan.demojpa.system.dto.UserDto;
import com.yuan.demojpa.system.pojo.User;
import com.yuan.demojpa.system.repository.UserRepository;
import com.yuan.demojpa.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, String, UserRepository> implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserRepository getBaseRepository() {
        return userRepository;
    }

    @Override
    public Page<Map> data(UserDto dto) {
        String sql = "select * from user";
        List<String> conditions = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        return getBaseRepository().pageBySQLInMap(sql, PageRequest.of(dto.getPage(), dto.getSize()));
    }

    @Override
    public Page<User> data2(UserDto userDto) {
        return getBaseRepository().findAll(userDto, PageRequest.of(userDto.getPage(), userDto.getSize()));
    }
}
