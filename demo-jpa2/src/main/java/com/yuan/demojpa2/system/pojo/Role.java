package com.yuan.demojpa2.system.pojo;

import com.yuan.demojpa2.commons.pojo.BasePojo;
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
    public Role(String id, Long createUser, Long updateUser, Date createDate, Date updateDate, String name, String enabled) {
        super(id, createUser, updateUser, createDate, updateDate);
        this.name = name;
        this.enabled = enabled;
    }
}
