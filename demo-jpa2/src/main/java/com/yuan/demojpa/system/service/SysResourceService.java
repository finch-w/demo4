package com.yuan.demojpa.system.service;

import com.yuan.demojpa.commons.service.BaseService;
import com.yuan.demojpa.system.dto.SysResourceDto;
import com.yuan.demojpa.system.pojo.SysResource;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface SysResourceService extends BaseService<SysResource, String> {
    Page<SysResource> selectPage(SysResourceDto dto);

    List<SysResource> selectList(SysResourceDto dto);

    Optional<SysResource> selectOne(SysResourceDto dto);

    Page<SysResource> selectPageJPQL(SysResourceDto dto);

    List<SysResource> selectListJPQL(SysResourceDto dto);

    Optional<SysResource> selectOneJPQL(SysResourceDto dto);

    Page<SysResource> selectPageSQL(SysResourceDto dto);

    List<SysResource> selectListSQL(SysResourceDto dto);

    Optional<SysResource> selectOneSQL(SysResourceDto dto);

    Page<SysResource> selectPageDSL(SysResourceDto dto);

    List<SysResource> selectListDSL(SysResourceDto dto);

    Optional<SysResource> selectOneDSL(SysResourceDto dto);
}
