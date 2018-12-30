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
public class Resource extends BasePojo {
    @Column(name = "name", unique = true)
    private String name;//资源名称
    @Column(name = "url", unique = true)
    private String url;//路径地址
    @Column(name = "icon")
    private String icon;//图标
    @Column(name = "parent")
    private String parent;//父级
    @Column(name = "level")
    private Integer level;//层级
    @Column(name = "order")
    private Integer order;//资源顺序

    @Builder
    public Resource(String id, String createUser, Date createDate, String updateUser, Date updateDate, String name, String url, String icon, String parent, Integer level, Integer order) {
        super(id, createUser, createDate, updateUser, updateDate);
        this.name = name;
        this.url = url;
        this.icon = icon;
        this.parent = parent;
        this.level = level;
        this.order = order;
    }
}
