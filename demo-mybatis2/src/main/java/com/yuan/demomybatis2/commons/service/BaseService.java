package com.yuan.demomybatis2.commons.service;

import com.yuan.demomybatis2.commons.pojo.BasePojo;

import java.io.Serializable;

public interface BaseService<T extends BasePojo, ID extends Serializable> {
    int insert(T t);

    int update(T t);

    int save(T t);

    int delete(ID... ids);

    T getById(ID id);
}
