package com.yuan.demojpa.system.service.impl;

import com.yuan.demojpa.commons.dao.BaseRepository;
import com.yuan.demojpa.commons.service.impl.BaseServiceImpl;
import com.yuan.demojpa.system.dao.ResourceDao;
import com.yuan.demojpa.system.dao.RoleDao;
import com.yuan.demojpa.system.dao.UserDao;
import com.yuan.demojpa.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemServiceImpl extends BaseServiceImpl implements SystemService {
    private final UserDao userDao;

    private final RoleDao roleDao;

    private final ResourceDao resourceDao;

    @Autowired
    public SystemServiceImpl(UserDao userDao, RoleDao roleDao, ResourceDao resourceDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.resourceDao = resourceDao;
    }

    @Override
    public BaseRepository getBaseRepository() {
        return null;
    }
}
