package com.yuan.demojooqjpa.commons.service.impl;

import com.yuan.demojooqjpa.commons.dao.BaseRepository;
import com.yuan.demojooqjpa.commons.service.BaseService;
import org.springframework.data.domain.Example;

import java.io.Serializable;
import java.util.Optional;

public abstract class BaseServiceImpl<T, ID extends Serializable, DAO extends BaseRepository<T, ID>> implements BaseService<T, ID> {
    public abstract DAO getBaseRepository();

    @Override
    public T save(T t) {
        return getBaseRepository().saveAndFlush(t);
    }

    @Override
    public void insert(T t) {
        getBaseRepository().insert(t);
    }

    @Override
    public void update(T t) {
        getBaseRepository().update(t);
    }

    @Override
    public void delete(ID... ids) {
        getBaseRepository().deleteAllById(ids);
    }

    @Override
    public Optional<T> getById(ID id) {
        return getBaseRepository().findById(id);
    }

    @Override
    public Optional<T> getByExample(T t) {
        return getBaseRepository().getOne(Example.of(t));
    }

    @Override
    public boolean exist(T t) {
        return getBaseRepository().count(Example.of(t)) > 0;
    }

    @Override
    public long count(T t) {
        return getBaseRepository().count(Example.of(t));
    }

}
