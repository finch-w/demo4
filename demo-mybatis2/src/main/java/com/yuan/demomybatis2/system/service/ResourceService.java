package com.yuan.demomybatis2.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yuan.demomybatis2.commons.service.BaseService;
import com.yuan.demomybatis2.system.dto.SysResourceDto;
import com.yuan.demomybatis2.system.pojo.SysResource;

public interface ResourceService extends BaseService<SysResource, String> {
    IPage<SysResource> selectPage(SysResourceDto dto);
}
