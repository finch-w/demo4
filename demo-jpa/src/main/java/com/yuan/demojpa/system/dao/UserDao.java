package com.yuan.demojpa.system.dao;

import com.yuan.demojpa.commons.dao.BaseRepository;
import com.yuan.demojpa.system.pojo.SysUser;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends BaseRepository<SysUser, String> {

}
