package com.yuan.demomybatis.system.service;

import com.github.pagehelper.PageInfo;
import com.yuan.demomybatis.commons.service.BaseService;
import com.yuan.demomybatis.system.dto.SysRoleDto;
import com.yuan.demomybatis.system.pojo.SysRole;

public interface RoleService extends BaseService<SysRole, String> {
    PageInfo<SysRole> selectPage(SysRoleDto dto);
}
