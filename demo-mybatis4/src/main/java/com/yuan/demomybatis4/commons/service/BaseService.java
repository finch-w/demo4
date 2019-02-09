package com.yuan.demomybatis4.commons.service;

import com.gitee.fastmybatis.core.PageInfo;

import java.util.List;

public interface BaseService<T, ID> {
    void insert(T t);

    void update(T t);

    @SuppressWarnings("unchecked")
    void delete(ID... ids);

    T getByBean(T t);

    T getById(ID id);

    List<T> list(T t);

    PageInfo<T> page(T t, int page, int size);
}
