package com.yuan.demomybatis.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuan.demomybatis.commons.service.impl.BaseServiceImpl;
import com.yuan.demomybatis.system.dao.RoleMapper;
import com.yuan.demomybatis.system.dto.SysRoleDto;
import com.yuan.demomybatis.system.pojo.SysRole;
import com.yuan.demomybatis.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends BaseServiceImpl<SysRole, String, RoleMapper> implements RoleService {
    private final RoleMapper roleMapper;

    @Autowired
    public RoleServiceImpl(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public RoleMapper getMapper() {
        return roleMapper;
    }

    @Override
    public PageInfo<SysRole> selectPage(SysRoleDto dto) {
        PageHelper.startPage(dto.getPage(), dto.getSize());
        return new PageInfo<>(getMapper().selectList(dto));
    }
}
