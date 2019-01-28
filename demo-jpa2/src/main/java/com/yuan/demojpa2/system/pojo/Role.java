package com.yuan.demojpa2.system.pojo;

import com.yuan.demojpa2.commons.pojo.BasePojo;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "role")
public class Role extends BasePojo {
    @Column(name = "name")
    private String name;
    @Column(name = "enabled")
    private Integer enabled;

    @Builder
    public Role(String id, String createUser, String updateUser, Date createDate, Date updateDate, String name, Integer enabled) {
        super(id, createUser, updateUser, createDate, updateDate);
        this.name = name;
        this.enabled = enabled;
    }
}
