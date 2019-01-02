package com.yuan.demojpa.base.service.impl;

import com.yuan.demojpa.base.dto.BaseCustomerTypeDto;
import com.yuan.demojpa.base.pojo.BaseCustomerType;
import com.yuan.demojpa.base.repository.BaseCustomerTypeRepository;
import com.yuan.demojpa.base.service.BaseCustomerTypeService;
import com.yuan.demojpa.commons.service.impl.BaseServiceImpl;
import com.yuan.demojpa.commons.utils.DateUtils;
import com.yuan.demojpa.commons.utils.SQLUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("ALL")
@Service
public class BaseCustomerTypeServiceImpl extends BaseServiceImpl<BaseCustomerType, String, BaseCustomerTypeRepository> implements BaseCustomerTypeService {
    private final BaseCustomerTypeRepository baseCustomerTypeRepository;

    @Autowired
    public BaseCustomerTypeServiceImpl(BaseCustomerTypeRepository baseCustomerTypeRepository) {
        this.baseCustomerTypeRepository = baseCustomerTypeRepository;
    }

    @Override
    public BaseCustomerTypeRepository getBaseRepository() {
        return baseCustomerTypeRepository;
    }

    @Override
    public Page<BaseCustomerType> data(BaseCustomerTypeDto dto) {
        return getBaseRepository().findAll(dto, PageRequest.of(dto.getPage(), dto.getSize(), Sort.by(Sort.Order.desc("createDate"))));
    }

    @Override
    public Page<BaseCustomerType> data2(BaseCustomerTypeDto dto) {
        String jpql = "select bct from BaseCustomerType bcd";
        List<String> conditions = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        if (!StringUtils.isEmpty(dto.getId())) {
            conditions.add("bcd.id in (:id)");
            map.put("id", dto.getId().split(","));
        }
        if (!StringUtils.isEmpty(dto.getCreateDateStart())) {
            conditions.add("bcd.createDate >= :createDateStart");
            map.put("createDateStart", DateUtils.removeTime(dto.getCreateDateStart()));
        }
        if (!StringUtils.isEmpty(dto.getCreateDateEnd())) {
            conditions.add("bcd.createDate <= :createDateEnd");
            map.put("createDateEnd", DateUtils.setDayFinalTime(dto.getCreateDateEnd()));
        }
        if (!StringUtils.isEmpty(dto.getUpdateDateStart())) {
            conditions.add("bcd.updateDate >= :updateDateStart");
            map.put("updateDateStart", DateUtils.removeTime(dto.getUpdateDateStart()));
        }
        if (!StringUtils.isEmpty(dto.getUpdateDateEnd())) {
            conditions.add("bcd.updateDate <= :updateDateEnd");
            map.put("updateDateEnd", DateUtils.setDayFinalTime(dto.getUpdateDateEnd()));
        }
        if (!StringUtils.isEmpty(dto.getName())) {
            conditions.add("bcd.name like :name");
            map.put("name", dto.getName() + "%");
        }
        if (!StringUtils.isEmpty(dto.getEnabled())) {
            conditions.add("bcd.enabled = ;enabled");
            map.put("enabled", dto.getEnabled());
        }
        jpql = SQLUtils.createSQL(jpql, conditions);
        jpql += " order by bcd.createDate desc";
        return getBaseRepository().findAllByJPQL(jpql, PageRequest.of(dto.getPage(), dto.getSize()), map);
    }

    @Override
    public Page<BaseCustomerType> data3(BaseCustomerTypeDto dto) {
        String sql = "select * from user";
        List<String> conditions = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        if (!StringUtils.isEmpty(dto.getId())) {
            conditions.add("id in (:id)");
            map.put("id", dto.getId().split(","));
        }

        if (!StringUtils.isEmpty(dto.getCreateDateStart())) {
            conditions.add("createDate >= :createDateStart");
            map.put("createDateStart", DateUtils.removeTime(dto.getCreateDateStart()));
        }
        if (!StringUtils.isEmpty(dto.getCreateDateEnd())) {
            conditions.add("createDate <= :createDateEnd");
            map.put("createDateEnd", DateUtils.setDayFinalTime(dto.getCreateDateEnd()));
        }

        if (!StringUtils.isEmpty(dto.getUpdateDateStart())) {
            conditions.add("updateDate >= :updateDateStart");
            map.put("updateDateStart", DateUtils.removeTime(dto.getUpdateDateStart()));
        }
        if (!StringUtils.isEmpty(dto.getUpdateDateEnd())) {
            conditions.add("updateDate >= :updateDateEnd");
            map.put("updateDateEnd", DateUtils.setDayFinalTime(dto.getUpdateDateEnd()));
        }
        if (!StringUtils.isEmpty(dto.getName())) {
            conditions.add("name like :name");
            map.put("name", dto.getName() + "%");
        }
        if (!StringUtils.isEmpty(dto.getEnabled())) {
            conditions.add("enabled = :enabled");
            map.put("enabled", dto.getEnabled());
        }
        sql = SQLUtils.createSQL(sql, conditions);
        sql += " order by createDate desc";
        return getBaseRepository().findAllBySQL(sql, PageRequest.of(dto.getPage(), dto.getSize()), map);
    }

