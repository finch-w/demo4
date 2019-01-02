package com.yuan.demojpa.system.pojo;

import com.yuan.demojpa.commons.pojo.BasePojo;
import lombok.*;

import javax.persistence.Entity;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resource extends BasePojo {
    private String name;
    private String parent;
    private String url;
    private String icon;
    private Integer level;
    private Integer orderNum;

    @Builder
    public Resource(String id, String createUser, String updateUser, Date createDate, Date updateDate, String name, String parent, String url, String icon, Integer level, Integer orderNum) {
        super(id, createUser, updateUser, createDate, updateDate);
        this.name = name;
        this.parent = parent;
        this.url = url;
        this.icon = icon;
        this.level = level;
        this.orderNum = orderNum;
    }
}
