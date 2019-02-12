package com.yuan.demojpa2.system.pojo;

import com.yuan.demojpa2.commons.pojo.BasePojo;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sys_user")
public class SysUser extends BasePojo {
    private String username;
    private String name;
    private String password;
    @Column(name = "is_del")
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
