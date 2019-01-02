package com.yuan.demojpa.system.service.impl;

import com.yuan.demojpa.commons.service.impl.BaseServiceImpl;
import com.yuan.demojpa.system.dto.ResourceDto;
import com.yuan.demojpa.system.pojo.Resource;
import com.yuan.demojpa.system.repository.ResourceRepository;
import com.yuan.demojpa.system.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ResourceServiceImpl extends BaseServiceImpl<Resource, String, ResourceRepository> implements ResourceService {
    private final ResourceRepository resourceRepository;

    @Autowired
    public ResourceServiceImpl(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    @Override
    public ResourceRepository getBaseRepository() {
        return resourceRepository;
    }

    @Override
    public Page<Resource> data(ResourceDto dto) {
        return getBaseRepository().findAll(dto, PageRequest.of(dto.getPage(), dto.getSize(), Sort.by(Sort.Order.desc("createDate"))));
    }

    @Override
    public Page<Resource> data2(ResourceDto dto) {
        String jpql = "select r from Resource r order by r.createDate desc ";
        Map<String, Object> map = new HashMap<>();
        return getBaseRepository().findAllByJPQL(jpql, PageRequest.of(dto.getPage(), dto.getSize()), map);
    }

    @Override
    public Page<Resource> data3(ResourceDto dto) {
        String sql = "select * from resource order by createDate desc ";
        Map<String, Object> map = new HashMap<>();
        return getBaseRepository().findAllBySQL(sql, PageRequest.of(dto.getPage(), dto.getSize()), map);
    }

    @Override
    public List<Resource> list(ResourceDto dto) {
        return getBaseRepository().findAll(dto, Sort.by(Sort.Order.desc("createDate")));
    }

    @Override
    public List<Resource> list2(ResourceDto dto) {
        String jpql = "select r from Resource r order by r.createDate desc ";
        Map<String, Object> map = new HashMap<>();
        return getBaseRepository().findAllByJPQL(jpql, map);
    }

    @Override
    public List<Resource> list3(ResourceDto dto) {
        String sql = "select * from resource order by createDate desc ";
        Map<String, Object> map = new HashMap<>();
        return getBaseRepository().findAllBySQL(sql, map);
    }
}
