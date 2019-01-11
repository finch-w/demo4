package com.yuan.demojpa.system.pojo;

import com.yuan.demojpa.commons.pojo.BasePojo;
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
@Table(name = "resource")
public class Resource extends BasePojo {
    @Column(name = "name")
    private String name;
    @Column(name = "parent")
    private String parent;
    @Column(name = "url")
    private String url;
    @Column(name = "icon")
    private String icon;
    @Column(name = "level")
    private Integer level;
    @Column(name = "parentOrder")
    private Integer parentOrder;

    @Builder
    public Resource(String id, String createUser, String updateUser, Date createDate, Date updateDate, String name, String parent, String url, String icon, Integer level, Integer parentOrder) {
        super(id, createUser, updateUser, createDate, updateDate);
        this.name = name;
        this.parent = parent;
        this.url = url;
        this.icon = icon;
        this.level = level;
        this.parentOrder = parentOrder;
    }
}
