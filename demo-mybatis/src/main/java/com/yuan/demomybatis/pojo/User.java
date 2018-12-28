package com.yuan.demomybatis.pojo;

import com.yuan.demomybatis.commons.pojo.BasePojo;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Mybatis Generator 2018/12/19
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
public class User extends BasePojo {
    private String name;
    private String username;
    private String password;
    private String salt;
    private Integer enabled;

    @Builder
    public User(String id, String createUser, String updateUser, Date createDate, Date updateDate, String name, String username, String password, String salt, Integer enabled) {
        super(id, createUser, updateUser, createDate, updateDate);
        this.name = name;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.enabled = enabled;
    }
}