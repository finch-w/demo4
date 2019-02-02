package com.yuan.demomybatis2.commons.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuan.demomybatis2.commons.dao.BaseMapper;
import com.yuan.demomybatis2.commons.pojo.BasePojo;
import com.yuan.demomybatis2.commons.service.BaseService;
import com.yuan.demomybatis2.commons.utils.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public abstract class BaseServiceImpl<T extends BasePojo, ID extends Serializable, MAPPER extends BaseMapper<T>> implements BaseService<T, ID> {
    public abstract MAPPER getBaseMapper();

    @Override
    @Transactional
    public int insert(T t) {
        t.setCreateDate(new Date());
        return getBaseMapper().insert(t);
    }

    @Override
    @Transactional
    public int update(T t) {
        t.setUpdateDate(new Date());
        T tDb = getBaseMapper().selectById(t.getId());
        BeanUtils.copyPojo(t, tDb);
        return getBaseMapper().updateById(tDb);
    }

    @Override
    @Transactional
    public int save(T t) {
//        Serializable id = (Serializable) BeanUtils.convert(t, "id");
        T tDb = StringUtils.isEmpty(t.getId()) ? null : getBaseMapper().selectById(t.getId());
        if (tDb == null) {
            t.setCreateDate(new Date());
            return getBaseMapper().insert(t);
        } else {
            t.setUpdateDate(new Date());
            BeanUtils.copyPojo(t, tDb);
            return getBaseMapper().updateById(tDb);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public int delete(ID... ids) {
        return getBaseMapper().deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public T get(ID id) {
        return getBaseMapper().selectById(id);
    }

    @Override
    public T get(T t) {
        return getBaseMapper().selectOne(new QueryWrapper<>(t));
    }

    @Override
    public List<T> selectList(T t) {
        return getBaseMapper().selectList(new QueryWrapper<>(t));
    }

    @Override
    public IPage<T> selectPage(Page<T> page, T t) {
        return getBaseMapper().selectPage(page, new QueryWrapper<>(t));
    }
}
