package com.yuan.demojpa.system.dao;

import com.yuan.demojpa.commons.dao.BaseRepository;
import com.yuan.demojpa.system.pojo.Log;
import org.springframework.stereotype.Repository;

@Repository
public interface LogDao extends BaseRepository<Log, String> {
}
