package com.yuan.demomybatis2.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuan.demomybatis2.commons.service.impl.BaseServiceImpl;
import com.yuan.demomybatis2.system.dao.UserMapper;
import com.yuan.demomybatis2.system.dto.SysUserDto;
import com.yuan.demomybatis2.system.pojo.SysUser;
import com.yuan.demomybatis2.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<SysUser, String, UserMapper> implements UserService {
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserMapper getBaseMapper() {
        return userMapper;
    }

    @Override
    public IPage<SysUser> selectPage(SysUserDto dto) {
        return getBaseMapper().selectPage(new Page(dto.getPage(), dto.getSize()), dto);
    }
}
