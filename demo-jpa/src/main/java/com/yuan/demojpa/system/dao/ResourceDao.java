package com.yuan.demojpa.system.dao;

import com.yuan.demojpa.commons.dao.BaseRepository;
import com.yuan.demojpa.system.pojo.Resource;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceDao extends BaseRepository<Resource, String> {
}
