package com.yuan.demojpa.system.service.impl;

import com.yuan.demojpa.commons.utils.BeanUtils;
import com.yuan.demojpa.commons.utils.DateUtils;
import com.yuan.demojpa.system.dao.RoleDao;
import com.yuan.demojpa.system.dto.RoleDto;
import com.yuan.demojpa.system.pojo.Role;
import com.yuan.demojpa.system.service.RoleService;
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
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    @Transactional
    public void save(Role role) {
        roleDao.saveAndFlush(role);
    }

    @Override
    @Transactional
    public void insert(Role role) {
        roleDao.insertAndFlush(role);
    }

    @Override
    @Transactional
    public void update(Role role) {
        roleDao.updateAndFlush(role, BeanUtils.IgnoreMode.NULL);
    }

    @Override
    @Transactional
    public void delete(String[] ids) {
        roleDao.deleteAllByIds(ids);
    }

    @Override
    public Page<Map<String, Object>> data(RoleDto roleDto) {
        Map<String, Object> map = new HashMap<>();
        String sql = "select ID as id,NAME as name,CREATEUSER as createUser,CREATEDATE as createDate,UPDATEUSER as updateUser,UPDATEDATE as updateDate from role where 1=1 ";
        if (BeanUtils.StringUtils.isNotEmpty(roleDto.getCreateDate())) {
            sql += " and CREATEDATE = :createdate ";
            map.put("createdate", DateUtils.removeTime(roleDto.getCreateDate()));
        }
        if (BeanUtils.StringUtils.isNotEmpty(roleDto.getCreatDateStart())) {
            sql += " and CREATEDATE >= :createdatestart";
            map.put("createdatestart", DateUtils.removeTime(roleDto.getCreatDateStart()));
        }
        if (BeanUtils.StringUtils.isNotEmpty(roleDto.getCreateDateEnd())) {
            sql += " and CREATEDATE <= :createdateend ";
            map.put("createdateend", DateUtils.addDateFianl(roleDto.getCreateDateEnd()));
        }
        if (BeanUtils.StringUtils.isNotEmpty(roleDto.getUpdateDate())) {
            sql += " and UPDATEDATE = :updatedate ";
            map.put("updatedate", DateUtils.removeTime(roleDto.getUpdateDate()));
        }
        if (BeanUtils.StringUtils.isNotEmpty(roleDto.getCreatDateStart())) {
            sql += " and UPDATEDATE >= :udpatedatestart ";
            map.put("udpatedatestart", DateUtils.removeTime(roleDto.getUpdateDate()));
        }
        if (BeanUtils.StringUtils.isNotEmpty(roleDto.getUpdateDateStart())) {
            sql += "  and UPDATEDATE <= :udpatedateend ";
            map.put("updatedateend", DateUtils.addDateFianl(roleDto.getCreateDateEnd()));
        }
        if (BeanUtils.StringUtils.isNotEmpty(roleDto.getName())) {
            sql += "  and NAME like :name";
            map.put("name", roleDto.getName() + "%");
        }
        sql += " order by CREATEDATE";
        return roleDao.pageBySQLInMap(sql, PageRequest.of(roleDto.getPage(), roleDto.getSize()), map);
    }

    @Override
    public List<Map<String, Object>> list(RoleDto roleDto) {
        Map<String, Object> map = new HashMap<>();
        String sql = "select ID as id,NAME as name from role where 1=1 ";
        if (BeanUtils.StringUtils.isNotEmpty(roleDto.getCreateDate())) {
            sql += " and CREATEDATE = :createdate ";
            map.put("createdate", DateUtils.removeTime(roleDto.getCreateDate()));
        }
        if (BeanUtils.StringUtils.isNotEmpty(roleDto.getCreatDateStart())) {
            sql += " and CREATEDATE >= :createdatestart";
            map.put("createdatestart", DateUtils.removeTime(roleDto.getCreatDateStart()));
        }
        if (BeanUtils.StringUtils.isNotEmpty(roleDto.getCreateDateEnd())) {
            sql += " and CREATEDATE <= :createdateend ";
            map.put("createdateend", DateUtils.addDateFianl(roleDto.getCreateDateEnd()));
        }
        if (BeanUtils.StringUtils.isNotEmpty(roleDto.getUpdateDate())) {
            sql += " and UPDATEDATE = :updatedate ";
            map.put("updatedate", DateUtils.removeTime(roleDto.getUpdateDate()));
        }
        if (BeanUtils.StringUtils.isNotEmpty(roleDto.getCreatDateStart())) {
            sql += " and UPDATEDATE >= :udpatedatestart ";
            map.put("udpatedatestart", DateUtils.removeTime(roleDto.getUpdateDate()));
        }
        if (BeanUtils.StringUtils.isNotEmpty(roleDto.getUpdateDateStart())) {
            sql += "  and UPDATEDATE <= :udpatedateend ";
            map.put("updatedateend", DateUtils.addDateFianl(roleDto.getCreateDateEnd()));
        }
        if (BeanUtils.StringUtils.isNotEmpty(roleDto.getName())) {
            sql += "  and NAME like :name";
            map.put("name", roleDto.getName() + "%");
        }
        sql += " order by CREATEDATE";
        return roleDao.listBySQLInMap(sql, map);
    }

    @Override
    public Optional<Role> get(String id) {
        return roleDao.findById(id);
    }

    @Override
    public Long check(Role role) {
        return roleDao.count(Example.of(role));
    }

    @Override
    public Role get(Role role) {
        return roleDao.getOne(Example.of(role));
    }
}
