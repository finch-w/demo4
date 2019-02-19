package com.yuan.demomybatis.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuan.demomybatis.commons.service.impl.BaseServiceImpl;
import com.yuan.demomybatis.system.dao.SysRoleMapper;
import com.yuan.demomybatis.system.dto.SysRoleDto;
import com.yuan.demomybatis.system.pojo.SysRole;
import com.yuan.demomybatis.system.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole, String, SysRoleMapper> implements SysRoleService {
    private final SysRoleMapper sysRoleMapper;

    @Autowired
    public SysRoleServiceImpl(SysRoleMapper sysRoleMapper) {
        this.sysRoleMapper = sysRoleMapper;
    }

    @Override
    public SysRoleMapper getMapper() {
        return sysRoleMapper;
    }

    @Override
    public PageInfo<SysRole> selectPage(SysRoleDto dto) {
        PageHelper.startPage(dto.getPage(), dto.getSize());
        return new PageInfo<>(sysRoleMapper.selectList(dto));
    }

    @Override
    public List<SysRole> selectList(SysRoleDto dto) {
        return sysRoleMapper.selectList(dto);
    }

    @Override
    public SysRole selectOne(SysRoleDto dto) {
        return sysRoleMapper.selectOneOne(dto);
    }
}

