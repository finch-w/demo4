package com.yuan.demojpa.system.service.impl;

import com.yuan.demojpa.commons.utils.BeanUtils;
import com.yuan.demojpa.commons.utils.DateUtils;
import com.yuan.demojpa.system.dao.UserDao;
import com.yuan.demojpa.system.dto.UserDto;
import com.yuan.demojpa.system.pojo.User;
import com.yuan.demojpa.system.service.UserService;
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

@SuppressWarnings("ALL")
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public void save(User user) {
        userDao.saveAndFlush(user);
    }

    @Override
    @Transactional
    public void insert(User user) {
        userDao.insertAndFlush(user);
    }

    @Override
    @Transactional
    public void update(User user, BeanUtils.IgnoreMode mode) {
        userDao.updateAndFlush(user, mode);
    }

    @Override
    @Transactional
    public void delete(String[] ids) {
        userDao.deleteAllByIds(ids);
    }

    @Override
    @Transactional
    public Optional<User> get(String id) {
        return userDao.findById(id);
    }

    @Override
    public List<Map<String, Object>> list(UserDto userDto) {
        Map<String, Object> map = new HashMap<>();
        String sql = " SELECT ID,NAME,USERNAME,CREATEUSER,UPDATEUSER,CREATEDATE,UPDATEDATE FROM user WHERE 1 = 1 ";

        if (BeanUtils.StringUtils.isNotEmpty(userDto.getCreateDate())) {
            sql += " and CREATEDATE = :createdate ";
            map.put("createdate", DateUtils.removeTime(userDto.getCreateDate()));
        }
        if (BeanUtils.StringUtils.isNotEmpty(userDto.getCreatDateStart())) {
            sql += " and CREATEDATE >= :createdatestart";
            map.put("createdatestart", DateUtils.removeTime(userDto.getCreatDateStart()));
        }
        if (BeanUtils.StringUtils.isNotEmpty(userDto.getCreateDateEnd())) {
            sql += " and CREATEDATE <= :createdateend ";
            map.put("createdateend", DateUtils.addDateFianl(userDto.getCreateDateEnd()));
        }
        if (BeanUtils.StringUtils.isNotEmpty(userDto.getUpdateDate())) {
            sql += " and UPDATEDATE = :updatedate ";
            map.put("updatedate", DateUtils.removeTime(userDto.getUpdateDate()));
        }
        if (BeanUtils.StringUtils.isNotEmpty(userDto.getCreatDateStart())) {
            sql += " and UPDATEDATE >= :udpatedatestart ";
            map.put("udpatedatestart", DateUtils.removeTime(userDto.getUpdateDate()));
        }
        if (BeanUtils.StringUtils.isNotEmpty(userDto.getUpdateDateStart())) {
            sql += "  and UPDATEDATE <= :udpatedateend ";
            map.put("updatedateend", DateUtils.addDateFianl(userDto.getCreateDateEnd()));
        }
        if (BeanUtils.StringUtils.isNotEmpty(userDto.getName())) {
            sql += " AND NAME like :name ";
            map.put("name", userDto.getName() + "%");
        }
        if (BeanUtils.StringUtils.isNotEmpty(userDto.getUsername())) {
            sql += " and USERNAME like :username ";
            map.put("username", userDto.getUsername() + "%");
        }
        sql += " order by CREATEDATE desc ";
        return userDao.listBySQLInMap(sql, map);
    }

    @Override
    public Page<Map<String, Object>> page(UserDto userDto) {
        Map<String, Object> map = new HashMap<>();
        String sql = " SELECT ID,NAME,USERNAME,CREATEUSER,UPDATEUSER,CREATEDATE,UPDATEDATE FROM user WHERE 1 = 1 ";

        if (BeanUtils.StringUtils.isNotEmpty(userDto.getCreateDate())) {
            sql += " and CREATEDATE = :createdate ";
            map.put("createdate", DateUtils.removeTime(userDto.getCreateDate()));
        }
        if (BeanUtils.StringUtils.isNotEmpty(userDto.getCreatDateStart())) {
            sql += " and CREATEDATE >= :createdatestart";
            map.put("createdatestart", DateUtils.removeTime(userDto.getCreatDateStart()));
        }
        if (BeanUtils.StringUtils.isNotEmpty(userDto.getCreateDateEnd())) {
            sql += " and CREATEDATE <= :createdateend ";
            map.put("createdateend", DateUtils.addDateFianl(userDto.getCreateDateEnd()));
        }
        if (BeanUtils.StringUtils.isNotEmpty(userDto.getUpdateDate())) {
            sql += " and UPDATEDATE = :updatedate ";
            map.put("updatedate", DateUtils.removeTime(userDto.getUpdateDate()));
        }
        if (BeanUtils.StringUtils.isNotEmpty(userDto.getCreatDateStart())) {
            sql += " and UPDATEDATE >= :udpatedatestart ";
            map.put("udpatedatestart", DateUtils.removeTime(userDto.getUpdateDate()));
        }
        if (BeanUtils.StringUtils.isNotEmpty(userDto.getUpdateDateStart())) {
            sql += "  and UPDATEDATE <= :udpatedateend ";
            map.put("updatedateend", DateUtils.addDateFianl(userDto.getCreateDateEnd()));
        }
        if (BeanUtils.StringUtils.isNotEmpty(userDto.getName())) {
            sql += " AND NAME like :name ";
            map.put("name", userDto.getName() + "%");
        }
        if (BeanUtils.StringUtils.isNotEmpty(userDto.getUsername())) {
            sql += " and USERNAME like :username ";
            map.put("username", userDto.getUsername() + "%");
        }
        if (BeanUtils.StringUtils.isNotEmpty(userDto.getEnabled())) {
            sql += " and ENABLED = :enabled ";
            map.put("enabled", userDto.getEnabled());
        }
        sql += " order by CREATEDATE desc ";
        return userDao.pageBySQLInMap(sql, PageRequest.of(userDto.getPage(), userDto.getSize()), map);
    }

    @Override
    public long check(User user) {
        return userDao.count(Example.of(user));
    }

    @Override
    public User get(User user) {
        return userDao.getOne(Example.of(user));
    }
}

