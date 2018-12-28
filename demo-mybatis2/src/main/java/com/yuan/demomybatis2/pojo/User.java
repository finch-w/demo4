package com.yuan.demomybatis2.pojo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
* Created by Mybatis Generator 2018/12/20
*/
@Data
@TableName(value = "user")
public class User implements Serializable {
    @TableId(type = IdType.UUID)
    private String id;

    private String name;

    private static final long serialVersionUID = 1L;
}