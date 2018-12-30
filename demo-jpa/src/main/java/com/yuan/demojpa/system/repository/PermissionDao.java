package com.yuan.demojpa.system.repository;

import com.yuan.demojpa.commons.repository.BaseRespository;
import com.yuan.demojpa.system.pojo.Permission;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionDao extends BaseRespository<Permission, String> {
}
