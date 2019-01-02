package com.yuan.demojpa.system.service.impl;

import com.yuan.demojpa.commons.service.impl.BaseServiceImpl;
import com.yuan.demojpa.commons.utils.DateUtils;
import com.yuan.demojpa.commons.utils.SQLUtils;
import com.yuan.demojpa.system.dto.RoleDto;
import com.yuan.demojpa.system.pojo.Role;
import com.yuan.demojpa.system.repository.RoleRepository;
import com.yuan.demojpa.system.service.RoleService;
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
public class RoleServiceImpl extends BaseServiceImpl<Role, String, RoleRepository> implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleRepository getBaseRepository() {
        return roleRepository;
    }

    @Override
    public Page<Role> data(RoleDto dto) {
        return getBaseRepository().findAll(dto, PageRequest.of(dto.getPage(), dto.getSize(), Sort.by(Sort.Order.desc("createDate"))));
    }

    @Override
    public Page<Role> data2(RoleDto dto) {
        String jpql = "select r from Role r ";
        List<String> conditions = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        if (!StringUtils.isEmpty(dto.getId())) {
            conditions.add("r.id in (:id)");
            map.put("id", dto.getId().split(","));
        }
        if (!StringUtils.isEmpty(dto.getCreateDateStart())) {
            conditions.add("r.createDate >= :createDateStart");
            map.put("createDateStart", DateUtils.removeTime(dto.getCreateDateStart()));
        }
        if (!StringUtils.isEmpty(dto.getCreateDateEnd())) {
            conditions.add("r.createDate <= :createDateEnd");
            map.put("createDateEnd", DateUtils.setDayFinalTime(dto.getCreateDateEnd()));
        }
        if (!StringUtils.isEmpty(dto.getUpdateDateStart())) {
            conditions.add("r.updateDate >= :updateDateStart");
            map.put("updateDateStart", DateUtils.removeTime(dto.getUpdateDateStart()));
        }
        if (!StringUtils.isEmpty(dto.getUpdateDateEnd())) {
            conditions.add("r.updateDate <= :updateDateEnd");
            map.put("updateDateEnd", DateUtils.setDayFinalTime(dto.getUpdateDateEnd()));
        }
        String sql = SQLUtils.createSQL(jpql, conditions);
        sql += " order by r.createDate";
        return getBaseRepository().findAllByJPQL(sql, PageRequest.of(dto.getPage(), dto.getSize()));
    }

    @Override
    public Page<Role> data3(RoleDto dto) {
        String sql = "select * from role order by createDate";
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
        return getBaseRepository().findAllBySQL(sql, PageRequest.of(dto.getPage(), dto.getSize()));
    }

    @Override
    public List<Role> list(RoleDto dto) {
        return getBaseRepository().findAll(dto);
    }

    @Override
    public List<Role> list2(RoleDto dto) {
        String jpql = "select r from Role r ";
        List<String> conditions = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        if (!StringUtils.isEmpty(dto.getId())) {
            conditions.add("r.id in (:id)");
            map.put("id", dto.getId().split(","));
        }
        if (!StringUtils.isEmpty(dto.getCreateDateStart())) {
            conditions.add("r.createDate >= :createDateStart");
            map.put("createDateStart", DateUtils.removeTime(dto.getCreateDateStart()));
        }
        if (!StringUtils.isEmpty(dto.getCreateDateEnd())) {
            conditions.add("r.createDate <= :createDateEnd");
            map.put("createDateEnd", DateUtils.setDayFinalTime(dto.getCreateDateEnd()));
        }
        if (!StringUtils.isEmpty(dto.getUpdateDateStart())) {
            conditions.add("r.updateDate >= :updateDateStart");
            map.put("updateDateStart", DateUtils.removeTime(dto.getUpdateDateStart()));
        }
        if (!StringUtils.isEmpty(dto.getUpdateDateEnd())) {
            conditions.add("r.updateDate <= :updateDateEnd");
            map.put("updateDateEnd", DateUtils.setDayFinalTime(dto.getUpdateDateEnd()));
        }
        String sql = SQLUtils.createSQL(jpql, conditions);
        sql += " order by r.createDate";
        return getBaseRepository().findAllByJPQL(sql, map);
    }

    @Override
    public List<Role> list3(RoleDto dto) {
        String sql = "select * from role order by createDate";
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
