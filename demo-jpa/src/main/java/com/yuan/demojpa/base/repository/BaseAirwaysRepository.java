package com.yuan.demojpa.base.repository;

import com.yuan.demojpa.base.pojo.BaseAirways;
import com.yuan.demojpa.commons.dao.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface BaseAirwaysRepository extends BaseRepository<BaseAirways, String> {
    List<BaseAirways> findByIdIn(Collection<String> id);
}
