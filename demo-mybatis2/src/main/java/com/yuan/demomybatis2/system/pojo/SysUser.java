package com.yuan.demomybatis2.system.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yuan.demomybatis2.commons.pojo.BasePojo;
import lombok.*;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "sys_user")
@NoArgsConstructor
@AllArgsConstructor
public class SysUser extends BasePojo {
    private String username;
    private String name;
    private String password;

    @Builder
    public SysUser(String id, String createUser, String updateUser, Date createDate, Date updateDate, String username, String name, String password) {
        super(id, createUser, updateUser, createDate, updateDate);
        this.username = username;
        this.name = name;
        this.password = password;
    }
}
