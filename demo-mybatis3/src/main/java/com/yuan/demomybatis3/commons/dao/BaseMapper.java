package com.yuan.demomybatis3.commons.dao;

import com.gitee.hengboy.mybatis.enhance.mapper.EnhanceMapper;
import org.apache.ibatis.annotations.Mapper;

import java.io.Serializable;

@Mapper
public interface BaseMapper<T, Id extends Serializable> extends EnhanceMapper<T, Id> {
}
