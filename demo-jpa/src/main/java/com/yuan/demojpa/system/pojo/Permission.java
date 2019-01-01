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
public class Permission extends BasePojo {
    private String name;
    private Integer enabled;

    @Builder
    public Permission(String id, String createUser, String updateUser, Date createDate, Date updateDate, String name, Integer enabled) {
        super(id, createUser, updateUser, createDate, updateDate);
        this.name = name;
        this.enabled = enabled;
    }
}
