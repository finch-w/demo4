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
public class RolePermission extends BasePojo {
    private String roleId;
    private String permissionId;

    @Builder
    public RolePermission(String id, String createUser, Date createDate, String updateUser, Date updateDate, String roleId, String permissionId) {
        super(id, createUser, createDate, updateUser, updateDate);
        this.roleId = roleId;
        this.permissionId = permissionId;
    }
}
