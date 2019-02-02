package com.yuan.demomybatis2.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuan.demomybatis2.commons.service.impl.BaseServiceImpl;
import com.yuan.demomybatis2.system.dao.RoleMapper;
import com.yuan.demomybatis2.system.dto.SysRoleDto;
import com.yuan.demomybatis2.system.pojo.SysRole;
import com.yuan.demomybatis2.system.service.RoleService;
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
    public RoleMapper getBaseMapper() {
        return roleMapper;
    }

    @Override
    public IPage<SysRole> selectPage(SysRoleDto dto) {
        return getBaseMapper().selectPage(new Page(dto.getPage(), dto.getSize()), dto);
    }

    @Override
    public Integer check(SysRole role) {
        return getBaseMapper().check(role);
    }
}
