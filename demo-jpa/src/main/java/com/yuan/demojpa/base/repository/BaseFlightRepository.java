package com.yuan.demojpa.base.repository;

import com.yuan.demojpa.base.pojo.BaseFlight;
import com.yuan.demojpa.commons.dao.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseFlightRepository extends BaseRepository<BaseFlight, String> {
}
