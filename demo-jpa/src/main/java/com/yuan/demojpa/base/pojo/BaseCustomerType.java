package com.yuan.demojpa.base.pojo;

import com.yuan.demojpa.commons.pojo.BasePojo;
import lombok.*;

import javax.persistence.Entity;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BaseCustomerType extends BasePojo {
    private String name;//客户分组
    private Integer enabled;//启用状态(0：启用，1：停用)

    @Builder
    public BaseCustomerType(String id, String createUser, Date createDate, String updateUser, Date updateDate, String name, Integer enabled) {
        super(id, createUser, createDate, updateUser, updateDate);
        this.name = name;
        this.enabled = enabled;
    }
}
