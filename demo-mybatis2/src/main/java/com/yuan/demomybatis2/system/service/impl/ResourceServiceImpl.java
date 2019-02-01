package com.yuan.demomybatis2.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuan.demomybatis2.commons.service.impl.BaseServiceImpl;
import com.yuan.demomybatis2.system.dao.ResourceMapper;
import com.yuan.demomybatis2.system.dto.SysResourceDto;
import com.yuan.demomybatis2.system.pojo.SysResource;
import com.yuan.demomybatis2.system.service.ResourceService;
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
    public ResourceMapper getBaseMapper() {
        return resourceMapper;
    }

    @Override
    public IPage<SysResource> selectPage(SysResourceDto dto) {
        return getBaseMapper().selectPage(new Page(dto.getPage(), dto.getSize()), dto);
    }
}
