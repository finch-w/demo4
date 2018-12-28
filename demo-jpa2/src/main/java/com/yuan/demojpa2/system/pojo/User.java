package com.yuan.demojpa2.system.pojo;

import com.yuan.demojpa2.commons.pojo.BasePojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends BasePojo {
    private String name;
    private String password;
    private String username;
    private Integer enabled;
    private String salt;

    public User(String id, Long createUser, Long updateUser, Date createDate, Date updateDate, String name, String password, String username, Integer enabled, String salt) {
        super(id, createUser, updateUser, createDate, updateDate);
        this.name = name;
        this.password = password;
        this.username = username;
        this.enabled = enabled;
        this.salt = salt;
    }
}
