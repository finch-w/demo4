package com.yuan.demojpa.system.repository;

import com.yuan.demojpa.commons.repository.BaseRespository;
import com.yuan.demojpa.system.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends BaseRespository<User, String> {
}
