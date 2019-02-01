package com.yuan.demomybatis2.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yuan.demomybatis2.commons.service.BaseService;
import com.yuan.demomybatis2.system.dto.SysRoleDto;
import com.yuan.demomybatis2.system.pojo.SysRole;


public interface RoleService extends BaseService<SysRole, String> {
    IPage<SysRole> selectPage(SysRoleDto dto);
}
