package com.yuan.demojpa.system.service.impl;

import com.yuan.demojpa.commons.service.impl.BaseServiceImpl;
import com.yuan.demojpa.system.dao.RoleDao;
import com.yuan.demojpa.system.dto.RoleDto;
import com.yuan.demojpa.system.pojo.SysRole;
import com.yuan.demojpa.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends BaseServiceImpl<SysRole, String, RoleDao> implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    public RoleDao getBaseRepository() {
        return roleDao;
    }

    @Override
    public Long check(SysRole role) {
        return getBaseRepository().countByName(role.getName());
    }

    @Override
    public Page<SysRole> selectPage(RoleDto dto) {

        return getBaseRepository().findAll(dto, PageRequest.of(dto.getPage(), dto.getSize(), Sort.by(Sort.Order.desc("createDate"))));
    }
}
