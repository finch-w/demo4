package com.yuan.demomybatis2.system.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yuan.demomybatis2.commons.pojo.BasePojo;
import lombok.*;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_role")
@NoArgsConstructor
@AllArgsConstructor
public class SysRole extends BasePojo {
    private String name;

    @Builder
    public SysRole(String id, String createUser, String updateUser, Date createDate, Date updateDate, String name) {
        super(id, createUser, updateUser, createDate, updateDate);
        this.name = name;
    }
}
