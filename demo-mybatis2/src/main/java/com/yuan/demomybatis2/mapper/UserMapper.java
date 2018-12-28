package com.yuan.demomybatis2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuan.demomybatis2.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * Created by Mybatis Generator 2018/12/20
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    IPage<User> selectPage(IPage<User> page);
}