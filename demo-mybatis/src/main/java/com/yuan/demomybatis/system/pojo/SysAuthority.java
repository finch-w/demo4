package com.yuan.demomybatis.system.pojo;

import com.yuan.demomybatis.commons.pojo.BasePojo;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sys_authority")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysAuthority extends BasePojo {
    private String name;

    @Builder
    public SysAuthority(String id, Date createDate, Date updateDate, String createUser, String updateUser, String name) {
        super(id, createDate, updateDate, createUser, updateUser);
        this.name = name;
    }
}
