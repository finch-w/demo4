package com.yuan.demojpa.base.pojo;

import com.yuan.demojpa.commons.pojo.BasePojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseOriginDocument extends BasePojo {
    private String airways;
    private String flightNo;
    @Column(name = "isSpecial")
    private Integer isSpecial;
    private String type;
    private String code;
    @Column(name = "inDepartment")
    private String inDepartment;
    @Column(name = "inStatus")
    private Integer inStatus;
    @Column(name = "outDepartment")
    private String outDepartment;
    @Column(name = "outStatus")
    private Integer outStatus;
}
