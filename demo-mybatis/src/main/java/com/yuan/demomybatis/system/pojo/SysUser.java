package com.yuan.demomybatis.system.pojo;

import com.yuan.demomybatis.commons.pojo.BasePojo;
import lombok.*;
import org.hibernate.validator.constraints.Length;

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
    @Length(min = 6, message = "密码长度至少六位")
    private String password;

    @Builder
    public SysUser(String id, Date createDate, Date updateDate, String createUser, String updateUser, String username, String name, String password) {
        super(id, createDate, updateDate, createUser, updateUser);
        this.username = username;
        this.name = name;
        this.password = password;
    }
}
