package com.yuan.demojpa.system.service.impl;

import com.yuan.demojpa.commons.dto.Query;
import com.yuan.demojpa.commons.dto.impl.MapQuery;
import com.yuan.demojpa.commons.service.impl.BaseServiceImpl;
import com.yuan.demojpa.system.dao.SysUserDao;
import com.yuan.demojpa.system.dto.SysUserDto;
import com.yuan.demojpa.system.pojo.SysUser;
import com.yuan.demojpa.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser, String, SysUserDao> implements SysUserService {
    private final SysUserDao sysUserDao;

    @Autowired
    public SysUserServiceImpl(SysUserDao sysUserDao) {
        this.sysUserDao = sysUserDao;
    }

    @Override
    public SysUserDao getBaseRepository() {
        return sysUserDao;
    }

    @Override
    public Page<SysUser> selectPage(SysUserDto dto) {
        Query query = selectPageQuery(dto);
        return sysUserDao.findAllBySQLQuery(query, PageRequest.of(dto.getPage(), dto.getSize()));
    }

    @Override
    public List<SysUser> selectList(SysUserDto dto) {
        return sysUserDao.findAllBySQLQuery(selectPageQuery(dto));
    }

    @Override
    public boolean checkInsert(SysUser user) {
        Query query = selectCheckInsertQuery(user);
        return sysUserDao.findOneBySQLQuery(query, Long.class).map(aLong -> aLong > 0).orElse(false);
    }

    private Query selectCheckInsertQuery(SysUser user) {
        return new MapQuery("", new HashMap<>());
    }

    //SQL
    private Query selectPageQuery(SysUserDto dto) {
        String sql = "";
        return new MapQuery(sql);
    }
}
