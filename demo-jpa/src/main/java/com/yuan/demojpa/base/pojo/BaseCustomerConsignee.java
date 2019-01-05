package com.yuan.demojpa.base.pojo;

import com.yuan.demojpa.commons.pojo.BasePojo;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table(name = "baseCustomerConsignee")
public class BaseCustomerConsignee extends BasePojo {
    private String name;
    @Column(name = "idNum")
    private String idNum;
    private String airport;
    @Column(name = "isDefaulut")
    private Integer isDefaulut;
    private Integer enabled;

    @Builder
    public BaseCustomerConsignee(String id, String createUser, String updateUser, Date createDate, Date updateDate, String name, String idNum, String airport, Integer isDefaulut, Integer enabled) {
        super(id, createUser, updateUser, createDate, updateDate);
        this.name = name;
        this.idNum = idNum;
        this.airport = airport;
        this.isDefaulut = isDefaulut;
        this.enabled = enabled;
    }
}
