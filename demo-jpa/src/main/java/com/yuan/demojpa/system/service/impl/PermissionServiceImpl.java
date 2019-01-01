package com.yuan.demojpa.system.service.impl;

import com.yuan.demojpa.commons.service.impl.BaseServiceImpl;
import com.yuan.demojpa.system.dto.PermissionDto;
import com.yuan.demojpa.system.pojo.Permission;
import com.yuan.demojpa.system.repository.PermissionRepository;
import com.yuan.demojpa.system.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

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
        String jpql = "select p from Permission p where order by p.createDate desc ";
        return getBaseRepository().findAllByJPQL(jpql, PageRequest.of(dto.getPage(), dto.getSize()));
    }

    @Override
    public Page<Permission> data3(PermissionDto dto) {
        String sql = "select * from permission order by createDate desc ";
        return getBaseRepository().findAllBySQL(sql, PageRequest.of(dto.getPage(), dto.getSize()));
    }


    @Override
    public List<Permission> list(PermissionDto dto) {
        return getBaseRepository().findAll(dto);
    }

    @Override
    public List<Permission> list2(PermissionDto dto) {
        String jpql = "select p from Permmsion p order by p.createDate desc";
        return getBaseRepository().findAllBySQL(jpql);
    }

    @Override
    public List<Permission> list3(PermissionDto dto) {
        String sql = "select * from permission order by createDate desc ";
        return getBaseRepository().findAllBySQL(sql);
    }


}

