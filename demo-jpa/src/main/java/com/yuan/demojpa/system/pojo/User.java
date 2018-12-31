package com.yuan.demojpa.system.pojo;

import com.yuan.demojpa.commons.pojo.BasePojo;
import lombok.*;

import javax.persistence.Entity;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends BasePojo {
    private String username;
    private String password;
    private String name;
    private String salt;
    private Integer enabled;

    @Builder
    public User(String id, String createUser, String updateUser, Date createDate, Date updateDate, String username, String password, String name, String salt, Integer enabled) {
        super(id, createUser, updateUser, createDate, updateDate);
        this.username = username;
        this.password = password;
        this.name = name;
        this.salt = salt;
        this.enabled = enabled;
    }
}
