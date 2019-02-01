package com.yuan.demomybatis.system.dao;

import com.yuan.demomybatis.commons.dao.BaseMapper;
import com.yuan.demomybatis.system.dto.SysRoleDto;
import com.yuan.demomybatis.system.pojo.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<SysRole> {
    List<SysRole> selectListByCondition(SysRoleDto dto);
}
