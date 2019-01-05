package com.yuan.demojpa.base.repository;

import com.yuan.demojpa.base.pojo.BaseStorage;
import com.yuan.demojpa.commons.dao.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseStorageRepository extends BaseRepository<BaseStorage, String> {
}
