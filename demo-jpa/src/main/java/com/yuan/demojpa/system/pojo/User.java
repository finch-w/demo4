package com.yuan.demojpa.system.pojo;

import com.yuan.demojpa.commons.pojo.BasePojo;
import lombok.*;

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

    @Builder
    public User(String id, String createUser, Date createDate, String updateUser, Date updateDate, String name, String password, String username, Integer enabled, String salt) {
        super(id, createUser, createDate, updateUser, updateDate);
        this.name = name;
        this.password = password;
        this.username = username;
        this.enabled = enabled;
        this.salt = salt;
    }
}
