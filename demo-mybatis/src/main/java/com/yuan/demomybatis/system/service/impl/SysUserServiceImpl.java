package com.yuan.demomybatis.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuan.demomybatis.commons.service.impl.BaseServiceImpl;
import com.yuan.demomybatis.system.dao.SysUserMapper;
import com.yuan.demomybatis.system.dto.SysUserDto;
import com.yuan.demomybatis.system.pojo.SysUser;
import com.yuan.demomybatis.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser, String, SysUserMapper> implements SysUserService {
    private final SysUserMapper sysUserMapper;

    @Autowired
    public SysUserServiceImpl(SysUserMapper sysUserMapper) {
        this.sysUserMapper = sysUserMapper;
    }

    @Override
    public SysUserMapper getMapper() {
        return sysUserMapper;
    }

    @Override
    public PageInfo<SysUser> selectPage(SysUserDto dto) {
        PageHelper.startPage(dto.getPage(), dto.getSize());
        return new PageInfo<>(sysUserMapper.selectList(dto));
    }

    @Override
    public boolean checkInsert(SysUser user) {
        return sysUserMapper.checkInsert(user) > 0;
    }
}
