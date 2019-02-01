package com.yuan.demomybatis2.system.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuan.demomybatis2.commons.dao.BaseMapper;
import com.yuan.demomybatis2.system.dto.SysResourceDto;
import com.yuan.demomybatis2.system.pojo.SysResource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ResourceMapper extends BaseMapper<SysResource> {
    IPage<SysResource> selectPage(Page page, @Param("dto") SysResourceDto dto);

    List<SysResource> selectList(SysResourceDto dto);
}
