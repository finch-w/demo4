package com.yuan.demojpa.system.pojo;

import com.yuan.demojpa.commons.pojo.BasePojo;
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
    public SysAuthority(String id, String createUser, String updateUser, Date createDate, Date updateDate, String name) {
        super(id, createUser, updateUser, createDate, updateDate);
        this.name = name;
    }
}
