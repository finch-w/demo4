package com.yuan.demojpa.system.pojo;

import com.yuan.demojpa.commons.pojo.BasePojo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "rolePermission")
@EqualsAndHashCode(callSuper = true)
@Data
public class RolePermission extends BasePojo {
    @Column(name = "roleId")
    private String roleId;
    @Column(name = "permissionId")
    private String permissionId;
}
