package com.yuan.demojpa.system.dao;

import com.yuan.demojpa.commons.dao.BaseDao;
import com.yuan.demojpa.system.pojo.Permission;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionDao extends BaseDao<Permission,String> {
}
