package com.yuan.demomybatis2.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuan.demomybatis2.commons.service.impl.BaseServiceImpl;
import com.yuan.demomybatis2.system.dao.AuthorityMapper;
import com.yuan.demomybatis2.system.dto.SysAuthorityDto;
import com.yuan.demomybatis2.system.pojo.SysAuthority;
import com.yuan.demomybatis2.system.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl extends BaseServiceImpl<SysAuthority, String, AuthorityMapper> implements AuthorityService {
    private final AuthorityMapper authorityMapper;

    @Autowired
    public AuthorityServiceImpl(AuthorityMapper authorityMapper) {
        this.authorityMapper = authorityMapper;
    }

    @Override
    public AuthorityMapper getBaseMapper() {
        return authorityMapper;
    }

    @Override
    public IPage<SysAuthority> selectPage(SysAuthorityDto dto) {
        return getBaseMapper().selectPage(new Page(dto.getPage(), dto.getSize()), dto);
    }

    @Override
    public Integer check(SysAuthority authority) {
        return getBaseMapper().check(authority);
    }
}
