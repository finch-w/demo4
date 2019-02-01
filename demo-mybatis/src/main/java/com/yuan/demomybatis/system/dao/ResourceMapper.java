package com.yuan.demomybatis.system.dao;

import com.yuan.demomybatis.commons.dao.BaseMapper;
import com.yuan.demomybatis.system.dto.SysResourceDto;
import com.yuan.demomybatis.system.pojo.SysResource;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ResourceMapper extends BaseMapper<SysResource> {
    List<SysResource> selectListByCondition(SysResourceDto dto);
}
