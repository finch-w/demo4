package com.yuan.demojpa.system.service;

import com.yuan.demojpa.commons.service.BaseService;
import com.yuan.demojpa.system.dto.PermissionDto;
import com.yuan.demojpa.system.pojo.Permission;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PermissionService extends BaseService<Permission, String> {
    Page<Permission> data(PermissionDto dto);

    Page<Permission> data2(PermissionDto dto);

    Page<Permission> data3(PermissionDto dto);

    List<Permission> list(PermissionDto dto);

    List<Permission> list2(PermissionDto dto);

    List<Permission> list3(PermissionDto dto);
}
