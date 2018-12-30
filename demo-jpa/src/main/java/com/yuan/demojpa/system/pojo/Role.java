package com.yuan.demojpa.system.pojo;

import com.yuan.demojpa.commons.pojo.BasePojo;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

/**
 * 权限角色
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role extends BasePojo {
    @Column(name = "role")
    private String name;//角色名
    @Column(name = "enabled")
    private Integer enabled;//是否启用

    @Builder
    public Role(String id, String createUser, Date createDate, String updateUser, Date updateDate, String name, Integer enabled) {
        super(id, createUser, createDate, updateUser, updateDate);
        this.name = name;
        this.enabled = enabled;
    }
}
