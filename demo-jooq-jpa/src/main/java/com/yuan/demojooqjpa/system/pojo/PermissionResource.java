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
public class PermissionResource extends BasePojo {
    private String permissionId;
    private String resourceId;

    @Builder
    public PermissionResource(String id, Long createUser, Long updateUser, Date createDate, Date updateDate, String permissionId, String resourceId) {
        super(id, createUser, updateUser, createDate, updateDate);
        this.permissionId = permissionId;
        this.resourceId = resourceId;
    }
}
