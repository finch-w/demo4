package com.yuan.demomybatis2.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuan.demomybatis2.commons.service.impl.BaseServiceImpl;
import com.yuan.demomybatis2.system.dao.SysUserMapper;
import com.yuan.demomybatis2.system.dto.SysUserDto;
import com.yuan.demomybatis2.system.pojo.SysUser;
import com.yuan.demomybatis2.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser, String, SysUserMapper> implements SysUserService {
    private final SysUserMapper userMapper;

    @Autowired
    public SysUserServiceImpl(SysUserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public SysUserMapper getBaseMapper() {
        return userMapper;
    }

    @Override
    public IPage<SysUser> selectPage(SysUserDto dto) {
        return userMapper.selectPage(new Page(dto.getPage(), dto.getSize()), dto);
    }

    @Override
    public boolean checkInsert(SysUser user) {
        return userMapper.checkInsert(user) > 0;
    }
}
