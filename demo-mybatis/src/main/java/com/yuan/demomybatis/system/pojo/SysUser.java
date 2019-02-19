package com.yuan.demomybatis.system.pojo;

import com.yuan.demomybatis.commons.pojo.BasePojo;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sys_user")
public class SysUser extends BasePojo implements UserDetails {
    private String username;//账户名
    private String name;//用户名
    private String password;//密码
    @Column(name = "is_del")
    private Integer isDel;
    @Singular
    private List<GrantedAuthority> authorities;

    @Builder
    public SysUser(String id, Date createDate, Date updateDate, String createUser, String updateUser, String username, String name, String password, Integer isDel, List<GrantedAuthority> authorities) {
        super(id, createDate, updateDate, createUser, updateUser);
        this.username = username;
        this.name = name;
        this.password = password;
        this.isDel = isDel;
        this.authorities = authorities;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
