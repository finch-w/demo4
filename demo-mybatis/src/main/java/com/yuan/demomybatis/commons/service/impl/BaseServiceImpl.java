package com.yuan.demomybatis.commons.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuan.demomybatis.commons.dao.BaseMapper;
import com.yuan.demomybatis.commons.pojo.BasePojo;
import com.yuan.demomybatis.commons.service.BaseService;
import com.yuan.demomybatis.commons.utils.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public abstract class BaseServiceImpl<T extends BasePojo, ID extends Serializable, MAPPER extends BaseMapper<T>> implements BaseService<T, ID> {
    public abstract MAPPER getMapper();

    @Override
    @Transactional
    public int insert(T t) {
        t.setCreateDate(new Date());
        return getMapper().insert(t);
    }

    @Override
    @Transactional
    public int update(T t) {
        T tDb = getMapper().selectByPrimaryKey(t.getId());
        BeanUtils.copyPojo(t, tDb);
        tDb.setUpdateDate(new Date());
        return getMapper().updateByPrimaryKey(tDb);
    }

    @Override
    @Transactional
    public int save(T t) {
        T tDb = StringUtils.isEmpty(t.getId()) ? null : getMapper().selectByPrimaryKey(t.getId());
        if (tDb == null) {
            t.setCreateDate(new Date());
            return insert(t);
        } else {
            tDb.setUpdateDate(new Date());
            return update(t);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public int delete(ID... ids) {
        return Arrays.stream(ids).map(getMapper()::deleteByPrimaryKey).reduce(Integer::sum).orElse(0);
    }

    @Override
    public Integer count(T t) {
        return getMapper().selectCount(t);
    }

    @Override
    public T get(ID id) {
        return getMapper().selectByPrimaryKey(id);
    }

    @Override
    public T get(T t) {
        return getMapper().selectOne(t);
    }

    @Override
    public List<T> selectList(T t) {
        return getMapper().select(t);
    }

    @Override
    public PageInfo<T> selectPage(int page, int size, T t) {
        PageHelper.startPage(page, size);
        return new PageInfo<>(getMapper().select(t));
    }

}
