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
    public Page<User> data(UserDto dto) {
        String sql = "select * from user";
        List<String> conditions = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        return getBaseRepository().findAll(dto, PageRequest.of(dto.getPage(), dto.getSize()));
    }

    @Override
    public Page<User> data2(UserDto userDto) {
        String jpq = "select u from User u";
        return getBaseRepository().findAllByJPQL(jpq, PageRequest.of(userDto.getPage(), userDto.getSize()));
    }

    @Override
    public Page<User> data3(UserDto dto) {
        String jpql = "select u.* from user u";
        List<String> conditons = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        return getBaseRepository().findAllBySQL(jpql, PageRequest.of(dto.getPage(), dto.getSize()));
    }

    @Override
    public List<User> list(UserDto dto) {
        return getBaseRepository().findAll(dto);
    }

    @Override
    public List<User> list2(UserDto dto) {
        String jpql = "select u from User u";
        return getBaseRepository().findAllByJPQL(jpql);
    }

    @Override
    public List<User> list3(UserDto dto) {
        String sql = "select * from user";
        return getBaseRepository().findAllBySQL(sql);
    }
}
