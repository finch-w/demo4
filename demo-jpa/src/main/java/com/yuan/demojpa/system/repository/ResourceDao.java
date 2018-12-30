package com.yuan.demojpa.system.repository;

import com.yuan.demojpa.commons.repository.BaseRespository;
import com.yuan.demojpa.system.pojo.Resource;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceDao extends BaseRespository<Resource, String> {
}
