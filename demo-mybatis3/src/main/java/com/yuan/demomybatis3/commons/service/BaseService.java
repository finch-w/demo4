package com.yuan.demomybatis3.commons.service;

import com.yuan.demomybatis3.commons.pojo.BasePojo;

import java.io.Serializable;

public interface BaseService<T extends BasePojo, Id extends Serializable> {
    void save(T t);

    void insert(T t);

    void update(T t);

    @SuppressWarnings({"unchecked"})
    void delete(Id... ids);

    T get(Id id);


}
