package com.yuan.demomybatis.system.service;

import com.github.pagehelper.PageInfo;
import com.yuan.demomybatis.commons.service.BaseService;
import com.yuan.demomybatis.system.dto.SysRoleDto;
import com.yuan.demomybatis.system.pojo.SysRole;

import java.util.List;

public interface SysRoleService extends BaseService<SysRole, String> {
    PageInfo<SysRole> selectPage(SysRoleDto dto);

    List<SysRole> selectList(SysRoleDto dto);

    SysRole selectOne(SysRoleDto dto);
}
