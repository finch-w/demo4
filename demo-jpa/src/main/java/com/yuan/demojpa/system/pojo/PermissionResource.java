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
public class PermissionResource extends BasePojo {
    private String permissionId;
    private String resourceId;

    @Builder
    public PermissionResource(String id, String createUser, Date createDate, String updateUser, Date updateDate, String permissionId, String resourceId) {
        super(id, createUser, createDate, updateUser, updateDate);
        this.permissionId = permissionId;
        this.resourceId = resourceId;
    }
}
