package com.yuan.demojpa.system.service.impl;

import com.yuan.demojpa.commons.service.impl.BaseServiceImpl;
import com.yuan.demojpa.commons.utils.DateUtils;
import com.yuan.demojpa.commons.utils.SQLUtils;
import com.yuan.demojpa.system.dto.UserDto;
import com.yuan.demojpa.system.pojo.User;
import com.yuan.demojpa.system.repository.UserRepository;
import com.yuan.demojpa.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("ALL")
@Service
public class UserServiceImpl extends BaseServiceImpl<User, String, UserRepository> implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserRepository getBaseRepository() {
        return userRepository;
    }

    @Override
    public Page<User> data(UserDto dto) {

        return getBaseRepository().findAll(dto, PageRequest.of(dto.getPage(), dto.getSize()));
    }

    @Override
    public Page<User> data2(UserDto dto) {
        String jpql = "select u from User u";
        List<String> conditions = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        if (!StringUtils.isEmpty(dto.getId())) {
            conditions.add("u.id in (:id)");
            map.put("id", dto.getId().split(","));
        }
        if (!StringUtils.isEmpty(dto.getCreateDateStart())) {
            conditions.add("u.createDate >= :createDateStart");
            map.put("createDateStart", DateUtils.removeTime(dto.getCreateDateStart()));
        }
        if (!StringUtils.isEmpty(dto.getCreateDateEnd())) {
            conditions.add("u.createDate <= :createDateEnd");
            map.put("createDateEnd", DateUtils.setDayFinalTime(dto.getCreateDateEnd()));
        }
        if (!StringUtils.isEmpty(dto.getUpdateDateStart())) {
            conditions.add("u.updateDate >= :updateDateStart");
            map.put("updateDateStart", DateUtils.removeTime(dto.getUpdateDateStart()));
        }
        if (!StringUtils.isEmpty(dto.getUpdateDateEnd())) {
            conditions.add("u.updateDate <= :updateDateEnd");
            map.put("updateDateEnd", DateUtils.setDayFinalTime(dto.getUpdateDateEnd()));
        }
        if (!StringUtils.isEmpty(dto.getName())) {
            conditions.add("u.name like :name");
            map.put("name", dto.getName() + "%");
        }
        if (!StringUtils.isEmpty(dto.getUsername())) {
            conditions.add("u.username like :username");
            map.put("username", dto.getUsername() + "%");
        }
        if (!StringUtils.isEmpty(dto.getEnabled())) {
            conditions.add("u.enabled = ;enabled");
            map.put("enabled", dto.getEnabled());
        }
        String sql = SQLUtils.createSQL(jpql, conditions);
        sql += " order by u.createDate desc";
        return getBaseRepository().findAllByJPQL(sql, PageRequest.of(dto.getPage(), dto.getSize()));
    }

    @Override
    public Page<User> data3(UserDto dto) {
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
        if (!StringUtils.isEmpty(dto.getUsername())) {
            conditions.add("username like :username");
            map.put("username", dto.getUsername() + "%");
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
    public List<User> list(UserDto dto) {
        return getBaseRepository().findAll(dto);
    }

    @Override
    public List<User> list2(UserDto dto) {
        String jpql = "select u from User u";
        List<String> conditions = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        if (!StringUtils.isEmpty(dto.getId())) {
            conditions.add("u.id in (:id)");
            map.put("id", dto.getId().split(","));
        }
        if (!StringUtils.isEmpty(dto.getCreateDateStart())) {
            conditions.add("u.createDate >= :createDateStart");
            map.put("createDateStart", DateUtils.removeTime(dto.getCreateDateStart()));
        }
        if (!StringUtils.isEmpty(dto.getCreateDateEnd())) {
            conditions.add("u.createDate <= :createDateEnd");
            map.put("createDateEnd", DateUtils.setDayFinalTime(dto.getCreateDateEnd()));
        }
        if (!StringUtils.isEmpty(dto.getUpdateDateStart())) {
            conditions.add("u.updateDate >= :updateDateStart");
            map.put("updateDateStart", DateUtils.removeTime(dto.getUpdateDateStart()));
        }
        if (!StringUtils.isEmpty(dto.getUpdateDateEnd())) {
            conditions.add("u.updateDate <= :updateDateEnd");
            map.put("updateDateEnd", DateUtils.setDayFinalTime(dto.getUpdateDateEnd()));
        }
        String sql = SQLUtils.createSQL(jpql, conditions);
        sql += " order by u.createDate desc";
        return getBaseRepository().findAllByJPQL(sql, map);
    }

    @Override
    public List<User> list3(UserDto dto) {
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
