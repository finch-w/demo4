package com.yuan.demojpa.commons.service.impl;

import com.yuan.demojpa.commons.dao.BaseRepository;
import com.yuan.demojpa.commons.pojo.BasePojo;
import com.yuan.demojpa.commons.service.BaseService;
import com.yuan.demojpa.system.pojo.SysUser;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public abstract class BaseServiceImpl<T extends BasePojo, ID extends Serializable, DAO extends BaseRepository<T, ID>> implements BaseService<T, ID> {
    public abstract DAO getBaseRepository();

    protected boolean isNotEmpty(Object object) {
        return !StringUtils.isEmpty(object);
    }


    @Override
    @Transactional
    public T save(T t) {
        t.setCreateDate(new Date());
        t.setUpdateDate(new Date());
        t.setCreateUser(getUser());
        t.setUpdateUser(getUser());
        return getBaseRepository().saveAndFlush(t);
    }

    @Override
    @Transactional
    public void insert(T t) {
        t.setCreateDate(new Date());
        t.setCreateUser(getUser());
        getBaseRepository().insert(t);
    }

    @Override
    @Transactional
    public void update(T t) {
        t.setUpdateDate(new Date());
        t.setUpdateUser(getUser());
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
    public Page<T> pageByExample(int page, int size, T t) {
        return getBaseRepository().findAll(Example.of(t), PageRequest.of(page, size));
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
     */
    private String getUser() {
        SysUser credentials = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return credentials.getId();
    }

}
