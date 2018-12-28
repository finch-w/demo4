package com.yuan.demojpa.system.dao;

import com.yuan.demojpa.commons.dao.BaseDao;
import com.yuan.demojpa.system.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends BaseDao<User, String> {
}
