package com.yuan.demomybatis3.commons.service.impl;

import com.yuan.demomybatis3.commons.dao.BaseMapper;
import com.yuan.demomybatis3.commons.pojo.BasePojo;
import com.yuan.demomybatis3.commons.service.BaseService;
import com.yuan.demomybatis3.commons.utils.BeanUtils;

import java.io.Serializable;


public abstract class BaseServiceImpl<T extends BasePojo, Id extends Serializable, MAPPER extends BaseMapper<T, Id>> implements BaseService<T, Id> {
    public abstract MAPPER getMapper();

    @Override
    public void save(T t) {
    }

    @Override
    public void insert(T t) {
        getMapper().insert(t);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void update(T t) {
        T t1 = getMapper().selectOne((Id) t.getId());
        BeanUtils.copyPojo(t, t1);
        getMapper().update(t1);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void delete(Id... ids) {
        getMapper().deleteArray(ids);
    }

    @Override
    public T get(Id id) {
        return getMapper().selectOne(id);
    }


}
