package com.yuan.demomybatis.service.impl;

import com.yuan.demomybatis.mapper.UserMapper;
import com.yuan.demomybatis.pojo.User;
import com.yuan.demomybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Transactional
    public void insert(User user) {
        userMapper.insert(user);
    }

    @Transactional
    public void insert(List<User> users) {
        Integer integer = users.stream().map(userMapper::insert).reduce(Integer::sum).orElse(0);
    }

    public void update(User user) {

    }

    @Transactional
    public void update(List<User> users) {
        Integer integer = users.stream().map(userMapper::updateByPrimaryKey).reduce(Integer::sum).orElse(0);
    }

    @Transactional
    public void delete(Long... ids) {
        Integer integer = Arrays.stream(ids).map(userMapper::deleteByPrimaryKey).reduce(Integer::sum).orElse(0);
    }

    public User get(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public List<User> select(){
        return userMapper.selectAll();
    }

    public List<User> select(User user){
        return userMapper.select(user);
    }

    public List<User> select(String name){
        Example.Builder builder = new Example.Builder(User.class);
        builder.andWhere(Sqls.custom().andLike("name",name+"%"));
        return userMapper.selectByExample(builder.build());
    }

}
