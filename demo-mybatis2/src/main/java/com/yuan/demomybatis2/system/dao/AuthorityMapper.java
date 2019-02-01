package com.yuan.demomybatis2.system.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuan.demomybatis2.commons.dao.BaseMapper;
import com.yuan.demomybatis2.system.dto.SysAuthorityDto;
import com.yuan.demomybatis2.system.pojo.SysAuthority;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AuthorityMapper extends BaseMapper<SysAuthority> {
    IPage<SysAuthority> selectPage(Page page, @Param("dto") SysAuthorityDto dto);

    List<SysAuthority> selectList(SysAuthorityDto dto);
}
