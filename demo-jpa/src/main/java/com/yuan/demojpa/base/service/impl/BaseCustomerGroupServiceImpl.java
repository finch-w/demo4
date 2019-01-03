package com.yuan.demojpa.base.service.impl;

import com.yuan.demojpa.base.dto.BaseCustomerGroupDto;
import com.yuan.demojpa.base.pojo.BaseCustomerGroup;
import com.yuan.demojpa.base.repository.BaseCustomerGroupRepository;
import com.yuan.demojpa.base.service.BaseCustomerGroupService;
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
public class BaseCustomerGroupServiceImpl extends BaseServiceImpl<BaseCustomerGroup, String, BaseCustomerGroupRepository> implements BaseCustomerGroupService {
    @Autowired
    private BaseCustomerGroupRepository baseCustomerGroupRepository;

    @Override
    public BaseCustomerGroupRepository getBaseRepository() {
        return baseCustomerGroupRepository;
    }

    @Override
    public Page<BaseCustomerGroup> data(BaseCustomerGroupDto dto) {
        return getBaseRepository().findAll(dto, PageRequest.of(dto.getPage() - 1, dto.getSize(), Sort.by(Sort.Order.desc("createDate"))));
    }

    @Override
    public List<BaseCustomerGroup> list(BaseCustomerGroupDto dto) {
        return getBaseRepository().findAll(dto, Sort.by(Sort.Order.desc("createDate")));
    }

    @Override
    public Page<BaseCustomerGroup> data2(BaseCustomerGroupDto dto) {
        String jpql = "select bcd from BaseCustomerGroup bcd ";
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
        return getBaseRepository().findAllByJPQL(jpql, PageRequest.of(dto.getPage() - 1, dto.getSize()), map);
    }

    @Override
    public Page<BaseCustomerGroup> data3(BaseCustomerGroupDto dto) {
        String jpql = "select bcd.* from baseCustomerGroup bcd ";
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
        return getBaseRepository().findAllByJPQL(jpql, PageRequest.of(dto.getPage() - 1, dto.getSize()), map);
    }

    public List<BaseCustomerGroup> list2(BaseCustomerGroupDto dto) {
        String jpql = "select t from BaseCustomerGroup t";
        List<String> conditions = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        if (!StringUtils.isEmpty(dto.getId())) {
            conditions.add("t.id in (:id)");
            map.put("id", dto.getId().split(","));
        }
        if (!StringUtils.isEmpty(dto.getCreateDateStart())) {
            conditions.add("t.createDate >= :createDateStart");
            map.put("createDateStart", DateUtils.removeTime(dto.getCreateDateStart()));
        }
        if (!StringUtils.isEmpty(dto.getCreateDateEnd())) {
            conditions.add("t.createDate <= :createDateEnd");
            map.put("createDateEnd", DateUtils.setDayFinalTime(dto.getCreateDateEnd()));
        }
        if (!StringUtils.isEmpty(dto.getUpdateDateStart())) {
            conditions.add("t.updateDate >= :updateDateStart");
            map.put("updateDateStart", DateUtils.removeTime(dto.getUpdateDateStart()));
        }
        if (!StringUtils.isEmpty(dto.getUpdateDateEnd())) {
            conditions.add("t.updateDate <= :updateDateEnd");
            map.put("updateDateEnd", DateUtils.setDayFinalTime(dto.getUpdateDateEnd()));
        }
        if (!StringUtils.isEmpty(dto.getName())) {
            conditions.add("t.name like :name");
            map.put("name", dto.getName() + "%");
        }
        if (!StringUtils.isEmpty(dto.getEnabled())) {
            conditions.add("t.enabled = ;enabled");
            map.put("enabled", dto.getEnabled());
        }
        jpql = SQLUtils.createSQL(jpql, conditions);
        jpql += " order by t.createDate desc";
        return getBaseRepository().findAllByJPQL(jpql, map);
    }

    public List<BaseCustomerGroup> list3(BaseCustomerGroupDto dto) {
        String jpql = "select t.* from baseCustomerGroup t";
        List<String> conditions = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        if (!StringUtils.isEmpty(dto.getId())) {
            conditions.add("t.id in (:id)");
            map.put("id", dto.getId().split(","));
        }
        if (!StringUtils.isEmpty(dto.getCreateDateStart())) {
            conditions.add("t.createDate >= :createDateStart");
            map.put("createDateStart", DateUtils.removeTime(dto.getCreateDateStart()));
        }
        if (!StringUtils.isEmpty(dto.getCreateDateEnd())) {
            conditions.add("t.createDate <= :createDateEnd");
            map.put("createDateEnd", DateUtils.setDayFinalTime(dto.getCreateDateEnd()));
        }
        if (!StringUtils.isEmpty(dto.getUpdateDateStart())) {
            conditions.add("t.updateDate >= :updateDateStart");
            map.put("updateDateStart", DateUtils.removeTime(dto.getUpdateDateStart()));
        }
        if (!StringUtils.isEmpty(dto.getUpdateDateEnd())) {
            conditions.add("t.updateDate <= :updateDateEnd");
            map.put("updateDateEnd", DateUtils.setDayFinalTime(dto.getUpdateDateEnd()));
        }
        if (!StringUtils.isEmpty(dto.getName())) {
            conditions.add("t.name like :name");
            map.put("name", dto.getName() + "%");
        }
        if (!StringUtils.isEmpty(dto.getEnabled())) {
            conditions.add("t.enabled = ;enabled");
            map.put("enabled", dto.getEnabled());
        }
        jpql = SQLUtils.createSQL(jpql, conditions);
        jpql += " order by t.createDate desc";
        return getBaseRepository().findAllByJPQL(jpql, map);
    }
}
