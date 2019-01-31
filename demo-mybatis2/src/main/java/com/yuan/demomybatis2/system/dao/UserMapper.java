package com.yuan.demomybatis2.system.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuan.demomybatis2.commons.dao.BaseMapper;
import com.yuan.demomybatis2.system.dto.SysUserDto;
import com.yuan.demomybatis2.system.pojo.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper extends BaseMapper<SysUser> {

    IPage<SysUser> findAllByCondition(Page<SysUser> page, @Param("dto") SysUserDto dto);
}
