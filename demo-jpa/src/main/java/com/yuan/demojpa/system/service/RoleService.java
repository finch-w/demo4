package com.yuan.demojpa.system.service;

import com.yuan.demojpa.commons.service.BaseService;
import com.yuan.demojpa.system.dto.RoleDto;
import com.yuan.demojpa.system.pojo.SysRole;
import org.springframework.data.domain.Page;

public interface RoleService extends BaseService<SysRole, String> {
    Long check(SysRole role);

    Page<SysRole> selectPage(RoleDto dto);
}
