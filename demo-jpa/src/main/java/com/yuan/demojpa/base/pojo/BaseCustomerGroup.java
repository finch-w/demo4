package com.yuan.demojpa.base.pojo;

import com.yuan.demojpa.commons.pojo.BasePojo;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "baseCustomerGroup")
@NoArgsConstructor
@AllArgsConstructor
public class BaseCustomerGroup extends BasePojo {
    private String name;
    private Integer enabled;

    @Builder
    public BaseCustomerGroup(String id, String createUser, String updateUser, Date createDate, Date updateDate, String name, Integer enabled) {
        super(id, createUser, updateUser, createDate, updateDate);
        this.name = name;
        this.enabled = enabled;
    }
}
