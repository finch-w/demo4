package com.yuan.demojpa.commons.service;

import java.io.Serializable;
import java.util.Optional;

public interface BaseService<T, ID extends Serializable> {
    T save(T t);

    void insert(T t);

    void update(T t);

    void delete(ID... ids);

    Optional<T> getById(ID id);

    T getByExample(T t);

    boolean exist(T t);

    long count(T t);
}
