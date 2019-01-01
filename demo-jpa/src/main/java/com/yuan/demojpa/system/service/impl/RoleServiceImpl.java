package com.yuan.demojpa.system.service.impl;

import com.yuan.demojpa.commons.service.impl.BaseServiceImpl;
import com.yuan.demojpa.system.dto.RoleDto;
import com.yuan.demojpa.system.pojo.Role;
import com.yuan.demojpa.system.repository.RoleRepository;
import com.yuan.demojpa.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

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
        String jpql = "select r from Role r order by r.createDate";
        return getBaseRepository().findAllByJPQL(jpql, PageRequest.of(dto.getPage(), dto.getSize()));
    }

    @Override
    public Page<Role> data3(RoleDto dto) {
        String sql = "select * from role order by createDate";
        return getBaseRepository().findAllBySQL(sql, PageRequest.of(dto.getPage(), dto.getSize()));
    }

    @Override
    public List<Role> list(RoleDto dto) {
        return getBaseRepository().findAll(dto);
    }

    @Override
    public List<Role> list2(RoleDto dto) {
        String jpql = "select r from Role r order by r.createDate";
        return getBaseRepository().findAllByJPQL(jpql);
    }

    @Override
    public List<Role> list3(RoleDto dto) {
        String sql = "select * from role";
        return getBaseRepository().findAllBySQL(sql);
    }


}
