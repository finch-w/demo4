package com.yuan.demomybatis2.system.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yuan.demomybatis2.commons.pojo.BasePojo;
import lombok.*;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_user")
public class SysUser extends BasePojo {
    private String username;
    private String name;
    private String password;
    @TableField(value = "is_del")
    private Integer isDel;

    @Builder
    public SysUser(String id, String createUser, String updateUser, Date createDate, Date updateDate, String username, String name, String password, Integer isDel) {
        super(id, createUser, updateUser, createDate, updateDate);
        this.username = username;
        this.name = name;
        this.password = password;
        this.isDel = isDel;
    }
}
