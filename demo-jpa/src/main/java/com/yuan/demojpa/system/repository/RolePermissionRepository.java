package com.yuan.demojpa.system.repository;

import com.yuan.demojpa.commons.dao.BaseRepository;
import com.yuan.demojpa.system.pojo.RolePermission;
import org.springframework.stereotype.Repository;

@Repository
public interface RolePermissionRepository extends BaseRepository<RolePermission, String> {
}
