package com.yuan.demojpa.system.service.impl;

import com.yuan.demojpa.commons.utils.BeanUtils;
import com.yuan.demojpa.commons.utils.DateUtils;
import com.yuan.demojpa.system.dao.PermissionDao;
import com.yuan.demojpa.system.dto.PermissionDto;
import com.yuan.demojpa.system.pojo.Permission;
import com.yuan.demojpa.system.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionDao permissionDao;

    @Override
    @Transactional
    public void insert(Permission permission) {
        permissionDao.insert(permission);
    }

    @Override
    @Transactional
    public void update(Permission permission) {
        permissionDao.update(permission, BeanUtils.IgnoreMode.NULL);
    }

    @Override
    @Transactional
    public void delete(String[] ids) {
        permissionDao.deleteAllByIds(ids);
    }

    @Override
    public Long check(Permission permission) {
        return permissionDao.count(Example.of(permission));
    }

    @Override
    public Optional<Permission> get(String id) {
        return permissionDao.findById(id);
    }

    @Override
    public Permission get(Permission permission) {
        return permissionDao.getOne(Example.of(permission));
    }

    @Override
    public Page<Map<String, Object>> data(PermissionDto permissionDto) {
        Map<String, Object> map = new HashMap<>();
        String sql = "select ID as id,NAME as name,CREATEDATE as createDate,CREATEUSER as createUser,UPDATEDATE as updateDate,UPDATEUSER as updateUser from permission where 1=1 ";
        if (BeanUtils.StringUtils.isNotEmpty(permissionDto.getCreateDate())) {
            sql += " and CREATEDATE = :createdate ";
            map.put("createdate", DateUtils.removeTime(permissionDto.getCreateDate()));
        }
        if (BeanUtils.StringUtils.isNotEmpty(permissionDto.getCreatDateStart())) {
            sql += " and CREATEDATE >= :createdatestart";
            map.put("createdatestart", DateUtils.removeTime(permissionDto.getCreatDateStart()));
        }
        if (BeanUtils.StringUtils.isNotEmpty(permissionDto.getCreateDateEnd())) {
            sql += " and CREATEDATE <= :createdateend ";
            map.put("createdateend", DateUtils.addDateFianl(permissionDto.getCreateDateEnd()));
        }
        if (BeanUtils.StringUtils.isNotEmpty(permissionDto.getUpdateDate())) {
            sql += " and UPDATEDATE = :updatedate ";
            map.put("updatedate", DateUtils.removeTime(permissionDto.getUpdateDate()));
        }
        if (BeanUtils.StringUtils.isNotEmpty(permissionDto.getCreatDateStart())) {
            sql += " and UPDATEDATE >= :udpatedatestart ";
            map.put("udpatedatestart", DateUtils.removeTime(permissionDto.getUpdateDate()));
        }
        if (BeanUtils.StringUtils.isNotEmpty(permissionDto.getUpdateDateStart())) {
            sql += "  and UPDATEDATE <= :udpatedateend ";
            map.put("updatedateend", DateUtils.addDateFianl(permissionDto.getCreateDateEnd()));
        }
        if (BeanUtils.StringUtils.isNotEmpty(permissionDto.getName())) {
            sql += " AND NAME like :name ";
            map.put("name", permissionDto.getName() + "%");
        }
        sql += " order by CREATEDATE desc ";
        return permissionDao.pageBySQLInMap(sql, PageRequest.of(permissionDto.getPage(), permissionDto.getSize()), map);
    }

    @Override
    public List<Map<String, Object>> list(PermissionDto permissionDto) {
        Map<String, Object> map = new HashMap<>();
        String sql = "select ID as id,NAME as name,CREATEDATE as createDate,CREATEUSER as createUser,UPDATEDATE as updateDate,UPDATEUSER as updateUser from permission where 1=1 ";
        if (BeanUtils.StringUtils.isNotEmpty(permissionDto.getCreateDate())) {
            sql += " and CREATEDATE = :createdate ";
            map.put("createdate", DateUtils.removeTime(permissionDto.getCreateDate()));
        }
        if (BeanUtils.StringUtils.isNotEmpty(permissionDto.getCreatDateStart())) {
            sql += " and CREATEDATE >= :createdatestart";
            map.put("createdatestart", DateUtils.removeTime(permissionDto.getCreatDateStart()));
        }
        if (BeanUtils.StringUtils.isNotEmpty(permissionDto.getCreateDateEnd())) {
            sql += " and CREATEDATE <= :createdateend ";
            map.put("createdateend", DateUtils.addDateFianl(permissionDto.getCreateDateEnd()));
        }
        if (BeanUtils.StringUtils.isNotEmpty(permissionDto.getUpdateDate())) {
            sql += " and UPDATEDATE = :updatedate ";
            map.put("updatedate", DateUtils.removeTime(permissionDto.getUpdateDate()));
        }
        if (BeanUtils.StringUtils.isNotEmpty(permissionDto.getCreatDateStart())) {
            sql += " and UPDATEDATE >= :udpatedatestart ";
            map.put("udpatedatestart", DateUtils.removeTime(permissionDto.getUpdateDate()));
        }
        if (BeanUtils.StringUtils.isNotEmpty(permissionDto.getUpdateDateStart())) {
            sql += "  and UPDATEDATE <= :udpatedateend ";
            map.put("updatedateend", DateUtils.addDateFianl(permissionDto.getCreateDateEnd()));
        }
        if (BeanUtils.StringUtils.isNotEmpty(permissionDto.getName())) {
            sql += " AND NAME like :name ";
            map.put("name", permissionDto.getName() + "%");
        }
        sql += " order by CREATEDATE desc ";
        return permissionDao.listBySQLInMap(sql, map);
    }
}
