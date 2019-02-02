package com.yuan.demojpa.system.pojo;

import com.yuan.demojpa.commons.pojo.BasePojo;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sys_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysUser extends BasePojo {
    @NotEmpty(message = "账户不能为空")
    private String username;
    @NotEmpty(message = "用户名不能为空")
    private String name;
    @NotEmpty(message = "密码不能为空")
    private String password;

    @Builder
    public SysUser(String id, String createUser, String updateUser, Date createDate, Date updateDate, String username, String name, String password) {
        super(id, createUser, updateUser, createDate, updateDate);
        this.username = username;
        this.name = name;
        this.password = password;
    }
}
