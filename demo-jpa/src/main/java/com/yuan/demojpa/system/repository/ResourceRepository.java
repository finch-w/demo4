package com.yuan.demojpa.system.repository;

import com.yuan.demojpa.commons.dao.BaseRepository;
import com.yuan.demojpa.system.pojo.Resource;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRepository extends BaseRepository<Resource, String> {
}
