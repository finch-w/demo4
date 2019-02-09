package com.yuan.demomybatis3.system.pojo;

import com.gitee.hengboy.mybatis.enhance.common.annotation.Column;
import com.gitee.hengboy.mybatis.enhance.common.annotation.Table;
import com.yuan.demomybatis3.commons.pojo.BasePojo;
import lombok.Builder;

import java.util.Date;

@Table(name = "sys_user")
public class SysUser extends BasePojo {
    private String username;
    private String name;
    private String passord;
    @Column(name = "is_del")
    private Integer isDel;

    @Builder
    public SysUser(String id, String createUser, String updateUser, Date createDate, Date updateDate, String username, String name, String passord, Integer isDel) {
        super(id, createUser, updateUser, createDate, updateDate);
        this.username = username;
        this.name = name;
        this.passord = passord;
        this.isDel = isDel;
    }
}
