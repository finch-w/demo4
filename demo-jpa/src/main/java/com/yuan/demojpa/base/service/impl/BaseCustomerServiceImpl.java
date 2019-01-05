package com.yuan.demojpa.base.service.impl;

import com.yuan.demojpa.base.dto.BaseCustomerDto;
import com.yuan.demojpa.base.pojo.BaseCustomer;
import com.yuan.demojpa.base.repository.BaseCustomerRepository;
import com.yuan.demojpa.base.service.BaseCustomerService;
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

@Service
public class BaseCustomerServiceImpl extends BaseServiceImpl<BaseCustomer, String, BaseCustomerRepository> implements BaseCustomerService {
    private final BaseCustomerRepository baseCustomerRepository;

    @Autowired
    public BaseCustomerServiceImpl(BaseCustomerRepository baseCustomerRepository) {
        this.baseCustomerRepository = baseCustomerRepository;
    }

    @Override
    public BaseCustomerRepository getBaseRepository() {
        return baseCustomerRepository;
    }

    @Override
    public Page<BaseCustomer> data(BaseCustomerDto dto) {
        return getBaseRepository().findAll(dto, PageRequest.of(dto.getPage(), dto.getSize(), Sort.by(Sort.Order.desc("createDate"))));
    }

    @Override
    public Page<BaseCustomer> dataJPQL(BaseCustomerDto dto) {
        String jpql = "select t from BaseCustomer t";
        GetQuery getQuery = new GetQuery(dto, jpql).invoke();
        return getBaseRepository().findAllByJPQL(getQuery.getSql(), PageRequest.of(dto.getPage(), dto.getSize()), getQuery.getMap());
    }

    @Override
    public Page<BaseCustomer> dataSQL(BaseCustomerDto dto) {
        String jpql = "select t.* from baseCustomer t";
        GetQuery getQuery = new GetQuery(dto, jpql).invoke();
        jpql = getQuery.getSql();
        Map<String, Object> map = getQuery.getMap();
        return getBaseRepository().findAllBySQL(jpql, PageRequest.of(dto.getPage(), dto.getSize()), map);
    }

    @Override
    public List<BaseCustomer> list(BaseCustomerDto dto) {
        return getBaseRepository().findAll(dto, Sort.by(Sort.Order.desc("createDate")));
    }


    @Override
    public List<BaseCustomer> listJPQL(BaseCustomerDto dto) {
        String jpql = "select t from BaseCustomer t";
        GetQuery getQuery = new GetQuery(dto, jpql).invoke();
        return getBaseRepository().findAllByJPQL(getQuery.getSql(), getQuery.getMap());
    }

    @Override
    public List<BaseCustomer> listSQL(BaseCustomerDto dto) {
        String jpql = "select t.* from baseCustomer t";
        GetQuery getQuery = new GetQuery(dto, jpql).invoke();
        jpql = getQuery.getSql();
        Map<String, Object> map = getQuery.getMap();
        return getBaseRepository().findAllBySQL(jpql, map);
    }


    private class GetQuery {
        private BaseCustomerDto dto;
        private String sql;
        private Map<String, Object> map;

        public GetQuery(BaseCustomerDto dto, String sql) {
            this.dto = dto;
            this.sql = sql;
        }

        public String getSql() {
            return sql;
        }

        public Map<String, Object> getMap() {
            return map;
        }

        GetQuery invoke() {
            List<String> conditions = new ArrayList<>();
            map = new HashMap<>();
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
                conditions.add("(t.cname like :name or t.cnameShort like :name or t.ename like :name or t.enaameShort like :name)");
                map.put("name", dto.getName() + "%");
            }
            if (!StringUtils.isEmpty(dto.getCity())) {
                conditions.add("t.city = :city");
                map.put("name", dto.getCity());
            }
            if (!StringUtils.isEmpty(dto.getGroup())) {
                conditions.add("t.group = :group");
                map.put("group", dto.getGroup());
            }
            if (!StringUtils.isEmpty(dto.getType())) {
                conditions.add("t.type = :type");
                map.put("type", dto.getType());
            }

            sql = SQLUtils.createSQL(sql, conditions, " order by t.createDate desc");
            return this;
        }
    }
}
