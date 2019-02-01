package com.yuan.demomybatis2.system.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuan.demomybatis2.commons.dao.BaseMapper;
import com.yuan.demomybatis2.system.dto.SysRoleDto;
import com.yuan.demomybatis2.system.pojo.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RoleMapper extends BaseMapper<SysRole> {
    IPage<SysRole> selectPage(Page page, @Param("dto") SysRoleDto dto);
}
