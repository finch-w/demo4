package com.yuan.demojooqjpa.system.pojo;

import com.yuan.demojooqjpa.commons.pojo.BasePojo;
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
    public User(String id, Long createUser, Long updateUser, Date createDate, Date updateDate, String name, String password, String username, Integer enabled, String salt) {
        super(id, createUser, updateUser, createDate, updateDate);
        this.name = name;
        this.password = password;
        this.username = username;
        this.enabled = enabled;
        this.salt = salt;
    }
}
