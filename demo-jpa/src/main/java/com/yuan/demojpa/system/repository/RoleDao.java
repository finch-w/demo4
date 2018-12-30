package com.yuan.demojpa.system.repository;

import com.yuan.demojpa.commons.repository.BaseRespository;
import com.yuan.demojpa.system.pojo.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends BaseRespository<Role, String> {
}
