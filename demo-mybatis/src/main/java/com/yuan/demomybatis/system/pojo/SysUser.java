package com.yuan.demomybatis.system.pojo;

import com.yuan.demomybatis.commons.pojo.BasePojo;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sys_user")
public class SysUser extends BasePojo {
    private String username;//账户名
}
