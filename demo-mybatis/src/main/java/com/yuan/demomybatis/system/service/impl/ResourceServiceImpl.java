package com.yuan.demomybatis.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuan.demomybatis.commons.service.impl.BaseServiceImpl;
import com.yuan.demomybatis.system.dao.ResourceMapper;
import com.yuan.demomybatis.system.dto.SysResourceDto;
import com.yuan.demomybatis.system.pojo.SysResource;
import com.yuan.demomybatis.system.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceServiceImpl extends BaseServiceImpl<SysResource, String, ResourceMapper> implements ResourceService {
    private final ResourceMapper resourceMapper;

    @Autowired
    public ResourceServiceImpl(ResourceMapper resourceMapper) {
        this.resourceMapper = resourceMapper;
    }

    @Override
    public ResourceMapper getMapper() {
        return resourceMapper;
    }

    @Override
    public PageInfo<SysResource> selectPage(SysResourceDto dto) {
        PageHelper.startPage(dto.getPage(), dto.getSize());
        return new PageInfo<>(getMapper().selectListByCondition(dto));
    }
}
