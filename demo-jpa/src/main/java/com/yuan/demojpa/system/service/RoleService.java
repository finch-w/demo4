package com.yuan.demojpa.system.service;

import com.yuan.demojpa.commons.service.BaseService;
import com.yuan.demojpa.system.dto.RoleDto;
import com.yuan.demojpa.system.pojo.Role;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RoleService extends BaseService<Role, String> {
    Page<Role> data(RoleDto dto);

    Page<Role> data2(RoleDto dto);

    Page<Role> data3(RoleDto dto);

    List<Role> list(RoleDto dto);

    List<Role> list2(RoleDto dto);

    List<Role> list3(RoleDto dto);
}
