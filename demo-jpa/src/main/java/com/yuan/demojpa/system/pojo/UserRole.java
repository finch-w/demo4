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
public class UserRole extends BasePojo {
    private String userId;
    private String roleId;

    @Builder
    public UserRole(String id, String createUser, Date createDate, String updateUser, Date updateDate, String userId, String roleId) {
        super(id, createUser, createDate, updateUser, updateDate);
        this.userId = userId;
        this.roleId = roleId;
    }
}
