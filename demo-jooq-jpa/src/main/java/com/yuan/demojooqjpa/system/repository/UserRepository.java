package com.yuan.demojooqjpa.system.repository;

import com.yuan.demojooqjpa.commons.dao.BaseRepository;
import com.yuan.demojooqjpa.system.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User, String> {
}
