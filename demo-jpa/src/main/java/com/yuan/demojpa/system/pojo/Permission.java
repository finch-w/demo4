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
public class Permission extends BasePojo {
    @Column(name = "name")
    private String name;//权限名称
    @Column(name = "enabled")
    private Integer enabled;//是否启用

    @Builder
    public Permission(String id, String createUser, Date createDate, String updateUser, Date updateDate, String name, Integer enabled) {
        super(id, createUser, createDate, updateUser, updateDate);
        this.name = name;
        this.enabled = enabled;
    }
}
