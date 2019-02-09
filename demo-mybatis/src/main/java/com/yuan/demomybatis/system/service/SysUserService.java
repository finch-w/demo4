package com.yuan.demomybatis.system.service;

import com.github.pagehelper.PageInfo;
import com.yuan.demomybatis.commons.service.BaseService;
import com.yuan.demomybatis.system.dto.SysUserDto;
import com.yuan.demomybatis.system.pojo.SysUser;

public interface SysUserService extends BaseService<SysUser, String> {
    PageInfo<SysUser> selectPage(SysUserDto dto);

    boolean checkInsert(SysUser user);
}
