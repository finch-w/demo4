package com.yuan.demomybatis.system.service;

import com.github.pagehelper.PageInfo;
import com.yuan.demomybatis.commons.service.BaseService;
import com.yuan.demomybatis.system.dto.SysAuthorityDto;
import com.yuan.demomybatis.system.pojo.SysAuthority;

public interface AuthorityService extends BaseService<SysAuthority, String> {
    PageInfo<SysAuthority> selectPage(SysAuthorityDto dto);
}
