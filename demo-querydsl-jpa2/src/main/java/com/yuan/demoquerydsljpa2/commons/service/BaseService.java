package com.yuan.demoquerydsljpa2.commons.service;


import com.yuan.demoquerydsljpa2.commons.pojo.BasePojo;

import java.io.Serializable;
import java.util.Optional;

public interface BaseService<T extends BasePojo, ID extends Serializable> {
    T save(T t);

    void insert(T t);

    void update(T t);

    void delete(ID... ids);

    Optional<T> getById(ID id);

    Optional<T> getByExample(T t);

    boolean exist(T t);

    long count(T t);
}
