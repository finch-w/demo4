package com.yuan.demojpa.base.pojo;

import com.yuan.demojpa.commons.pojo.BasePojo;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "baseCustomerPicker")
@NoArgsConstructor
@AllArgsConstructor
public class BaseCustomerPicker extends BasePojo {
    private String name;
    @Column(name = "idNum")
    private String idNum;
    @Column(name = "isDefault")
    private Integer isDefault;
    private Integer enabled;

    @Builder
    public BaseCustomerPicker(String id, String createUser, String updateUser, Date createDate, Date updateDate, String name, String idNum, Integer isDefault, Integer enabled) {
        super(id, createUser, updateUser, createDate, updateDate);
        this.name = name;
        this.idNum = idNum;
        this.isDefault = isDefault;
        this.enabled = enabled;
    }
}
