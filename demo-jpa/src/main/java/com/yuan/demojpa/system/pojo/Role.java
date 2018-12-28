package com.yuan.demojpa.system.pojo;

import com.yuan.demojpa.commons.pojo.BasePojo;
import lombok.*;

import javax.persistence.Entity;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role extends BasePojo {
    private String name;
    private String enabled;

    @Builder
    public Role(String id, String createUser, Date createDate, String updateUser, Date updateDate, String name, String enabled) {
        super(id, createUser, createDate, updateUser, updateDate);
        this.name = name;
        this.enabled = enabled;
    }
}
