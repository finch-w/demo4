package com.yuan.demojpa.system.service;

import com.yuan.demojpa.system.dto.PermissionDto;
import com.yuan.demojpa.system.pojo.Permission;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface PermissionService {
    void insert(Permission permission);

    @Transactional
    void update(Permission permission);

    void delete(String[] ids);

    Long check(Permission permission);

    Optional<Permission> get(String id);

    Permission get(Permission permission);

    Page<Map<String, Object>> data(PermissionDto permissionDto);

    List<Map<String, Object>> list(PermissionDto permissionDto);


}
