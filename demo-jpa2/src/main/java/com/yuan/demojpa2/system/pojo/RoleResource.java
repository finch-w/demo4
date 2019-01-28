package com.yuan.demojpa2.system.pojo;

import com.yuan.demojpa2.commons.pojo.BasePojo;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "role_resource")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleResource extends BasePojo {
    @Column(name = "roleId")
    private String roleId;
    @Column(name = "resourceId")
    private String resourceId;

    @Builder
    public RoleResource(String id, String createUser, String updateUser, Date createDate, Date updateDate, String roleId, String resourceId) {
        super(id, createUser, updateUser, createDate, updateDate);
        this.roleId = roleId;
        this.resourceId = resourceId;
    }
}
