package com.yuan.demojpa.system.service.impl;

import com.yuan.demojpa.commons.dto.MapQuery;
import com.yuan.demojpa.commons.service.impl.BaseServiceImpl;
import com.yuan.demojpa.system.dao.UserDao;
import com.yuan.demojpa.system.dto.UserDto;
import com.yuan.demojpa.system.pojo.SysUser;
import com.yuan.demojpa.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<SysUser, String, UserDao> implements UserService {
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDao getBaseRepository() {
        return userDao;
    }

    public Page<SysUser> page(UserDto dto) {
        MapQuery mapQuery = dto.getDtoSQL();
        return getBaseRepository().findAllBySQL(mapQuery.getSql(), PageRequest.of(dto.getPage(), dto.getSize()), mapQuery.getMap());
    }
}