package com.yuan.demojpa.system.pojo;

import com.yuan.demojpa.commons.pojo.BasePojo;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends BasePojo {
    @Column(name = "username", updatable = false, nullable = false)
    private String username;//账号
    @Column(name = "password", nullable = false)
    private String password;//密码
    @Column(name = "salt",nullable = false)
    private String salt;//校验值
    @Column(name = "name",nullable = false)
    private String name;//姓名
    @Column(name = "enabled")
    private Integer enabled;//是否启用


    @Builder
    public User(String id, String createUser, Date createDate, String updateUser, Date updateDate, String username, String password, String salt, String name, Integer enabled) {
        super(id, createUser, createDate, updateUser, updateDate);
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.name = name;
        this.enabled = enabled;
    }
}
