package com.yuan.demojpa.system.pojo;

import com.yuan.demojpa.commons.pojo.BasePojo;
import lombok.*;

import javax.persistence.Entity;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Resource extends BasePojo {
    private String name;
    private String detail;
    private String url;
    private String parent;
    private Integer level;

    @Builder
    public Resource(String id, String createUser, Date createDate, String updateUser, Date updateDate, String name, String detail, String url, String parent, Integer level) {
        super(id, createUser, createDate, updateUser, updateDate);
        this.name = name;
        this.detail = detail;
        this.url = url;
        this.parent = parent;
        this.level = level;
    }
}
