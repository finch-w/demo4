package com.yuan.demojpa.commons.service;


import com.yuan.demojpa.commons.pojo.BasePojo;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BaseService<T extends BasePojo, ID extends Serializable> {
    T save(T t);

    void insert(T t);

    void update(T t);

    void delete(ID... ids);

    Optional<T> getById(ID id);

    Optional<T> getByExample(T t);

    List<T> listByExample(T t);

    boolean exist(T t);

    long count(T t);
}
