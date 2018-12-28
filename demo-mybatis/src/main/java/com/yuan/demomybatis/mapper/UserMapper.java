package com.yuan.demomybatis.mapper;

import com.yuan.demomybatis.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;



/**
 * Created by Mybatis Generator 2018/12/19
 */
@Mapper
public interface UserMapper extends tk.mybatis.mapper.common.Mapper<User>, MySqlMapper<User> {
    User selectById(Long id);



}