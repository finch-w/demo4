package com.yuan.demomybatis.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuan.demomybatis.commons.service.impl.BaseServiceImpl;
import com.yuan.demomybatis.system.dao.AuthorityMapper;
import com.yuan.demomybatis.system.dto.SysAuthorityDto;
import com.yuan.demomybatis.system.pojo.SysAuthority;
import com.yuan.demomybatis.system.service.AuthorityService;
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
    public AuthorityMapper getMapper() {
        return authorityMapper;
    }

    @Override
    public PageInfo<SysAuthority> selectPage(SysAuthorityDto dto) {
        PageHelper.startPage(dto.getPage(), dto.getSize());
        return new PageInfo<>(authorityMapper.selectList(dto));
    }
}
