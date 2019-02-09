package com.yuan.demomybatis2.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yuan.demomybatis2.commons.service.BaseService;
import com.yuan.demomybatis2.system.dto.SysUserDto;
import com.yuan.demomybatis2.system.pojo.SysUser;

public interface SysUserService extends BaseService<SysUser, String> {
    IPage<SysUser> selectPage(SysUserDto dto);

    boolean checkInsert(SysUser user);
}
