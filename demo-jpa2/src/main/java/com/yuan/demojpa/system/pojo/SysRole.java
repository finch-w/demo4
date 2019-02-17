package com.yuan.demojpa.system.pojo;

import com.yuan.demojpa.commons.pojo.BasePojo;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "sys_role")
public class SysRole extends BasePojo {
    private String name;

    @Builder
    public SysRole(String id, String createUser, String updateUser, Date createDate, Date updateDate, String name) {
        super(id, createUser, updateUser, createDate, updateDate);
        this.name = name;
    }
}
