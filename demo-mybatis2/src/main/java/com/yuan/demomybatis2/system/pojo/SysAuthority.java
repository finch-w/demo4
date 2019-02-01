package com.yuan.demomybatis2.system.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yuan.demomybatis2.commons.pojo.BasePojo;
import lombok.*;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@TableName("sys_authority")
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
