package com.yuan.demojpa.system.pojo;

import com.yuan.demojpa.commons.pojo.BasePojo;
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
    public Permission(String id, String createUser, Date createDate, String updateUser, Date updateDate, String name, Integer enabled) {
        super(id, createUser, createDate, updateUser, updateDate);
        this.name = name;
        this.enabled = enabled;
    }
}
