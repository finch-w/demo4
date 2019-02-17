package com.yuan.demojpa.system.service;

import com.yuan.demojpa.commons.service.BaseService;
import com.yuan.demojpa.system.dto.SysRoleDto;
import com.yuan.demojpa.system.pojo.SysRole;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface SysRoleService extends BaseService<SysRole, String> {
    Page<SysRole> selectPage(SysRoleDto dto);

    List<SysRole> selectList(SysRoleDto dto);

    Optional<SysRole> selectOne(SysRoleDto dto);

    Page<SysRole> selectPageSQL(SysRoleDto dto);

    List<SysRole> selectListSQL(SysRoleDto dto);

    Optional<SysRole> selectOneSQL(SysRoleDto dto);
}
