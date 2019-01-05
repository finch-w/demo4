package com.yuan.demojpa.base.repository;

import com.yuan.demojpa.base.pojo.BaseCustomer;
import com.yuan.demojpa.commons.dao.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseCustomerRepository extends BaseRepository<BaseCustomer, String> {
}
