package com.yuan.demojooqjpa.system.pojo;

import com.yuan.demojooqjpa.commons.pojo.BasePojo;
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
    public Resource(String id, Long createUser, Long updateUser, Date createDate, Date updateDate, String name, String detail, String url, String parent, Integer level) {
        super(id, createUser, updateUser, createDate, updateDate);
        this.name = name;
        this.detail = detail;
        this.url = url;
        this.parent = parent;
        this.level = level;
    }
}
