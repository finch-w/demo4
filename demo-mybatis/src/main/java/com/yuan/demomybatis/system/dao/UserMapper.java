package com.yuan.demomybatis.system.dao;

import com.yuan.demomybatis.commons.dao.BaseMapper;
import com.yuan.demomybatis.system.dto.SysUserDto;
import com.yuan.demomybatis.system.pojo.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<SysUser> {
    List<SysUser> selectList(SysUserDto dto);
}
