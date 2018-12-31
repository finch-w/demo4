package com.yuan.demojpa.system.repository;

import com.yuan.demojpa.commons.dao.BaseRepository;
import com.yuan.demojpa.system.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User, String> {
    User findByName(String name);
}
