package com.yuan.demojpa.system.service;

import com.yuan.demojpa.commons.service.BaseService;
import com.yuan.demojpa.system.dto.SysAuthorityDto;
import com.yuan.demojpa.system.pojo.SysAuthority;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface SysAuthorityService extends BaseService<SysAuthority, String> {
    Page<SysAuthority> selectPage(SysAuthorityDto dto);

    List<SysAuthority> selectList(SysAuthorityDto dto);

    Optional<SysAuthority> selectOne(SysAuthorityDto dto);

    Page<SysAuthority> selectPageSQL(SysAuthorityDto dto);

    List<SysAuthority> selectListSQL(SysAuthorityDto dto);

    Optional<SysAuthority> selectOneSQL(SysAuthorityDto dto);

    Page<SysAuthority> selectPageJPQL(SysAuthorityDto dto);

    List<SysAuthority> selectListJPQL(SysAuthorityDto dto);

    Optional<SysAuthority> selectOneJPQL(SysAuthorityDto dto);

    Page<SysAuthority> selectPageDSL(SysAuthorityDto dto);

    List<SysAuthority> selectListDSL(SysAuthorityDto dto);

    Optional<SysAuthority> selectOneDSL(SysAuthorityDto dto);

    boolean checkInsert(SysAuthority authority);
}
