package com.yuan.demojpa.system.dao;

import com.yuan.demojpa.commons.dao.BaseRepository;
import com.yuan.demojpa.system.pojo.SysRole;
import org.springframework.stereotype.Repository;

@Repository
public interface SysRoleDao extends BaseRepository<SysRole, String> {
}
