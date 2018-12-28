package com.yuan.demojpa2.system.pojo;

import com.yuan.demojpa2.commons.pojo.BasePojo;
import lombok.*;

import javax.persistence.Entity;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Permission extends BasePojo {
    private String name;
    private Integer enabled;

    @Builder
    public Permission(String id, Long createUser, Long updateUser, Date createDate, Date updateDate, String name, Integer enabled) {
        super(id, createUser, updateUser, createDate, updateDate);
        this.name = name;
        this.enabled = enabled;
    }
}
