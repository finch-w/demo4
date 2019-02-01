package com.yuan.demomybatis2.system.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yuan.demomybatis2.commons.pojo.BasePojo;
import lombok.*;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@TableName("sys_resource")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysResource extends BasePojo {
    private String name;
    private String url;
    private String icon;
    private String parent;
    private Integer level;

    @Builder

    public SysResource(String id, String createUser, String updateUser, Date createDate, Date updateDate, String name, String url, String icon, String parent, Integer level) {
        super(id, createUser, updateUser, createDate, updateDate);
        this.name = name;
        this.url = url;
        this.icon = icon;
        this.parent = parent;
        this.level = level;
    }
}
