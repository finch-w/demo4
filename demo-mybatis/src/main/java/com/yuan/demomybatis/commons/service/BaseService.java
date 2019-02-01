package com.yuan.demomybatis.commons.service;

import com.github.pagehelper.PageInfo;
import com.yuan.demomybatis.commons.pojo.BasePojo;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T extends BasePojo, ID extends Serializable> {
    int insert(T t);

    int update(T t);

    int save(T t);

    @SuppressWarnings("unchecked")
    @Transactional
    int delete(ID... ids);

    T get(ID id);

    T get(T t);

    List<T> list(T t);

    PageInfo<T> page(int page, int size, T t);
}
