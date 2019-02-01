package com.yuan.demomybatis2.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yuan.demomybatis2.commons.service.BaseService;
import com.yuan.demomybatis2.system.dto.SysAuthorityDto;
import com.yuan.demomybatis2.system.pojo.SysAuthority;

public interface AuthorityService extends BaseService<SysAuthority, String> {
    IPage<SysAuthority> selectPage(SysAuthorityDto dto);
}
