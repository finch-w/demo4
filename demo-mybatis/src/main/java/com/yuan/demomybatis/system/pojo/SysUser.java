package com.yuan.demomybatis.system.pojo;

import com.yuan.demomybatis.commons.pojo.BasePojo;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sys_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysUser extends BasePojo {
    private String username;
    private String name;
    private String password;

    @Builder
    public SysUser(String id, Date createDate, Date updateDate, String createUser, String updateUser, String username, String name, String password) {
        super(id, createDate, updateDate, createUser, updateUser);
        this.username = username;
        this.name = name;
        this.password = password;
    }
}
