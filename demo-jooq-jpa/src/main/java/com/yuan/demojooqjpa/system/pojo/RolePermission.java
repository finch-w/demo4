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
public class RolePermission extends BasePojo {
    private String roleId;
    private String permissionId;

    @Builder
    public RolePermission(String id, Long createUser, Long updateUser, Date createDate, Date updateDate, String roleId, String permissionId) {
        super(id, createUser, updateUser, createDate, updateDate);
        this.roleId = roleId;
        this.permissionId = permissionId;
    }
}
