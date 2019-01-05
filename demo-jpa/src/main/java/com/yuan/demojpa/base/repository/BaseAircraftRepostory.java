package com.yuan.demojpa.base.repository;

import com.yuan.demojpa.base.pojo.BaseAircraft;
import com.yuan.demojpa.commons.dao.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseAircraftRepostory extends BaseRepository<BaseAircraft, String> {
}
