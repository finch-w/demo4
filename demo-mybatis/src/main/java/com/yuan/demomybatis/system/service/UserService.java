package com.yuan.demomybatis.system.service;

import com.github.pagehelper.PageInfo;
import com.yuan.demomybatis.commons.service.BaseService;
import com.yuan.demomybatis.system.dto.SysUserDto;
import com.yuan.demomybatis.system.pojo.SysUser;

import java.util.List;

public interface UserService extends BaseService<SysUser, String> {

    List<SysUser> findAllByDto(SysUserDto dto);

    PageInfo<SysUser> findAllByDtoPage(SysUserDto dto);

    Integer check(SysUser user);
}
