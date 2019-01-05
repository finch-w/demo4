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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "baseDocument")
public class BaseDocument extends BasePojo {
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

    @Builder
    public BaseDocument(String id, String createUser, String updateUser, Date createDate, Date updateDate, String airways, String flightNo, Integer isSpecial, String type, String code, String inDepartment, Integer inStatus, String outDepartment, Integer outStatus) {
        super(id, createUser, updateUser, createDate, updateDate);
        this.airways = airways;
        this.flightNo = flightNo;
        this.isSpecial = isSpecial;
        this.type = type;
        this.code = code;
        this.inDepartment = inDepartment;
        this.inStatus = inStatus;
        this.outDepartment = outDepartment;
        this.outStatus = outStatus;
    }
}
