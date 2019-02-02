package com.yuan.demomybatis2.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuan.demomybatis2.commons.pojo.BasePojo;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T extends BasePojo, ID extends Serializable> {
    int insert(T t);

    int update(T t);

    int save(T t);

    @SuppressWarnings("unused")
    int delete(ID... ids);

    T get(ID id);

    T get(T t);

    List<T> selectList(T t);

    IPage<T> selectPage(Page<T> page, T t);
}
