package com.yuan.demomybatis.system.pojo;

import com.yuan.demomybatis.commons.pojo.BasePojo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "sys_role")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysRole extends BasePojo {
    private String name;

    @Builder
    public SysRole(String id, Date createDate, Date updateDate, String createUser, String updateUser, String name) {
        super(id, createDate, updateDate, createUser, updateUser);
        this.name = name;
    }
}
