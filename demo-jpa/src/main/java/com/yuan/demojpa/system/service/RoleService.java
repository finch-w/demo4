package com.yuan.demojpa.system.service;

import com.yuan.demojpa.system.dto.RoleDto;
import com.yuan.demojpa.system.pojo.Role;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface RoleService {
    void save(Role role);

    void update(Role role);

    void delete(String[] ids);

    void insert(Role role);

    Page<Map<String, Object>> data(RoleDto roleDto);

    List<Map<String, Object>> list(RoleDto roleDto);

    Optional<Role> get(String id);

    Long check(Role role);

    Role get(Role role);
}
