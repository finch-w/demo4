package com.yuan.demojpa.base.dto;

import com.yuan.demojpa.commons.pojo.BasePojo;
import lombok.*;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseCustomerGroup extends BasePojo {
    private String name;

    @Builder
    public BaseCustomerGroup(String id, String createUser, Date createDate, String updateUser, Date updateDate, String name) {
        super(id, createUser, createDate, updateUser, updateDate);
        this.name = name;
    }
}
