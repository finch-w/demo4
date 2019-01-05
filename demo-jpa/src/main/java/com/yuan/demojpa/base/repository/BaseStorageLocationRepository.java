package com.yuan.demojpa.base.repository;

import com.yuan.demojpa.base.pojo.BaseStorageLocation;
import com.yuan.demojpa.commons.dao.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseStorageLocationRepository extends BaseRepository<BaseStorageLocation, String> {
}
