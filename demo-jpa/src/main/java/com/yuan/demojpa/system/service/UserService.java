package com.yuan.demojpa.system.service;

import com.yuan.demojpa.commons.service.BaseService;
import com.yuan.demojpa.system.dto.UserDto;
import com.yuan.demojpa.system.pojo.SysUser;
import org.springframework.data.domain.Page;

public interface UserService extends BaseService<SysUser, String> {
    Page<SysUser> findPage(UserDto dto);

    Long check(SysUser user);
}