    @Override
    public List<BaseCustomerType> list(BaseCustomerTypeDto dto) {
        return getBaseRepository().findAll(dto, Sort.by(Sort.Order.desc("createDate")));
    }

    @Override
    public List<BaseCustomerType> list2(BaseCustomerTypeDto dto) {
        String jpql = "select bct from BaseCustomerType bcd";
        List<String> conditions = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        if (!StringUtils.isEmpty(dto.getId())) {
            conditions.add("bcd.id in (:id)");
            map.put("id", dto.getId().split(","));
        }
        if (!StringUtils.isEmpty(dto.getCreateDateStart())) {
            conditions.add("bcd.createDate >= :createDateStart");
            map.put("createDateStart", DateUtils.removeTime(dto.getCreateDateStart()));
        }
        if (!StringUtils.isEmpty(dto.getCreateDateEnd())) {
            conditions.add("bcd.createDate <= :createDateEnd");
            map.put("createDateEnd", DateUtils.setDayFinalTime(dto.getCreateDateEnd()));
        }
        if (!StringUtils.isEmpty(dto.getUpdateDateStart())) {
            conditions.add("bcd.updateDate >= :updateDateStart");
            map.put("updateDateStart", DateUtils.removeTime(dto.getUpdateDateStart()));
        }
        if (!StringUtils.isEmpty(dto.getUpdateDateEnd())) {
            conditions.add("bcd.updateDate <= :updateDateEnd");
            map.put("updateDateEnd", DateUtils.setDayFinalTime(dto.getUpdateDateEnd()));
        }
        if (!StringUtils.isEmpty(dto.getName())) {
            conditions.add("bcd.name like :name");
            map.put("name", dto.getName() + "%");
        }
        if (!StringUtils.isEmpty(dto.getEnabled())) {
            conditions.add("bcd.enabled = ;enabled");
            map.put("enabled", dto.getEnabled());
        }
        jpql = SQLUtils.createSQL(jpql, conditions);
        jpql += " order by bcd.createDate desc";
        return getBaseRepository().findAllByJPQL(jpql, map);
    }

    @Override
    public List<BaseCustomerType> list3(BaseCustomerTypeDto dto) {
        String sql = "select * from user";
        List<String> conditions = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        if (!StringUtils.isEmpty(dto.getId())) {
            conditions.add("id in (:id)");
            map.put("id", dto.getId().split(","));
        }

        if (!StringUtils.isEmpty(dto.getCreateDateStart())) {
            conditions.add("createDate >= :createDateStart");
            map.put("createDateStart", DateUtils.removeTime(dto.getCreateDateStart()));
        }
        if (!StringUtils.isEmpty(dto.getCreateDateEnd())) {
            conditions.add("createDate <= :createDateEnd");
            map.put("createDateEnd", DateUtils.setDayFinalTime(dto.getCreateDateEnd()));
        }

        if (!StringUtils.isEmpty(dto.getUpdateDateStart())) {
            conditions.add("updateDate >= :updateDateStart");
            map.put("updateDateStart", DateUtils.removeTime(dto.getUpdateDateStart()));
        }
        if (!StringUtils.isEmpty(dto.getUpdateDateEnd())) {
            conditions.add("updateDate >= :updateDateEnd");
            map.put("updateDateEnd", DateUtils.setDayFinalTime(dto.getUpdateDateEnd()));
        }
        if (!StringUtils.isEmpty(dto.getName())) {
            conditions.add("name like :name");
            map.put("name", dto.getName() + "%");
        }
        if (!StringUtils.isEmpty(dto.getEnabled())) {
            conditions.add("enabled = :enabled");
            map.put("enabled", dto.getEnabled());
        }
        sql = SQLUtils.createSQL(sql, conditions);
        sql += " order by createDate desc";
        return getBaseRepository().findAllBySQL(sql, map);
    }
}
