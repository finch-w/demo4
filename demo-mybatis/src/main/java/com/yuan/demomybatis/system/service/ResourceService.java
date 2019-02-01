package com.yuan.demomybatis.system.service;

import com.github.pagehelper.PageInfo;
import com.yuan.demomybatis.commons.service.BaseService;
import com.yuan.demomybatis.system.dto.SysResourceDto;
import com.yuan.demomybatis.system.pojo.SysResource;

public interface ResourceService extends BaseService<SysResource, String> {
    PageInfo<SysResource> selectPage(SysResourceDto dto);
}
