package com.yuan.demojooqjpa2.commons.service.impl;

import com.yuan.demojooqjpa2.commons.dao.BaseRepository;
import com.yuan.demojooqjpa2.commons.pojo.BasePojo;
import com.yuan.demojooqjpa2.commons.service.BaseService;
import org.springframework.data.domain.Example;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public abstract class BaseServiceImpl<T extends BasePojo, ID extends Serializable, DAO extends BaseRepository<T, ID>> implements BaseService<T, ID> {
    public abstract DAO getBaseRepository();


    @Override
    @Transactional
    public T save(T t) {
        t.setCreateDate(new Date());
        t.setUpdateDate(new Date());
        return getBaseRepository().saveAndFlush(t);
    }

    @Override
    @Transactional
    public void insert(T t) {
        t.setCreateDate(new Date());
        getBaseRepository().insert(t);
    }

    @Override
    @Transactional
    public void update(T t) {
        t.setUpdateDate(new Date());
        getBaseRepository().update(t);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public void delete(ID... ids) {
        getBaseRepository().delete(ids);
    }

    @Override
    public Optional<T> getById(ID id) {
        return getBaseRepository().findById(id);
    }

    @Override
    public Optional<T> getByExample(T t) {
        return getBaseRepository().findOne(Example.of(t));
    }

    @Override
    public List<T> listByExample(T t) {
        return getBaseRepository().findAll(Example.of(t));
    }

    @Override
    public boolean exist(T t) {
        return getBaseRepository().count(Example.of(t)) > 0;
    }

    @Override
    public long count(T t) {
        return getBaseRepository().count(Example.of(t));
    }

    /**
     * 获取当前用户
     *
     * @return
     */
    public Object getUser() {
        return null;
    }

}
