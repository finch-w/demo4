package com.yuan.demojpa2.system.dao;

import com.yuan.demojpa2.commons.dao.BaseRepository;
import com.yuan.demojpa2.system.pojo.SysUser;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserDao extends BaseRepository<SysUser, String> {
}
