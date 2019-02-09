package com.yuan.demomybatis4.commons.service.impl;

import com.gitee.fastmybatis.core.PageInfo;
import com.gitee.fastmybatis.core.query.Query;
import com.gitee.fastmybatis.core.query.param.PageParam;
import com.gitee.fastmybatis.core.util.MapperUtil;
import com.yuan.demomybatis4.commons.dao.BaseMapper;
import com.yuan.demomybatis4.commons.service.BaseService;

import java.util.Arrays;
import java.util.List;

public abstract class BaseServiceImpl<T, ID, MAPPER extends BaseMapper<T, ID>> implements BaseService<T, ID> {
    public abstract MAPPER getMapper();

    @Override
    public void insert(T t) {
        getMapper().save(t);
    }

    @Override
    public void update(T t) {
        getMapper().update(t);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void delete(ID... ids) {
        Integer integer = Arrays.stream(ids).map(getMapper()::deleteById).reduce(Integer::sum).orElse(0);
    }


    @Override
    public T getByBean(T t) {
        return getMapper().getByQuery(Query.build(t));
    }

    @Override
    public T getById(ID id) {
        return getMapper().getById(id);
    }

    @Override
    public List<T> list(T t) {
        return getMapper().list(Query.build(t));
    }

    @Override
    public PageInfo<T> page(T t, int page, int size) {
        PageParam pageParam = new PageParam();
        pageParam.setPageIndex(page);
        pageParam.setPageSize(size);
        return MapperUtil.query(getMapper(), pageParam);
    }
}
