package com.yuan.demojpa2.system.dao;

import com.yuan.demojpa2.commons.dao.BaseDao;
import com.yuan.demojpa2.system.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends BaseDao<User, String> {
}
