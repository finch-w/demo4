package com.yuan.demojpa.system.service.impl;

import com.yuan.demojpa.commons.service.impl.BaseServiceImpl;
import com.yuan.demojpa.commons.utils.DateUtils;
import com.yuan.demojpa.commons.utils.SQLUtils;
import com.yuan.demojpa.system.dto.PermissionDto;
import com.yuan.demojpa.system.pojo.Permission;
import com.yuan.demojpa.system.repository.PermissionRepository;
import com.yuan.demojpa.system.service.PermissionService;
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
public class PermissionServiceImpl extends BaseServiceImpl<Permission, String, PermissionRepository> implements PermissionService {
    private final PermissionRepository permissionRepository;

    @Autowired
    public PermissionServiceImpl(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public PermissionRepository getBaseRepository() {
        return permissionRepository;
    }

    @Override
    public Page<Permission> data(PermissionDto dto) {
        return getBaseRepository().findAll(dto, PageRequest.of(dto.getPage(), dto.getSize(), Sort.by(Sort.Order.desc("createDate"))));
    }

    @Override
    public Page<Permission> data2(PermissionDto dto) {
        String jpql = "select p from Permission p  ";
        List<String> conditions = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        if (!StringUtils.isEmpty(dto.getId())) {
            conditions.add("p.id in (:id)");
            map.put("id", dto.getId().split(","));
        }
        if (!StringUtils.isEmpty(dto.getCreateDateStart())) {
            conditions.add("p.createDate >= :createDateStart");
            map.put("createDateStart", DateUtils.removeTime(dto.getCreateDateStart()));
        }
        if (!StringUtils.isEmpty(dto.getCreateDateEnd())) {
            conditions.add("p.createDate <= :createDateEnd");
            map.put("createDateEnd", DateUtils.setDayFinalTime(dto.getCreateDateEnd()));
        }
        if (!StringUtils.isEmpty(dto.getUpdateDateStart())) {
            conditions.add("p.updateDate >= :updateDateStart");
            map.put("updateDateStart", DateUtils.removeTime(dto.getUpdateDateStart()));
        }
        if (!StringUtils.isEmpty(dto.getUpdateDateEnd())) {
            conditions.add("p.updateDate <= :updateDateEnd");
            map.put("updateDateEnd", DateUtils.setDayFinalTime(dto.getUpdateDateEnd()));
        }
        if (!StringUtils.isEmpty(dto.getName())) {
            conditions.add("p.name like :name");
            map.put("name", dto.getName() + "%");
        }

        if (!StringUtils.isEmpty(dto.getEnabled())) {
            conditions.add("p.enabled = ;enabled");
            map.put("enabled", dto.getEnabled());
        }
        jpql = SQLUtils.createSQL(jpql, conditions);
        jpql += " order by p.createDate desc";
        return getBaseRepository().findAllByJPQL(jpql, PageRequest.of(dto.getPage(), dto.getSize()), map);
    }

    @Override
    public Page<Permission> data3(PermissionDto dto) {
        String sql = "select * from permission  ";
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
    public List<Permission> list(PermissionDto dto) {
        return getBaseRepository().findAll(dto);
    }

    @Override
    public List<Permission> list2(PermissionDto dto) {
        String jpql = "select p from Permission p  ";
        List<String> conditions = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        if (!StringUtils.isEmpty(dto.getId())) {
            conditions.add("p.id in (:id)");
            map.put("id", dto.getId().split(","));
        }
        if (!StringUtils.isEmpty(dto.getCreateDateStart())) {
            conditions.add("p.createDate >= :createDateStart");
            map.put("createDateStart", DateUtils.removeTime(dto.getCreateDateStart()));
        }
        if (!StringUtils.isEmpty(dto.getCreateDateEnd())) {
            conditions.add("p.createDate <= :createDateEnd");
            map.put("createDateEnd", DateUtils.setDayFinalTime(dto.getCreateDateEnd()));
        }
        if (!StringUtils.isEmpty(dto.getUpdateDateStart())) {
            conditions.add("p.updateDate >= :updateDateStart");
            map.put("updateDateStart", DateUtils.removeTime(dto.getUpdateDateStart()));
        }
        if (!StringUtils.isEmpty(dto.getUpdateDateEnd())) {
            conditions.add("p.updateDate <= :updateDateEnd");
            map.put("updateDateEnd", DateUtils.setDayFinalTime(dto.getUpdateDateEnd()));
        }
        if (!StringUtils.isEmpty(dto.getName())) {
            conditions.add("p.name like :name");
            map.put("name", dto.getName() + "%");
        }

        if (!StringUtils.isEmpty(dto.getEnabled())) {
            conditions.add("p.enabled = ;enabled");
            map.put("enabled", dto.getEnabled());
        }
        jpql = SQLUtils.createSQL(jpql, conditions);
        jpql += " order by p.createDate desc";
        return getBaseRepository().findAllBySQL(jpql, map);
    }

    @Override
    public List<Permission> list3(PermissionDto dto) {
        String sql = "select * from permission  ";
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

