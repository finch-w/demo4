package com.yuan.demojpa2.system.service.impl;

import com.yuan.demojpa2.commons.dto.MapQuery;
import com.yuan.demojpa2.commons.service.impl.BaseServiceImpl;
import com.yuan.demojpa2.system.dao.SysUserDao;
import com.yuan.demojpa2.system.dto.SysUserDto;
import com.yuan.demojpa2.system.pojo.SysUser;
import com.yuan.demojpa2.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
        MapQuery query = dto.baseConditionQuery();
        return sysUserDao.findAllBySQL(query.getSql(), PageRequest.of(dto.getPage(), dto.getSize()), query.getMap());
    }

    @Override
    public List<SysUser> selectList(SysUserDto dto) {
        MapQuery query = dto.baseConditionQuery();
        return sysUserDao.findAllBySQL(query.getSql(), query.getMap());
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Override
    public boolean checkInsert(SysUser user) {
        MapQuery query = SysUserDto.checkInsert(user);
        return sysUserDao.findOneBySQL(query.getSql(), Long.class, query.getMap()).get() > 0;
    }
}
