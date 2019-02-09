package com.yuan.demojpa.system.service;

import com.yuan.demojpa.commons.service.BaseService;
import com.yuan.demojpa.system.dto.SysUserDto;
import com.yuan.demojpa.system.pojo.SysUser;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SysUserService extends BaseService<SysUser, String> {
    Page<SysUser> selectPage(SysUserDto dto);

    List<SysUser> selectList(SysUserDto dto);

    boolean checkInsert(SysUser user);
}
