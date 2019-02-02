package com.yuan.demomybatis.system.dao;

import com.yuan.demomybatis.commons.dao.BaseMapper;
import com.yuan.demomybatis.system.dto.SysAuthorityDto;
import com.yuan.demomybatis.system.pojo.SysAuthority;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuthorityMapper extends BaseMapper<SysAuthority> {
    List<SysAuthority> selectList(SysAuthorityDto dto);

    Integer check(SysAuthority authority);
}
